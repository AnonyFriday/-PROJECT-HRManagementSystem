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
public abstract class AdministratorValidator implements Validator {

    // =========================================================
    // Subsidy
    // =========================================================
    /**
     * Validate Subsidy
     *
     * @param subsidy
     * @throws IllegalArgumentException
     */
    public static void validateSubsidy(float subsidy) throws IllegalArgumentException {
        if (!Validator.isPositiveNumber(subsidy, true)) {
            throw new IllegalArgumentException(AsmConstants.EX_FIELD_CANNOT_LESS_THAN_VALUE(AsmConstants.SUBSIDY, 0));
        }
    }
}
