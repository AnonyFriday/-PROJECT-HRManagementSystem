/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package asm.entities;

import asm.enums.EmployeeType;
import asm.enums.SearchingCondition;
import asm.enums.UpdateField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author duyvu
 */
public interface IManagement {

    public abstract boolean addEmployeeToList(Employee emp);

    public abstract void displayAllEmployees();

    public default <E extends Employee> void displayAllEmployees(Collection<E> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("[]");
        }

        Iterator<E> iter = list.iterator();
        while (iter.hasNext()) {
            iter.next().outputEmployee(true);
        }
    }

    public abstract void displayEmployeeByType(EmployeeType type);

    public abstract double calculateAverageSalaryNetOfCompany();

    public abstract float searchMaxSalaryNetEmployee();

    public abstract float searchMinSalaryNetEmployee();

    public abstract ArrayList<Employee> searchEmployeeByCondition(SearchingCondition type, String value);

    public abstract void sortEmployees();

    public abstract boolean removeEmployeeById(String id);

    public abstract boolean updateEmployeeById(String id);
    //    public abstract boolean updateEmployeeById(String id, UpdateField field) throws Exception;

    public abstract int findIndexEmployeeById(String id);
}
