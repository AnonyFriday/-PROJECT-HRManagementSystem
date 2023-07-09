/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm.utils.constants;

/**
 *
 * @author duyvu
 */
public final class AsmConstants implements AdministratorConstants, BusinessConstants {

    // Force the class to not create + be inherited by another class
    private AsmConstants() {
    }

    // =========================================================
    // Title for input fields
    // =========================================================
    public final static String TITLE_INPUT_SUBSIDY = "-Enter Subsidy: ";
    public final static String TITLE_INPUT_REVENUE = "-Enter Revenue: ";
    

    // =========================================================
    // Title for fields exceptions
    // =========================================================
    public final static String NUMBER_FORMAT_FLOAT_ONLY = "Accept Float type only. Please try again.";
}
