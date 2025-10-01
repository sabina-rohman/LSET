package co.uk.lset.payrollmanagement;

public class PartTimeEmployee extends Employee{
    private double hoursWorked;
    private static final double FULL_TIME_MONTHLY_HOURS = 160.0;

    public PartTimeEmployee(int ID, String name, String department, double basicSalary, double hoursWorked) {
        super(ID, name, department, basicSalary);
        this.hoursWorked = hoursWorked;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }


    @Override
    public double calculateSalary() {
        return (hoursWorked / FULL_TIME_MONTHLY_HOURS) * getBasicSalary();
    }

    @Override
    public void displayPaySlip() {
        String paySlip = "\n=========Payslip===========\n" +
                "Basic Salary: " + getBasicSalary() +"\n" +
                "Department: " + getDepartment() + "\n" +
                "Hours worked: " + getHoursWorked() + "\n" +
                "Full time monthly hours: " + FULL_TIME_MONTHLY_HOURS + "\n" +
                "Total salary: " + String.format("%.2f", calculateSalary());

        System.out.println(paySlip);
    }
}
