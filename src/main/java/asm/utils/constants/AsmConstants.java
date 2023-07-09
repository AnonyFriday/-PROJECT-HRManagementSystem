/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm.utils.constants;

import asm.utils.validators.EmployeeValidator;

/**
 *
 * @author duyvu
 */
public final class AsmConstants implements AdministratorConstants, BusinessConstants, EmployeeConstants {

    // Force the class to not create + be inherited by another class
    private AsmConstants() {
    }

    // Fields Name
    public final static String NAME = "Name";
    public final static String ID = "Id";
    public final static String GENDER = "Gender";
    public final static String DOB = "Date Of Birth";
    public final static String SALARY_BASIC = "Salary Basic";
    public final static String SALARY_LEVEL = "Salary Level";
    public final static String SALARY_NET = "Salary Net";
    public final static String EXPERIENCE = "Experience";
    public final static String SUBSIDY = "Subsidy";
    public final static String REVENUE = "Revenue";

    // Classes Name
    public final static String EMPLOYEE = "Employee";
    public final static String BUSINESS = "Business";
    public final static String ADMINISTRATOR = "Administrator";

    // Datatype Name
    public final static String FLOAT = "Float";
    public final static String BYTE = "Byte";
    public final static String STRING = "String";
    public final static String INT = "Inteter";
    public final static String BOOLEAN = "Boolean";

    // Other Name
    public final static String CHOICE = "Choice";

    // =========================================================
    // Title for input fields
    // =========================================================
    public final static String TITLE_FIELD_INPUT(String field) {
        return "-Enter " + field.toUpperCase() + ": ";
    }

    // =========================================================
    // Generic Title for fields exceptions
    // =========================================================
    public final static String EX_FIELD_DATATYPE_ONLY(String field, String dataType) {
        return field.toUpperCase() + " accepts " + dataType.toUpperCase() + " type only. Please try again.";
    }

    public final static String EX_FIELD_VALUE_ONLY(String field, String value) {
        return field.toUpperCase() + " accepts " + value + " only. Please try again.";
    }

    public final static String EX_FIELD_CANNOT_EMPTY(String field) {
        return field.toUpperCase() + " cannot be empty. Please try again.";
    }

    public final static String EX_FIELD_BE_IN_FORMAT(String field, String format) {
        return field.toUpperCase() + " must be in the format " + format + ". Please try again.";
    }

    public final static <T extends Number> String EX_FIELD_CANNOT_LESS_THAN_VALUE(String field, T value) {
        return field.toUpperCase() + " cannot be less than " + value + ". Please try again.";
    }

    public final static <T extends Number> String EX_FIELD_CANNOT_GREATER_EQUALS_VALUE(String field, T value) {
        return field.toUpperCase() + " cannot be less than " + value + ". Please try again.";
    }

    public final static <T extends Number> String EX_FIELD_CANNOT_LESS_THAN_OR_EQUALS_VALUE(String field, T value) {
        return field.toUpperCase() + " cannot be less than " + value + " or equals to " + value + ". Please try again.";
    }

    public final static <T extends Number> String EX_FIELD_CANNOT_GREATER_THAN_OR_EQUALS_VALUE(String field, T value) {
        return field.toUpperCase() + " cannot be greater than " + value + " or equals to " + value + ". Please try again.";
    }

    public final static <T extends Number> String EX_STRING_CANNOT_EXCEED_NUM_CHARACTERS(String field, T numsCharacter) {
        return field + " cannot exceed " + numsCharacter + " characters. Please try again.";
    }

    // =========================================================
    // Literal String for specific usecase
    // =========================================================
    public final static <T extends Number> String EX_AGE_GREATER_THAN_OR_EQUALS_VALUE(T number) {
        return "Age has to be greater than or equals to " + number + ".";
    }
}
