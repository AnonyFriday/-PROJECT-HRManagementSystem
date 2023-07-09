/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm.entities;

import asm.enums.EmployeeType;
import asm.utils.constants.AsmConstants;
import asm.utils.validators.AdministratorValidator;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author duyvu
 */
public final class Administrator extends Employee {

    // =========================================================
    // Fields
    // =========================================================
    private float subsidy;

    // =========================================================
    // Constructor
    // =========================================================
    /**
     * Default Constructor
     */
    public Administrator() {
        super();
        this.subsidy = 0f;
    }

    /**
     * Parameterized Constructor
     *
     * @param subsidy
     * @param name
     * @param id
     * @param gender
     * @param dateOfBirth
     * @param salaryBasic
     * @param experience
     */
    public Administrator(String name, String id,
            boolean gender, LocalDate dateOfBirth,
            float salaryBasic, byte experience, float subsidy) {
        super(name, id, gender, dateOfBirth, salaryBasic, experience);
        this.subsidy = subsidy;
    }

    // =========================================================
    // Subsidy
    // =========================================================
    /**
     * Setting Subsidy Value
     *
     * @param subsidy
     * @throws java.lang.Exception
     */
    public void setSubsidy(float subsidy) throws Exception {
        try {
            AdministratorValidator.validateSubsidy(subsidy);
            this.subsidy = subsidy;
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        } catch (Exception ex) {
            // Logging
        }
    }

    /**
     * Getting subsidy value
     *
     * @return float
     */
    public float getSubsidy() {
        return subsidy;
    }

    // =========================================================
    // Salary Net
    // =========================================================
    /**
     * Compute Salary net
     *
     * Formula: salaryNet = salaryBasic * salaryLevel + subsidy
     */
    @Override
    public void computeSalaryNet() {
        this.salaryNet = this.getSalaryBasic() * this.getSalaryLevel() + this.getSubsidy();
    }

    // =========================================================
    // Input & Output
    // =========================================================
    /**
     * Input Administrator Information
     */
    @Override
    public void inputAllFieldsEmployee(boolean isUpdateMode) {
        System.out.println(AsmConstants.TITLE_ADMIN_INPUT_OUTPUT_ALL_FIELD);
        super.inputAllFieldsEmployee(isUpdateMode);

        Scanner sc = new Scanner(System.in);
        this.inputFieldSubsidy(sc);
    }

    /**
     * Input Subsidy
     *
     * @param sc
     */
    public void inputFieldSubsidy(Scanner sc) {
        do {
            try {
                System.out.print(AsmConstants.TITLE_FIELD_INPUT(AsmConstants.SUBSIDY));
                setSubsidy(Float.parseFloat(sc.nextLine()));
                break;
            } catch (NumberFormatException ex) {
                System.out.println(AsmConstants.EX_FIELD_DATATYPE_ONLY(AsmConstants.SUBSIDY, AsmConstants.FLOAT));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (true);
    }

    /**
     * Output Administrator information
     */
    @Override
    public void outputEmployee(boolean isComputeSalaryNet) {
        System.out.println(AsmConstants.TITLE_ADMIN_INPUT_OUTPUT_ALL_FIELD);
        super.outputEmployee(isComputeSalaryNet);

        System.out.printf("%-55s%-15s\n", " ", "Subsidy");
        System.out.printf("%-55s%-15.4f\n", " ", getSubsidy());
    }

    // =========================================================
    // Abstract Function From Parent
    // =========================================================
    /**
     * Get the current type of Administrator based on the current
     *
     * @return Administrator
     */
    @Override
    protected EmployeeType getType() {
        return EmployeeType.ADMINISTRATION;
    }
}
