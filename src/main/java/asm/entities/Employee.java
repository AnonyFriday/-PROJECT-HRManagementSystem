/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm.entities;

import asm.enums.EmployeeType;
import asm.utils.constants.AsmConstants;
import asm.utils.validators.EmployeeValidator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author duyvu
 */
public abstract class Employee {

    // =========================================================
    // Fields
    // =========================================================
    protected String name;
    protected String id;
    protected boolean gender;

    protected LocalDate dateOfBirth; // Better parsing function for dd/MM/yyyy format
    //    protected Date dateOfBirth;

    protected float salaryBasic;
    protected byte salaryLevel;
    protected float salaryNet;
    protected byte experience;

    // =========================================================
    // Constructor 
    // =========================================================
    /**
     * Default Constructor
     */
    protected Employee() {
        this.name = "";
        this.id = "";
        this.gender = false;
        this.dateOfBirth = null;
        this.salaryBasic = 0f;
        this.salaryLevel = 1;
        this.salaryNet = 0f;
        this.experience = 0;
    }

    /**
     * Parameterized Constructor that can be used to create an object of
     * Employee
     *
     * @param name
     * @param id
     * @param gender
     * @param dateOfBirth
     * @param salaryBasic
     * @param experience
     */
    public Employee(String name, String id, boolean gender, LocalDate dateOfBirth, float salaryBasic, byte experience) {

        this.name = name;
        this.id = id;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.salaryBasic = salaryBasic;
        this.experience = experience;

        // Default Derivived Fields
        this.salaryLevel = 1;
        this.salaryNet = 0f;
    }

    // =========================================================
    // Salary Basic
    // =========================================================
    public float getSalaryBasic() {
        return salaryBasic;
    }

    public void setSalaryBasic(float salaryBasic) {
        try {
            EmployeeValidator.validateSalaryBasic(salaryBasic);
            this.salaryBasic = salaryBasic;
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        } catch (Exception ex) {
            // Logging
        }
    }

    // =========================================================
    // Name
    // =========================================================
    /**
     * Get Name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set name for the employee
     *
     * @param name
     * @throws Exception
     */
    public void setName(String name) throws Exception {
        try {
            EmployeeValidator.validateName(name);
            this.name = name;
        } catch (IllegalArgumentException ex) {
            throw new IllegalAccessException(ex.getMessage());
        } catch (Exception ex) {
            // Logger
        }
    }

    // =========================================================
    // Salary Level
    // =========================================================
    /**
     * Get the salaryLevel based on the computation with experience
     *
     * SalaryLevel default is 1. For every 5 years, the level has been increased
     * to 1
     *
     * @return byte
     */
    public byte getSalaryLevel() {
        byte defaultSalaryLevel = 1;
        defaultSalaryLevel += this.experience / 5;
        return defaultSalaryLevel;
    }

    // =========================================================
    // Salary Net
    // =========================================================
    /**
     * Get the salaryNet
     *
     * @return float
     */
    public float getSalaryNet() {
        return this.salaryNet;
    }

    /**
     * Compute Salary Net
     *
     * Formula: salaryNBet = salaryBasic * salaryLevel
     */
    public void computeSalaryNet() {
        this.salaryNet = this.getSalaryBasic() * this.getSalaryLevel();
    }

    // =========================================================
    // Experience 
    // =========================================================
    /**
     * Setting Experience Value
     *
     * @param experience
     * @throws NumberFormatException
     * @throws java.lang.Exception
     */
    public void setExperience(byte experience) throws Exception {
        try {
            EmployeeValidator.validateExperience(experience);
            this.experience = experience;
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        } catch (Exception ex) {
            // Logging
        }
    }

    // =========================================================
    // ID   
    // =========================================================
    /**
     * Setting ID Value
     *
     * @param id
     * @throws NumberFormatException
     * @throws java.lang.Exception
     */
    public void setId(String id) throws Exception {
        try {
            EmployeeValidator.validateId(id);
            this.id = id;
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        } catch (Exception ex) {
            // Logging
        }
    }

    /**
     * Get ID
     *
     * @return String
     */
    public String getId() {
        return this.id;
    }

    // =========================================================
    // Gender (error)
    // =========================================================
    /**
     * Get the "female" or "male" bases on true false value
     *
     * @return
     */
    public String getGender() {
        if (this.gender == false) {
            return "female";
        } else {
            return "male";
        }
    }

    /**
     * Set the
     *
     * @param gender
     * @throws java.lang.Exception
     */
    public void setGender(String gender) throws Exception {
        try {
            EmployeeValidator.validateGender(gender);
            this.gender = Boolean.parseBoolean(gender);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        } catch (Exception ex) {
            // Logging
        }
    }

//    // =========================================================
//    // Date Of Birth (Using Date class)
//    // ========================================================= 
//    /**
//     * Setting Date Of Birth Value
//     *
//     * @param dateOfBirth
//     * @throws Exception
//     */
//    public void setDateOfBirth(String dateOfBirth) throws Exception {
//
//        DateFormat df = new SimpleDateFormat(EmployeeValidator.DATE_FORMAT);
//
//        try {
//            EmployeeValidator.validateDateOfBirth(dateOfBirth);         // Validate Null String
//            df.setLenient(false);                         // Validate the Date format
//            this.dateOfBirth = df.parse(dateOfBirth);
//        } catch (ParseException ex) {
//            throw new ParseException("DOB is not in the correct date format", 0);
//        } catch (IllegalArgumentException ex) {
//            throw new IllegalArgumentException(ex.getMessage());
//        } catch (Exception ex) {
//            // Logging
//        }
//    }
//    /**
//     * Get the DateOfBirth value
//     *
//     * @return
//     */
//    public String getDateOfBirth() {
//        if (this.dateOfBirth == null) {
//            return "NULL";
//        }
//
//        DateFormat df = new SimpleDateFormat(EmployeeValidator.DATE_FORMAT);
//        return df.format(this.dateOfBirth);
//    }
    // =========================================================
    // Date Of Birth (Using DateLocal class)
    // ========================================================= 
    /**
     * Setting Date Of Birth Value
     *
     * @param dateOfBirth
     * @throws Exception
     */
    public void setDateOfBirth(String dateOfBirth) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EmployeeValidator.DATE_FORMAT);
        try {
            // Validate Both Age and DOB before attaching to the field
            EmployeeValidator.validateDateOfBirth(dateOfBirth);
            LocalDate formattedDOB = LocalDate.parse(dateOfBirth, formatter);
            EmployeeValidator.validateAge(formattedDOB);

            this.dateOfBirth = formattedDOB;
        } catch (DateTimeParseException ex) {
            throw new DateTimeParseException(AsmConstants.EX_FIELD_BE_IN_FORMAT(AsmConstants.DOB, EmployeeValidator.DATE_FORMAT), dateOfBirth, 0);
        } catch (IllegalArgumentException ex) {
            throw new IllegalAccessException(ex.getMessage());
        } catch (Exception ex) {
            // Logging
        }
    }

    /**
     * Get the DateOfBirth value
     *
     * Logging Exception for Internal Error occurs
     *
     * @return
     */
    public String getDateOfBirth() {
        if (this.dateOfBirth == null) {
            return "NULL";
        }

        try {
            return this.dateOfBirth.format(DateTimeFormatter.ofPattern(EmployeeValidator.DATE_FORMAT));
        } catch (DateTimeParseException | IllegalArgumentException ex) {
            // Logging
            return "";
        }
    }

    // =========================================================
    // Input & Output
    // =========================================================
    /**
     * Input Employee based on those basic fields
     *
     * Update Mode:
     * False（Input whole Fields)
     * True (Input whole fields EXCEPT id in case of updating information)
     *
     * @param isUpdateMode
     */
    public void inputAllFieldsEmployee(boolean isUpdateMode) {
        Scanner sc = new Scanner(System.in);

        if (!isUpdateMode) {
            this.inputFieldId(sc);
        }
        this.inputFieldName(sc);
        this.inputFieldGender(sc);
        this.inputFieldDateOfBirth(sc);
        this.inputFieldSalaryBasic(sc);
        this.inputFieldExperience(sc);
    }

    /**
     * Input Id
     *
     * @param sc
     */
    public void inputFieldId(Scanner sc) {
        do {
            try {
                System.out.print(AsmConstants.TITLE_FIELD_INPUT(AsmConstants.ID));
                setId(sc.nextLine());
                break;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (true);
    }

    /**
     * Input Name
     *
     * @param sc
     */
    public void inputFieldName(Scanner sc) {
        do {
            try {
                System.out.print(AsmConstants.TITLE_FIELD_INPUT(AsmConstants.NAME));
                setName(sc.nextLine());
                break;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (true);
    }

    /**
     * Input Date Of Birth
     *
     * @param sc
     */
    public void inputFieldDateOfBirth(Scanner sc) {
        do {
            try {
                System.out.print(AsmConstants.TITLE_FIELD_INPUT(AsmConstants.DOB));
                setDateOfBirth(sc.nextLine());
                break;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (true);
    }

    /**
     * Input Gender
     *
     * @param sc
     */
    public void inputFieldGender(Scanner sc) {
        do {
            try {
                System.out.print(AsmConstants.TITLE_FIELD_INPUT(AsmConstants.GENDER));
                setGender(sc.nextLine());
                break;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (true);
    }

    /**
     * Input Experience
     *
     * @param sc
     */
    public void inputFieldExperience(Scanner sc) {
        do {
            try {
                System.out.print(AsmConstants.TITLE_FIELD_INPUT(AsmConstants.EXPERIENCE));
                setExperience(Byte.parseByte(sc.nextLine()));
                break;
            } catch (NumberFormatException ex) {
                System.out.println(AsmConstants.EX_FIELD_DATATYPE_ONLY(AsmConstants.EXPERIENCE, AsmConstants.BYTE));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (true);
    }

    /**
     * Input Salary Basic
     *
     * @param sc
     */
    public void inputFieldSalaryBasic(Scanner sc) {
        do {
            try {
                System.out.print(AsmConstants.TITLE_FIELD_INPUT(AsmConstants.SALARY_BASIC));
                setSalaryBasic(Float.parseFloat(sc.nextLine()));
                break;
            } catch (NumberFormatException ex) {
                System.out.println(AsmConstants.EX_FIELD_DATATYPE_ONLY(AsmConstants.SALARY_BASIC, AsmConstants.FLOAT));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (true);
    }

    /**
     * Output Employee based on those basic fields
     *
     * Lazy loading computing SalaryNet when needed
     *
     * @param isComputeSalaryNet
     */
    public void outputEmployee(boolean isComputeSalaryNet) {
        if (isComputeSalaryNet) {
            this.computeSalaryNet();
        }

        System.out.printf("%-10s%-15s%-10s%-15s%-5s%-15s%-15s%-15s\n", "ID", "Name", "Gender", "DOB", "Exp", "S.Basic", "S.Level", "S.Net");
        System.out.printf("%-10s%-15s%-10s%-15s%-5s%-15.4f%-15d%-15.4f\n", getId(), getName(), getGender(), getDateOfBirth(), this.experience, getSalaryBasic(), getSalaryLevel(), getSalaryNet());
    }

    // =========================================================
    // Abstract Functions
    // =========================================================
    /**
     * Get the Type of the class based on Enum EmployeeType
     *
     * @return
     */
    abstract protected EmployeeType getType();
}
