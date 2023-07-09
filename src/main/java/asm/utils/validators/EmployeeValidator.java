/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm.utils.validators;

import asm.utils.constants.AsmConstants;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyvu
 */
public abstract class EmployeeValidator implements Validator {

    // =========================================================
    // Fields
    // =========================================================
    private final static byte MAX_NAME_SIZE = 50;
    public final static String DATE_FORMAT = "dd/MM/yyyy";
    public final static String ID_FORMAT = "HR***** (* is a digit)";
    public final static String GENDER_FORMAT = "'true' or 'fasle'";

    // =========================================================
    // Experience
    // =========================================================
    // =========================================================
    // ID
    // =========================================================
    /**
     * Validate ID
     *
     * @param str
     * @throws IllegalArgumentException
     */
    public static void validateId(String str) throws IllegalArgumentException {
        if (Validator.isNullStringOrEmptyString(str)) {
            throw new IllegalArgumentException(AsmConstants.EX_FIELD_CANNOT_EMPTY(AsmConstants.ID));
        } else if (!str.matches("^HR\\d{5}$")) {
            throw new IllegalArgumentException(AsmConstants.EX_FIELD_BE_IN_FORMAT(AsmConstants.ID, EmployeeValidator.ID_FORMAT));
        }
    }

    // =========================================================
    // Name
    // =========================================================
    /**
     * Validate Name
     *
     * @param str
     * @throws IllegalArgumentException
     */
    public static void validateName(String str) throws IllegalArgumentException {
        if (Validator.isNullStringOrEmptyString(str)) {
            throw new IllegalArgumentException(AsmConstants.EX_FIELD_CANNOT_EMPTY(AsmConstants.NAME));
        } else if (!Validator.isNumberInBound(str.length(), 0, EmployeeValidator.MAX_NAME_SIZE)) {
            throw new IllegalArgumentException(AsmConstants.EX_STRING_CANNOT_EXCEED_NUM_CHARACTERS(AsmConstants.NAME, EmployeeValidator.MAX_NAME_SIZE));
        }
    }

    /**
     * Validate Experience
     *
     * @param exp
     * @throws IllegalArgumentException
     * @throws NumberFormatException
     */
    public static void validateExperience(double exp) throws IllegalArgumentException {
        if (!Validator.isPositiveNumber(exp, false)) {
            throw new IllegalArgumentException(AsmConstants.EX_FIELD_CANNOT_LESS_THAN_OR_EQUALS_VALUE(AsmConstants.EXPERIENCE, 0));
        }
    }

    // =========================================================
    // Gender
    // =========================================================
    /**
     * Validate Gender
     *
     * @param value
     * @throws IllegalArgumentException
     */
    public static void validateGender(String value) throws IllegalArgumentException {
        if (!value.matches("^(true|false)$")) {
            throw new IllegalArgumentException(AsmConstants.EX_FIELD_VALUE_ONLY(AsmConstants.GENDER, EmployeeValidator.GENDER_FORMAT));
        }
    }

    // =========================================================
    // Date Of Birth
    // =========================================================
    /**
     * Validate Date Of Birth
     *
     * @param dateOfBirth
     * @throws IllegalArgumentException
     */
    public static void validateDateOfBirth(String dateOfBirth) throws IllegalArgumentException {
        if (Validator.isNullStringOrEmptyString(dateOfBirth)) {
            throw new IllegalArgumentException(AsmConstants.EX_FIELD_CANNOT_EMPTY(AsmConstants.DOB));
        }
    }

    public static void validateAge(LocalDate dateOfBirth) throws IllegalArgumentException {
        LocalDate currentDate = LocalDate.now();
        byte age = (byte) Period.between(dateOfBirth, currentDate).getYears();

        if (age < 18) {
            throw new IllegalArgumentException(AsmConstants.EX_AGE_GREATER_THAN_OR_EQUALS_VALUE(18));
        }
    }

    // =========================================================
    // Salary Basic
    // =========================================================
    /**
     * Validate Salary Basic
     *
     * @param salary
     * @throws IllegalArgumentException
     */
    public static void validateSalaryBasic(float salary) throws IllegalArgumentException {
        if (!Validator.isPositiveNumber(salary, false)) {
            throw new IllegalArgumentException(AsmConstants.EX_FIELD_CANNOT_LESS_THAN_OR_EQUALS_VALUE(AsmConstants.SALARY_BASIC, 0));
        }
    }
}
