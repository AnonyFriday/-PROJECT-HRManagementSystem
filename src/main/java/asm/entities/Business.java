/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm.entities;

import asm.enums.EmployeeType;
import asm.utils.validators.BusinessValidator;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author duyvu
 */
public final class Business extends Employee {

    // =========================================================
    // Fields
    // =========================================================
    private float revenue;
    private float bonus;

    // =========================================================
    // Constructor 
    // =========================================================
    /**
     * Default Constructor
     */
    public Business() {
        super();
        this.revenue = 0f;
        this.bonus = 0f;
    }

    /**
     * Constructor with parameters
     *
     * @param revenue
     * @param name
     * @param id
     * @param gender
     * @param dateOfBirth
     * @param salaryBasic
     * @param experience
     */
    public Business(String name, String id,
            boolean gender,
            LocalDate dateOfBirth, float salaryBasic,
            byte experience, float revenue) {
        
        super(name, id, gender, dateOfBirth, salaryBasic, experience);
        this.revenue = revenue;

        // Default fields
        this.bonus = 0f;
    }

    // =========================================================
    // Revenue 
    // =========================================================
    /**
     * Get the revenue of the Business employee
     *
     * @return
     */
    public float getRevenue() {
        return this.revenue;
    }

    /**
     * Setting Revenue Value
     *
     * @param revenue
     * @throws java.lang.Exception
     */
    public void setRevenue(float revenue) throws Exception {
        try {
            BusinessValidator.validateRevenue(revenue);
            this.revenue = revenue;
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        } catch (Exception ex) {
            // Logging
        }
    }

    // =========================================================
    // Bonus 
    // =========================================================
    /**
     * Get the Bonus based on rank of Revenue
     *
     * @return float
     */
    public float getBonus() {
        if (this.getRevenue() < 5_000_000f) {
            return 0f;
        } else if (this.getRevenue() < 10_000_000f) {
            return 0.05f;
        } else if (this.getRevenue() < 20_000_000f) {
            return 0.1f;
        } else {
            return 0.2f;
        }
    }

    // =========================================================
    // Salary Net
    // =========================================================
    /**
     * Compute Salary Net
     *
     * Formula: salaryNBet = salaryBasic * salaryLevel + revenue + bonus
     */
    @Override
    public void computeSalaryNet() {
        this.salaryNet = this.getSalaryBasic() * this.getSalaryLevel() + this.getRevenue() * this.getBonus();
    }

    // =========================================================
    // Input & Output
    // =========================================================
    /**
     * Input Employee
     */
    @Override
    public void inputAllFieldsEmployee(boolean isUpdateMode) {
        System.out.println("\t------------(Business)-----------------");
        super.inputAllFieldsEmployee(isUpdateMode);
        
        Scanner sc = new Scanner(System.in);
        this.inputFieldRevenue(sc);
    }

    /**
     * Input Revenue
     *
     * @param sc
     */
    public void inputFieldRevenue(Scanner sc) {
        do {
            try {
                System.out.print("-Enter Revenue: ");
                setRevenue(Float.parseFloat(sc.nextLine()));
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Revenue's Float type. Please try again.");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (true);
    }

    /**
     * Output Business Employee based on those basic fields
     */
    @Override
    public void outputEmployee(boolean isComputeSalaryNet) {
        System.out.println("\t------------(Business)-----------------");
        super.outputEmployee(isComputeSalaryNet);
        
        System.out.printf("%-55s%-15s%-15s\n", " ", "Revenue", "Bonus");
        System.out.printf("%-55s%-15.4f%-15.4f\n", " ", getRevenue(), getBonus());
    }

    // =========================================================
    // Abstract Function From Parent
    // =========================================================
    /**
     * Get the current type of Business based on the current
     *
     * @return Administrator
     */
    @Override
    protected EmployeeType getType() {
        return EmployeeType.BUSINESS;
    }
}
