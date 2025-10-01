package co.uk.lset.payrollmanagement;

import java.util.*;

public class PayrollService {
    //private static ArrayList<Employee> employees = new ArrayList<>();
    private static HashMap<Integer, Employee> employeesById= new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    private static final String BASIC_SALARY = "basic salary";
    private static final String BONUS = "bonus";
    private static final String ALLOWANCE = "allowance";
    private static final String DEDUCTION = "deduction";
    private static final String HOURS_WORKED = "hours worked";

    public static void main(String[] args) throws EmployeeNotFoundException {
        boolean isRunning = true;

        while(isRunning){
            displayOptions();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    //add employee
                    addEmployee();
                    break;
                case 2:
                    //update employee
                    updateEmployee();
                    break;
                case 3:
                    //delete employee
                    removeEmployee();
                    break;
                case 4:
                    //generate payslip
                    generatePayslip();
                    break;
                case 5:
                    //search employee
                    searchEmployeeById();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid number. Please enter option(1-6)");
            }
        }
        scanner.close();
    }

    private static void searchEmployeeById() throws EmployeeNotFoundException {
        System.out.println("Enter employee ID you want to search:");
        int employeeId = scanner.nextInt();
        scanner.nextLine();

        if(!employeesById.containsKey(employeeId)){
            throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found", null);
        }else{
            System.out.println(employeesById.get(employeeId));
        }
    }

    private static void generatePayslip() throws EmployeeNotFoundException {
        System.out.println("Enter employee ID to generate payslip:");
        int employeeId = scanner.nextInt();
        scanner.nextLine();

        if(!employeesById.containsKey(employeeId)){
            throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found", null);
        }else{
            employeesById.get(employeeId).displayPaySlip();
        }
    }

    private static void removeEmployee() throws EmployeeNotFoundException {
        System.out.println("Enter employee ID that you want to delete");
        int employeeIdToBeDeleted = scanner.nextInt();
        scanner.nextLine();

        if(!employeesById.containsKey(employeeIdToBeDeleted)){
            throw new EmployeeNotFoundException("Employee with ID " + employeeIdToBeDeleted + " not found", null);
        }else {
            employeesById.remove(employeeIdToBeDeleted);
            System.out.println("Employee removed!");
        }
    }

    private static void updateEmployee() throws EmployeeNotFoundException {
        int id = getAndValidateEmployeeId();

        if(!employeesById.containsKey(id)){
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found", null);
        }else{
            Employee employee = employeesById.get(id);

            while(true){ //TODO: Don't think we need this, as when updating we can just ask for employeeID and go and find the employee in the dictionary??
                System.out.println("Enter \"F\" for Full time Employee or \"P\" for Part time Employee");
                char fullOrPart = scanner.next().charAt(0);
                scanner.nextLine();

                if(fullOrPart == 'F'){

                    if(employee instanceof FullTimeEmployee){
                        updateAttribute(employee, BASIC_SALARY);
                        updateAttribute(employee, BONUS);
                        updateAttribute(employee, ALLOWANCE);
                        updateAttribute(employee, DEDUCTION);
                    }


                    double newSalary = employee.calculateSalary();

                    if(!employeesById.containsKey(id)){
                        System.out.println("Employee Id is invalid");
                    }

                    //TODO: Ask Monsur if it's correct?
                    employee.setBasicSalary(newSalary);
                    break;

                }else if(fullOrPart == 'P'){

                    if(employee instanceof PartTimeEmployee){
                        updateAttribute(employee, BASIC_SALARY);
                        updateAttribute(employee, HOURS_WORKED);
                    }


                    double newSalary = employee.calculateSalary();

                    if(!employeesById.containsKey(id)){
                        System.out.println("Employee Id is invalid");
                    }

                    //TODO: Ask Monsur if it's correct?
                    employee.setBasicSalary(newSalary);
                    break;
                }

            }

        }
    }

    private static void updateAttribute(Employee employee, String attributeName){
        while(true){
            System.out.println("Do you want to update your " + attributeName + " (Y/N)");
            char attributeUpdateChoice = scanner.next().charAt(0);
            scanner.nextLine();

            if(attributeUpdateChoice == 'Y'){
                double updatedAttribute = getValidatedEntry("Enter your new " + attributeName);
                switch (attributeName) {
                    case BASIC_SALARY:
                        employee.setBasicSalary(updatedAttribute);
                        break;
                    case ALLOWANCE:
                        if (employee instanceof FullTimeEmployee) {
                            ((FullTimeEmployee) employee).setAllowance(updatedAttribute);
                        }
                        break;
                    case BONUS:
                        if (employee instanceof FullTimeEmployee) {
                            ((FullTimeEmployee) employee).setBonus(updatedAttribute);
                        }
                        break;
                    case DEDUCTION:
                        if (employee instanceof FullTimeEmployee) {
                            ((FullTimeEmployee) employee).setDeduction(updatedAttribute);
                        }
                        break;
                    case HOURS_WORKED:
                        if (employee instanceof PartTimeEmployee) {
                            ((PartTimeEmployee) employee).setHoursWorked(updatedAttribute);
                        }
                        break;
                    default:
                        System.out.println("Unknown attribute: " + attributeName);
                }

                break;
            }else if(attributeUpdateChoice == 'N'){
                break;
            }else{
                System.out.println("Invalid choice! Please enter Y or N");
            }
        }
    }

    private static void addEmployee() {
        int id = getAndValidateEmployeeId();

        for(Integer employeeId : employeesById.keySet()){
            if(employeeId == id){
                System.out.println("Employee Id already exists!. Please enter valid ID");
                break;
            }
        }

        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        System.out.println("Enter your department (for eg: IT/HR/Marketing)");
        String department = scanner.nextLine();

        while(true){
            System.out.println("Enter \"F\" for Full time Employee or \"P\" for Part time Employee");
            char fullOrPart = scanner.next().charAt(0);
            scanner.nextLine();

            if(fullOrPart == 'F'){
                double basicSalary = getValidatedEntry("Enter your basic salary");
                double allowance = getValidatedEntry("Enter your allowance");
                double bonus = getValidatedEntry("Enter your bonus");
                double deduction = getValidatedEntry("Enter your deduction");

                Employee employee = new FullTimeEmployee(id, name, department, basicSalary, allowance, bonus, deduction);
                if(employeesById.containsKey(id)){
                    System.out.println("Employee already exists");
                }else{
                    employeesById.put(id, employee);
                }

                double salary = employee.calculateSalary();
//                if(!salaryById.containsKey(id)){
//                    salaryById.put(id, salary);
//                }

                //TODO: Ask Monsur if it is correct?
                if(!employeesById.containsKey(id)){
                    employeesById.get(id).setBasicSalary(salary);
                }

                break;
            }else if(fullOrPart == 'P'){
                double basicSalary = getValidatedEntry("Enter your basic salary");
                double hoursWorked = getValidatedEntry("Enter your hours worked");

                Employee employee = new PartTimeEmployee(id, name, department, basicSalary, hoursWorked);
                employeesById.put(id, employee);

                double salary = employee.calculateSalary();
//                if(!salaryById.containsKey(id)){
//                    salaryById.put(id, salary);
//                }

                //TODO: Ask Monsur if it is correct?
                if(!employeesById.containsKey(id)){
                    employeesById.get(id).setBasicSalary(salary);
                }

                break;
            }else{
                System.out.println("Invalid choice. Please enter F or P");
            }
        }

    }

    private static int getAndValidateEmployeeId() {
        int id;

        while(true){
            System.out.println("Enter Employee ID:");
            try{
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }

        return id;
    }

    private static double getValidatedEntry(String msg) {
        while(true){
            System.out.println(msg);
            try{
                double input = scanner.nextDouble();
                scanner.nextLine();
                return input;
            }catch(InputMismatchException e){
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }



    private static void displayOptions() {
        System.out.println("----Payroll management service----");
        System.out.println("Enter one of the following options (1-6)");
        System.out.println("1. Add Employee");
        System.out.println("2. Update Employee");
        System.out.println("3. Delete Employee");
        System.out.println("4. Generate Payslip");
        System.out.println("5. Search Employee");
        System.out.println("6. Exit");
        System.out.println("----------------------------------------");
    }
}
