/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm.utils.validators;

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
            throw new IllegalArgumentException("Subsidy cannot be less than 0.");
        }
    }
}
