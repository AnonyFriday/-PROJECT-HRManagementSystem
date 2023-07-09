/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm.utils.validators;

import asm.utils.constants.AsmConstants;

/**
 *
 * @author duyvu
 */
public abstract class BusinessValidator implements Validator {

    // =========================================================
    // Revenue 
    // =========================================================
    /**
     * Validate Revenue
     *
     * @param revenue
     * @throws IllegalArgumentException
     */
    public static void validateRevenue(float revenue) throws IllegalArgumentException {
        if (!Validator.isPositiveNumber(revenue, true)) {
            throw new IllegalArgumentException(AsmConstants.EX_FIELD_CANNOT_LESS_THAN_VALUE(AsmConstants.REVENUE, 0));
        }
    }
}
