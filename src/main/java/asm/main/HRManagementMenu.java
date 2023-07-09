/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm.main;

import asm.entities.Administrator;
import asm.entities.Business;
import asm.entities.Employee;
import asm.entities.HR;
import asm.entities.IManagement;
import asm.enums.EmployeeType;
import asm.enums.SearchingCondition;
import asm.utils.constants.AsmConstants;
import asm.utils.validators.MenuValidator;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Finished:
 * - Option 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
 * - Reformat Console UI
 *
 * Unfinished:
 * - Advanced option 10 for updating selective field
 * - Constant String
 * - Better Comment
 * - Better Documentation
 *
 * @author duyvu
 */
public final class HRManagementMenu {

    // =========================================================
    // Fields
    // =========================================================
    private final Scanner sc;

    // Dependencies
    private final IManagement managementDept;

    // =========================================================
    // Constructor 
    // =========================================================
    public HRManagementMenu(IManagement managementDept) {
        this.managementDept = managementDept;
        sc = new Scanner(System.in);
    }

    // =========================================================
    // Main Menu
    // =========================================================
    /**
     * Display Menu
     */
    public void displayMenu() {
        System.out.println("------------------------HR Management------------------------");
        System.out.println("=============================================================");
        System.out.println("\t\t 1. Add Employee.");
        System.out.println("\t\t 2. Output EMPLOYEEs.");
        System.out.println("\t\t 3. Output EMPLOYEE on TYPE.");
        System.out.println("\t\t 4. Output AVERAGE SALARY NET of the COMPANY.");
        System.out.println("\t\t 5. Output MAX employee's SALARY NET.");
        System.out.println("\t\t 6. Output MIN employee's SALARY NET.");
        System.out.println("\t\t 7. Find EMPLOYEE on ID or NAME.");
        System.out.println("\t\t 8. Sort EMPLOYEEs ascendingly on NAME (Sort descendingly on SALARY NET of SAME NAME.");
        System.out.println("\t\t 9. Remove EMPLOYEE on ID.");
        System.out.println("\t\t10. Update EMPLOYEE on ID.");
        System.out.println("\t\t11. [TESTING] Generating DEFAULT DATA");
        System.out.println("\t\t 0. EXIT.");
        System.out.println("=============================================================");
    }

    /**
     * Main Menu Controller
     */
    public void menuController() {

        byte choice = -1;

        do {
            displayMenu();

            System.out.print(AsmConstants.TITLE_FIELD_INPUT(AsmConstants.CHOICE));
            try {

                choice = Byte.parseByte(sc.nextLine());

                switch (choice) {
                    case 1: {
                        this.optionAddEmployee();
                        break;
                    }
                    case 2: {
                        this.optionDisplayEmployees();
                        break;
                    }
                    case 3: {
                        this.optionDisplayEmployeesByType();
                        break;
                    }
                    case 4: {
                        this.optionDisplayAverageCompanySalaryNet();
                        break;
                    }
                    case 5: {
                        this.optionDisplayHighestSalaryNet();
                        break;
                    }
                    case 6: {
                        this.optionDisplayLowestSalaryNet();
                        break;
                    }
                    case 7: {
                        this.optionDisplayEmployeeOnNameOrID();
                        break;
                    }
                    case 8: {
                        this.optionDisplaySortedEmployeeListOnNameAndSalaryNet();
                        break;
                    }
                    case 9: {
                        this.optionRemoveEmployeeOnId();
                        break;
                    }
                    case 10: {
                        this.optionUpdateEmployeeOnId();
                        break;
                    }
                    case 11: {
                        optionGeneratingDefaultData();
                        break;
                    }
                    case 0: {
                        System.out.println("Program Exit.");
                        break;
                    }
                    default: {
                        System.out.println("Please choose options from 1 - 10 (0 exit).");
                        break;
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter number from 1 - 10 (0 exit) only.");
            }

            // Exit the program when enter 0
        } while (choice != 0);

        // Release the input stream to Garbage Collector
        sc.close();
    }

    // =========================================================
    // Options Method
    // =========================================================
    /**
     * [1]
     * Option Add Employee To the list
     */
    public void optionAddEmployee() {
        byte empChoice = -1;
        Employee emp = null;

        do {
            try {
                // Handle if user do not type number
                System.out.print("-Enter your employee's type (1 - Business) (2 - Administrator) (0 - Exit): ");
                empChoice = Byte.parseByte(sc.nextLine());

                switch (empChoice) {
                    case 1: {
                        // If adding unsuccesfull, then asking to input again
                        emp = new Business();
                        emp.inputAllFieldsEmployee(false);
                        break;
                    }
                    case 2: {
                        // If adding unsuccesfull, then asking to input again
                        emp = new Administrator();
                        emp.inputAllFieldsEmployee(false);
                        break;
                    }
                    case 0: {
                        System.out.println("Exit Adding Employee.");
                        break;
                    }
                    default: {
                        throw new Exception("Please choose options from 1 - 2 (0 exit).");
                    }
                }

                // Handle Duplication and adding employee to the list
                if (empChoice == 1 || empChoice == 2) {
                    MenuValidator.validateAddEmployeeToList(emp);

                    if (!this.managementDept.addEmployeeToList(emp)) {
                        throw new Exception("Employee [" + emp.getId() + "] duplicated. Please try again.");
                    }
                }

            } catch (NumberFormatException ex) {
                System.out.println("Please enter number only.");
            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                // Logging
            }
        } while (empChoice != 0);
    }

    /**
     * [2]
     * Option Display a list of employees
     */
    public void optionDisplayEmployees() {
        managementDept.displayAllEmployees();
    }

    /**
     * [3]
     * Option Display a list of specific employee's type
     */
    public void optionDisplayEmployeesByType() {
        byte empChoice = -1;

        do {
            try {
                // Handle if user do not type number
                System.out.print("-Enter your employee's type (1 - Business) (2 - Administrator) (0 - Exit): ");
                empChoice = Byte.parseByte(sc.nextLine());

                switch (empChoice) {
                    case 1: {
                        // If adding unsuccesfull, then asking to input again
                        managementDept.displayEmployeeByType(EmployeeType.BUSINESS);
                        break;
                    }
                    case 2: {
                        // If adding unsuccesfull, then asking to input again
                        managementDept.displayEmployeeByType(EmployeeType.ADMINISTRATION);
                        break;
                    }
                    case 0: {
                        System.out.println("Exit Displaying Employee.");
                        break;
                    }
                    default: {
                        throw new Exception("Please choose options from 1 - 2 (0 exit).");
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter number only.");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                // Logging 
            }
        } while (empChoice != 0);
    }

    /**
     * [4]
     * Option display average company salary net
     */
    public void optionDisplayAverageCompanySalaryNet() {
        double salary = this.managementDept.calculateAverageSalaryNetOfCompany();
        System.out.printf("Average Salary Net of the Company: %.4f%n", salary);
    }

    /**
     * [5]
     * Option display highest salary net
     */
    public void optionDisplayHighestSalaryNet() {
        float highestSalaryNet = this.managementDept.searchMaxSalaryNetEmployee();
        System.out.printf("Highest Salary Net of the Company: %.4f%n", highestSalaryNet);
    }

    /**
     * [6]
     * Option display lowest salary net
     */
    public void optionDisplayLowestSalaryNet() {
        float lowestSalaryNet = this.managementDept.searchMinSalaryNetEmployee();
        System.out.printf("Lowest Salary Net of the Company: %.4f%n", lowestSalaryNet);
    }

    /**
     * [7]
     * Option find employee on ID or name
     */
    public void optionDisplayEmployeeOnNameOrID() {
        byte conditionChoice = -1;
        String keyword = null;
        ArrayList<Employee> founds = new ArrayList<>();

        do {
            try {
                // Handle condition choice if user do not type number
                System.out.print("-Find your employee on (1 - Id) (2 - Name) (0 - Exit): ");
                conditionChoice = Byte.parseByte(sc.nextLine());

                // Handle condition choice if user do not type number
                if (conditionChoice == 1 || conditionChoice == 2) {
                    System.out.print("-Please type " + (conditionChoice == 1
                            ? "Id: "
                            : "Name: "));

                    keyword = sc.nextLine();
                    MenuValidator.validateStringKeyword(keyword);
                }

                // Choosing Choice
                switch (conditionChoice) {
                    case 1: {
                        // Return employee with the given ID
                        founds = this.managementDept.searchEmployeeByCondition(SearchingCondition.ID, keyword);
                        break;
                    }
                    case 2: {
                        // Return the list of employee with the given Name
                        // Could be matched the same name pattern
                        founds = this.managementDept.searchEmployeeByCondition(SearchingCondition.NAME, keyword);
                        break;
                    }
                    case 0: {
                        System.out.println("Exiting Searching Employee.");
                        break;
                    }
                    default: {
                        // Skip the remaining code in the loop and start the next iteration
                        throw new Exception("Please choose options from 1 - 2 (0 exit).");
                    }
                }

                // Display the list of foundEmployees
                if (conditionChoice == 1 || conditionChoice == 2) {
                    if (founds.isEmpty()) {
                        System.out.printf("No employees found for the keyword \"%s\"%n", keyword);
                    } else {
                        this.managementDept.displayAllEmployees(founds);
                    }
                }

            } catch (NumberFormatException ex) {
                System.out.println("Please enter number only.");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                // Logging
            }
        } while (conditionChoice != 0);
    }

    /**
     * [8]
     * Option display sorted list of employee based on Name in ascending order
     *
     * If having the same name, sorting salary net in descending order
     */
    public void optionDisplaySortedEmployeeListOnNameAndSalaryNet() {
        this.managementDept.sortEmployees();
        System.out.println("The list has been sorted.");
    }

    /**
     * [9]
     * Option to delete employee based on given ID
     */
    public void optionRemoveEmployeeOnId() {
        String keywordId = null;
        String exitCode = "0";

        do {
            try {
                // Choose Remove Mode
                System.out.print("-Enter employee's id to remove (format: HR*****) (0 - Exit): ");
                keywordId = sc.nextLine();
                MenuValidator.validateStringKeyword(keywordId);

                // Terminate the loop if user don't want to continue      
                if (keywordId.equals(exitCode)) {
                    System.out.println("Exiting Removing Employee.");
                    break;
                }

                // Guarding remove employee based on keywordId
                if (this.managementDept.removeEmployeeById(keywordId)) {
                    System.out.println("Remove employee [" + keywordId + "] sucessfully.");
                    break;
                } else {
                    System.out.println("Employee [" + keywordId + "] is not found. Please try again.");
                }

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                // Logging
            }
        } while (true);
    }

    /**
     * [10]
     * Option to Update Information based on Id
     */
    public void optionUpdateEmployeeOnId() {
        String keywordId = null;
        String exitCode = "0";

        do {
            try {
                // Choose Update Mode
                System.out.print("-Enter employee's id to update (format: HR*****) (0 - Exit): ");
                keywordId = sc.nextLine();
                MenuValidator.validateStringKeyword(keywordId);

                // Exiting Update
                if (keywordId.equals(exitCode)) {
                    System.out.println("Exiting Updating Employee.");
                    break;
                }

                // Update all fields
                if (this.managementDept.updateEmployeeById(keywordId)) {
                    System.out.println("Update employee [" + keywordId + "] sucessfully.");
                    break;
                } else {
                    throw new Exception("Employee [" + keywordId + "] is not found. Please try again.");
                }

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                // Logging
            }
        } while (true);
    }

    /**
     * [10] [ERROR]
     * Option to Update Chosen Fields based on Id
     *
     * Update all fields Mode
     * Update specific field
     *
     */
//    public void optionUpdateEmployeeOnId() {
//        int updateOption = -1;
//        int fieldOption = -1;
//        String keywordId = null;
//
//        do {
//            try {
//                // Choose Update Mode
//                System.out.print("-Update Information Type (1 - All) (2 - Specific Field) (0 - Exit): ");
//                updateOption = Byte.parseByte(sc.nextLine());
//
//                // Exiting Update
//                if (updateOption == 0) {
//                    System.out.println("Exiting employee update.");
//                    break;
//                }
//
//                // Type the keywordId and handle the null value
//                if (updateOption == 1 || updateOption == 2) {
//                    System.out.print("-Enter employee's id to remove (format: HR*****) (0 - Exit): ");
//                    keywordId = sc.nextLine();
//                    MenuValidator.validateStringKeyword(keywordId);
//
//
//                }
//
//                switch (updateOption) {
//                    case 1: {
//                        // Update all fields
//                        if (this.managementDept.updateEmployeeById(keywordId, null)) {
//                            System.out.println("Update Sucessfully.");
//                            break;
//                        } else {
//                            throw new Exception("Employee [" + keywordId + "] is not found. Please try again.");
//                        }
//                    }
////                    case 2: {
////                        // Choose field to update
////                        System.out.println("\t------------(Fields to Update)------------");
////                        System.out.println("Employee options        : (1 - Name) (2 - Gender) (3 - Date Of Birth)");
////                        System.out.println("                          (4 - Salary Basic) (5 - Experience)");
////                        System.out.println("Business options        : (6 - Revenue)");
////                        System.out.println("Administrator options   : (7 - Subsidy)");
////                        System.out.println("(0 - Exit)");
////                        System.out.print("-Choose field to update: ");
////
////                        fieldOption = Byte.parseByte(sc.nextLine());
////
////                        // Exiting Field Update
////                        if (fieldOption == 0) {
////                            System.out.println("Exiting employee Field Update.");
////                            break;
////                        }
////
////                        if (this.managementDept.updateEmployeeById(keywordId, UpdateField.fromValue(fieldOption))) {
////                            System.out.println("Update Sucessfully.");
////                            break;
////                        } else {
////                            throw new Exception("Employee [" + keywordId + "] is not found. Please try again.");
////                        }
////                    }
//                    
//                    case 0: {
//                        
//                    }
//                    default: {
//                        // Skip the remaining code in the loop and start the next iteration
//                        throw new Exception("Please choose options from 1 - 2 (0 exit).");
//                    }
//                }
//
//            } catch (NumberFormatException ex) {
//                System.out.println("Please enter number only.");
//            } catch (IllegalArgumentException ex) {
//                System.out.println(ex.getMessage());
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//                // Logging
//            }
//        } while (updateOption != 0);
//    }
    // =========================================================
    // Testing Purpose
    // =========================================================
    /**
     * [TESTING PURPOSE] Options for auto generated data
     */
    public void optionGeneratingDefaultData() {
        int i = 0;
        Business emp1 = null;
        Administrator emp2 = null;
        Random random = new Random();
        Faker dataFaker = new Faker();
        final int NO_TESTCASE = 10;

        while (i < NO_TESTCASE) {

            emp1 = new Business(
                    //                    dataFaker.name().firstName(),
                    "VU KIM DUY",
                    "HR" + (random.nextInt(90000) + 10000),
                    random.nextBoolean(),
                    generateRandomLocalDate(),
                    random.nextFloat(),
                    (byte) random.nextInt(Byte.MAX_VALUE),
                    random.nextFloat()
            );

            emp2 = new Administrator(
                    dataFaker.name().firstName(),
                    "HR" + (random.nextInt(90000) + 10000),
                    random.nextBoolean(),
                    generateRandomLocalDate(),
                    random.nextFloat(),
                    (byte) random.nextInt(Byte.MAX_VALUE),
                    random.nextFloat());

            this.managementDept.addEmployeeToList(emp1);
            this.managementDept.addEmployeeToList(emp2);

            i += 2;
        }
    }

    /**
     * [TESTING PURPOSE] Generate Random LocalDate
     */
    public static LocalDate generateRandomLocalDate() {
        Random random = new Random();
        int year = random.nextInt(Year.now().getValue() - 1900) + 1900;
        int month = random.nextInt(12) + 1;

        // Handle 31,30,29,28 days
        int maxDay = LocalDate.of(year, month, 1).lengthOfMonth();
        int day = random.nextInt(maxDay) + 1;

        LocalDate randomDate = LocalDate.of(year, month, day);
        return randomDate;
    }

    // =========================================================
    // Entry Point
    // =========================================================
    /**
     * Entry point of the program
     *
     * @param args
     */
    public static void main(String[] args) {
        HR managementHr = new HR();
        HRManagementMenu tester = new HRManagementMenu(managementHr);
        tester.menuController();
    }
}
