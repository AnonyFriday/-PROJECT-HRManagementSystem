package asm.test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import asm.entities.Administrator;
import asm.entities.Business;
import asm.entities.Employee;
import asm.entities.HR;
import asm.enums.EmployeeType;
import asm.enums.SearchingCondition;
import asm.utils.validators.EmployeeValidator;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author duyvu
 */
public class TestMethod {

    private final HR hr;
    private final Faker dataFaker;
    private final int NO_TESTCASE;

    public TestMethod() {
        hr = new HR();
        dataFaker = new Faker(Locale.ENGLISH);
        NO_TESTCASE = 10;
    }

    public HR getHr() {
        return this.hr;
    }

    // Generate Fake Data Manually
    public void generateManualFakeData() {
        /**
         * Constructor and Default Fields
         */
//        SimpleDateFormat dateformat = new SimpleDateFormat(EmployeeValidator.DATE_FORMAT);
//        dateformat.setLenient(false);
//        Date dob;
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern(EmployeeValidator.DATE_FORMAT);
        Business emp1 = null;
        Business emp2 = null;
        Administrator emp3 = null;
        Administrator emp4 = null;

        try {

            // Test 1
//            dateformat.setLenient(false);
//            dob = dateformat.parse("29/02/2000");
            emp1 = new Business("VU KIM DUY_1", "HR222200", true,
                    LocalDate.parse("22/12/1999", dateformat), 100f, (byte) 5, 100f);
//            emp1.outputEmployee(true);

            // Test 2
            emp2 = new Business("VU KIM DUY_1", "HR2222", true,
                    LocalDate.parse("22/12/2009", dateformat), 200f, (byte) 5, 900f);
//            emp2.outputEmployee(true);

            // Test 3
//            dob = dateformat.parse("29/20/2000");
            emp3 = new Administrator("ARuoi", "HR222", false,
                    null, 000f, (byte) 0, 000000f);
//            emp3.outputEmployee(true);

            // Test 4
            emp4 = new Administrator("VU KIM DUY_1", "HR92983", false,
                    LocalDate.parse("22/03/2000", dateformat), 50f, (byte) 3, 200f);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        /**
         * Adding Data
         */
        hr.addEmployeeToList(emp1);
        hr.addEmployeeToList(emp2);
        hr.addEmployeeToList(emp3);
        hr.addEmployeeToList(emp4);
    }

    // Generate Random LocalDate
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

    // Generate Fake Data usinbg Faker Library
    public void generateFakerLibData() {
        int i = 0;
        Business emp1 = null;
        Administrator emp2 = null;
        Random random = new Random();

        while (i < NO_TESTCASE) {

            emp1 = new Business(
                    dataFaker.name().firstName(),
                    "HR" + random.nextInt(9999),
                    random.nextBoolean(),
                    TestMethod.generateRandomLocalDate(),
                    random.nextFloat(),
                    (byte) random.nextInt(Byte.MAX_VALUE),
                    random.nextFloat());

            emp2 = new Administrator(
                    dataFaker.name().firstName(),
                    "HR" + random.nextInt(9999),
                    random.nextBoolean(),
                    TestMethod.generateRandomLocalDate(),
                    random.nextFloat(),
                    (byte) random.nextInt(Byte.MAX_VALUE),
                    random.nextFloat());

            this.hr.addEmployeeToList(emp1);
            this.hr.addEmployeeToList(emp2);

            i += 2;
        }
    }

    /**
     * MAIN TESTING FUNCTION
     *
     * @param args
     */
    public static void main(String[] args) {

        TestMethod test = new TestMethod();

        /**
         * Adding Fake Data
         */
//        test.generateFakerLibData();
        test.generateManualFakeData();
        /**
         * Display All Data
         */
//        test.getHr().displayAllEmployees();

        /**
         * Display Specific Emp Type
         */
//        test.getHr().displayEmployeeByType(EmployeeType.ADMINISTRATION);
        /**
         * Display average salary net of the company
         */
//        System.out.println("Average Salary Net: " + test.getHr().calculateAverageSalaryNetOfCompany());
        /**
         * Display highest salary net of employees
         */
//        System.out.println("Highest Salary Net: " + test.getHr().searchMaxSalaryNetEmployee());
        /**
         * Search Employees on Name or on ID
         */
//        ArrayList<Employee> emps = test.getHr().searchEmployeeByCondition(SearchingCondition.NAME, "DUY");
//        test.getHr().displayAllEmployees(emps);
//        
//        ArrayList<Employee> emps1 = test.getHr().searchEmployeeByCondition(SearchingCondition.ID, "2222");
//        test.getHr().displayAllEmployees(emps1);
        /**
         * Sort Name Asc, If Name equals then sort Salary Net Desc
         */
//        test.getHr().sortEmployees();
//        test.getHr().displayAllEmployees();
    }
}
