/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm.utils.validators;

/**
 * Containing a set of general exceptions
 *
 * @author duyvu
 */
public interface Validator {

    /**
     * Validate that a number is greater than or equal to zero.
     *
     * @param	<T> the type of the number to validate
     * @param	num the number to validate
     * @param isEqualToZero
     * @return	true or false
     */
    public static <T extends Number> boolean isPositiveNumber(T num, boolean isEqualToZero) {
        return isEqualToZero ? num.doubleValue() >= 0.0f : num.doubleValue() > 0.0f;
    }

    /**
     * Validate if the string is null or empty
     *
     * @param str
     * @return true or false
     */
    public static boolean isNullStringOrEmptyString(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Validate if the object is null or not
     *
     * @param <T> the type of object to validate
     * @param obj the object to validate
     * @return true or false
     */
    public static <T extends Object> boolean isNullObject(T obj) {
        return obj == null;
    }

    /**
     * Validate if the num is in the bound or not (inclusive at both bounds)
     *
     * @param num
     * @param lowerBound
     * @param upperBound
     * @return true or false
     */
    public static boolean isNumberInBound(byte num, byte lowerBound, byte upperBound) {
        return lowerBound <= num && num <= upperBound;
    }

    public static boolean isNumberInBound(int num, int lowerBound, int upperBound) {
        return lowerBound <= num && num <= upperBound;
    }

    public static boolean isNumberInBound(short num, short lowerBound, short upperBound) {
        return lowerBound <= num && num <= upperBound;
    }

    public static boolean isNumberInBound(long num, long lowerBound, long upperBound) {
        return lowerBound <= num && num <= upperBound;
    }
}
