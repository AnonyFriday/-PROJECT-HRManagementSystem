/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm.utils.validators;

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
            throw new IllegalArgumentException("Revenue cannot be less than 0.");
        }
    }
}
