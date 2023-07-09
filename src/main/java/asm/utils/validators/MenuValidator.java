/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm.utils.validators;

import asm.entities.Employee;
import asm.utils.constants.AsmConstants;

/**
 *
 * @author duyvu
 */
public abstract class MenuValidator implements Validator {

    // =========================================================
    // Adding Employee To List
    // =========================================================
    public static final void validateAddEmployeeToList(Employee emp) throws NullPointerException {
        if (Validator.isNullObject(emp)) {
            throw new NullPointerException(AsmConstants.EX_FIELD_CANNOT_EMPTY(AsmConstants.EMPLOYEE));
        }
    }

    // =========================================================
    // Find Employee on String Keyword (ID, NAME)
    // Remove Employee on String Keyword (ID)
    // Update Emplyoee on String Keyword (ID)
    // =========================================================
    public static final void validateStringKeyword(String keyword) throws IllegalArgumentException {
        if (Validator.isNullStringOrEmptyString(keyword)) {
            throw new IllegalArgumentException(AsmConstants.EX_FIELD_CANNOT_EMPTY(keyword));
        }
    }
}
