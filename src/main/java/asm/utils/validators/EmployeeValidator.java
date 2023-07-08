/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm.utils.validators;

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
            throw new IllegalArgumentException("ID cannot be empty");
        } else if (!str.matches("^HR\\d{5}$")) {
            throw new IllegalArgumentException("ID's format must be HR***** (* is a digit).");
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
            throw new IllegalArgumentException("Name cannot be empty.");
        } else if (!Validator.isNumberInBound(str.length(), 0, EmployeeValidator.MAX_NAME_SIZE)) {
            throw new IllegalArgumentException("Name cannot exceed " + EmployeeValidator.MAX_NAME_SIZE + " characters");
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
            throw new IllegalArgumentException("Experience cannot be less than or equals to 0.");
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
            throw new IllegalArgumentException("Gender only accept 'true' or 'false' value.");
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
            throw new IllegalArgumentException("DOB cannot be empty or null.");
        }
    }

    public static void validateAge(LocalDate dateOfBirth) throws IllegalArgumentException {
        LocalDate currentDate = LocalDate.now();
        byte age = (byte) Period.between(dateOfBirth, currentDate).getYears();

        if (age < 18) {
            throw new IllegalArgumentException("Age has to be greater than or equals to 18.");
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
            throw new IllegalArgumentException("Salary Basic cannot be less than or equals to 0.");
        }
    }
}
