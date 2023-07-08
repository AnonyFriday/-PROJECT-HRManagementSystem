/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm.entities;

import asm.enums.EmployeeType;
import asm.enums.SearchingCondition;
import static asm.enums.SearchingCondition.ID;
import static asm.enums.SearchingCondition.NAME;
import asm.utils.validators.MenuValidator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author duyvu
 */
public final class HR implements IManagement {

    // =========================================================
    // Fields
    // =========================================================
    private final ArrayList<Employee> emps;

    // =========================================================
    // Constructor 
    // =========================================================
    /**
     * Default Constructor
     */
    public HR() {
        emps = new ArrayList<>();
    }

    // =========================================================
    // Operations on List
    // =========================================================
    /**
     * Add New Employee to the List
     *
     * @param emp
     * @return
     */
    @Override
    public boolean addEmployeeToList(Employee emp) {
        int foundEmpIdx = this.findIndexEmployeeById(emp.getId());
        return foundEmpIdx == -1 && this.emps.add(emp);
    }

    /**
     * Display all employees with in the List
     *
     * Toggle the salary net output's based on customer's demand
     */
    @Override
    public void displayAllEmployees() {
        if (this.emps == null || this.emps.isEmpty()) {
            System.out.println("[]");
        }

        for (Employee emp : this.emps) {
            emp.outputEmployee(true);
        }
    }

    /**
     * Display Employee By Type
     *
     * Toggle the salary net output's based on customer's demand
     *
     * @param type
     */
    @Override
    public void displayEmployeeByType(EmployeeType type) {
        if (this.emps == null || this.emps.isEmpty()) {
            System.out.println("[]");
        }

        for (Employee emp : this.emps) {
            if (emp.getType().equals(type)) {
                emp.outputEmployee(true);
            }
        }
    }

    /**
     * Display the average of the entire company's salary net
     *
     * @return double
     */
    @Override
    public double calculateAverageSalaryNetOfCompany() {
        // In case it's empty or null, just return 0
        if (this.emps == null || this.emps.isEmpty()) {
            return 0;
        }

        double wholeSalary = 0.0;

        for (Employee emp : this.emps) {
            emp.computeSalaryNet();
            wholeSalary += emp.getSalaryNet();
        }

        return wholeSalary / this.emps.size();
    }

    /**
     * Search the highest Salary Net of the Company
     *
     * @return float
     */
    @Override
    public float searchMaxSalaryNetEmployee() {
        // In case it's empty or null, just return 0
        if (this.emps == null || this.emps.isEmpty()) {
            return 0;
        }

        float maxSalaryNet = Float.MIN_VALUE;
        for (Employee emp : this.emps) {
            emp.computeSalaryNet();
            if (maxSalaryNet < emp.getSalaryNet()) {
                maxSalaryNet = emp.getSalaryNet();
            }
        }
        return maxSalaryNet;
    }

    /**
     * Search the lowest salary net of the company
     *
     * Using Collection to handle the Min operation
     *
     * If o1 - o2 = negative => min = o1
     * If o1 - o2 = positive || 0 => skip
     *
     * @return float
     */
    @Override
    public float searchMinSalaryNetEmployee() {
        // In case it's empty or null, just return 0
        if (this.emps == null || this.emps.isEmpty()) {
            return 0;
        }

        Employee minSalaryNetEmp = Collections.min(this.emps, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                o1.computeSalaryNet();
                o2.computeSalaryNet();
                return (int) (o1.getSalaryNet() - o2.getSalaryNet());
            }
        });
        return minSalaryNetEmp.getSalaryNet();
    }

    /**
     * Searching Employee based on General Purpose Condition (Name or Id)
     *
     * @param type
     * @param keyword the keyword of keyword
     * @return the asm.entities.Employee[]
     */
    @Override
    public ArrayList<Employee> searchEmployeeByCondition(SearchingCondition type, String keyword) {

        ArrayList<Employee> founds = new ArrayList<>();
        Iterator<Employee> iter = this.emps.iterator();

        // Handle keyword in case it's null or empty
        while (iter.hasNext()) {
            switch (type) {
                case ID: {
                    while (iter.hasNext()) {
                        Employee emp = iter.next();
                        if (!keyword.isEmpty() && emp.getId().equals(keyword)) {
                            founds.add(emp);
                        }
                    }
                    break;
                }
                case NAME: {
                    while (iter.hasNext()) {
                        Employee emp = iter.next();
                        if (!keyword.isEmpty() && emp.getName().contains(keyword)) {
                            founds.add(emp);
                        }
                    }
                    break;
                }
            }
        }
        return founds;
    }

    /**
     * Sorting Employee by using Collection Chaining
     *
     * Sorting Name ascending, if name is equal, sorting Salary net descending
     */
    @Override
    public void sortEmployees() {
        // In case it's empty or null, just return it without proceeding the followed code
        if (this.emps == null && this.emps.isEmpty()) {
            return;
        }

        Comparator<Employee> compareNameAsc = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        Comparator<Employee> compareSalaryNetDsc = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                o1.computeSalaryNet();
                o2.computeSalaryNet();
                return (int) (o2.getSalaryNet() - o1.getSalaryNet());
            }
        };

        Comparator<Employee> compareNameAscSalaryNetDsc = compareNameAsc.thenComparing(compareSalaryNetDsc);
        Collections.sort(this.emps, compareNameAscSalaryNetDsc);
    }

    /**
     * Remove employee from the list based on given ID
     *
     * @param id
     * @return
     */
    @Override
    public boolean removeEmployeeById(String id) {
        // In case it's empty or null, just return it without proceeding the followed code
        if (this.emps == null && this.emps.isEmpty()) {
            return false;
        }

        // If not found, then don't need to remove
        int foundIdx = this.findIndexEmployeeById(id);
        if (foundIdx == -1) {
            return false;
        } else {
            return this.emps.remove(foundIdx) != null;
        }
    }

    /**
     * Update Employee within the list by Id
     *
     * If it's null than return false
     * If it's not null than update all fields based on ID
     *
     * @param keywordId
     * @return
     */
    @Override
    public boolean updateEmployeeById(String keywordId) {
        // In case it's empty or null, just return it without proceeding the followed code
        if (this.emps == null && this.emps.isEmpty()) {
            return false;
        }

        // If not found, then don't need to update
        int storedIdxEmp = this.findIndexEmployeeById(keywordId);
        if (storedIdxEmp == -1) {
            return false;
        } else {
            Employee storedEmp = this.emps.get(storedIdxEmp);
            storedEmp.inputAllFieldsEmployee(true);
        }
        return true;
    }

    /**
     * [ERROR]
     * Update Employee within the list by Id.
     *
     * If it's null than return false
     * If it's not null than asking user to choose the field for update
     * All goods then return true
     *
     * @param keywordId
     * @param field
     * @return
     */
//    @Override
//    public boolean updateEmployeeById(String keywordId, UpdateField field) throws Exception {
//        // In case it's empty or null, just return it without proceeding the followed code
//        if (this.emps == null && this.emps.isEmpty()) {
//            return false;
//        }
//
//        // If not found, then don't need to update
//        int storedIdxEmp = this.findIndexEmployeeById(keywordId);
//        if (storedIdxEmp == -1) {
//            return false;
//        } else {
//            Employee storedEmp = this.emps.get(storedIdxEmp);
//
//            // If the fields null or empty, than update all fields
//            if (field == null) {
//                storedEmp.inputAllFieldsEmployee(true);
//            } else {
//                //If the field is not null, than update only that field
//                Scanner sc = new Scanner(System.in);
//                switch (field) {
//                    case NAME: {
//                        storedEmp.inputFieldName(sc);
//                        break;
//                    }
//                    case DATE_OF_BIRTH: {
//                        storedEmp.inputFieldDateOfBirth(sc);
//                        break;
//                    }
//                    case EXPERIENCE: {
//                        storedEmp.inputFieldExperience(sc);
//                        break;
//                    }
//                    case GENDER: {
//                        storedEmp.inputFieldGender(sc);
//                        break;
//                    }
//                    case SALARY_BASIC: {
//                        storedEmp.inputFieldSalaryBasic(sc);
//                        break;
//                    }
//                    case REVENUE: {
//                        if (storedEmp.getType().equals(EmployeeType.BUSINESS)) {
//                            ((Business) storedEmp).inputFieldRevenue(sc);
//                        } else {
//                            throw new Exception("Administrator [" + keywordId + "] cannot update this field. Please try again.");
//                        }
//                        break;
//                    }
//                    case SUBSIDY: {
//                        if (storedEmp.getType().equals(EmployeeType.ADMINISTRATION)) {
//                            ((Administrator) storedEmp).inputFieldSubsidy(sc);
//                        } else {
//                            throw new Exception("Business [" + keywordId + "] cannot update this field. Please try again.");
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//
//        return true;
//    }
    /**
     * Find the current index of the employee within the list
     *
     * If found, return the index
     * If not found, return -1
     *
     * @param id
     * @return
     */
    @Override
    public int findIndexEmployeeById(String id) {
        // In case it's empty or null, just return it without proceeding the followed code
        if (this.emps == null && this.emps.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < this.emps.size(); i++) {
            if (this.emps.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
