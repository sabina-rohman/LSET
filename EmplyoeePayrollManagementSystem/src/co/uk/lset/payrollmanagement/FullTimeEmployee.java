package co.uk.lset.payrollmanagement;

public class FullTimeEmployee extends Employee{
    private double allowance;
    private double bonus;
    private double deduction;

    public FullTimeEmployee(int ID, String name, String department, double basicSalary,
                            double allowance, double bonus, double deduction) {
        super(ID, name, department, basicSalary);
        this.allowance = allowance;
        this.bonus = bonus;
        this.deduction = deduction;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }

    @Override
    public double calculateSalary() {
        return super.calculateSalary() + allowance + bonus - deduction;
    }

    @Override
    public void displayPaySlip() {
        String paySlip = "\n=========Payslip===========\n" +
                "Basic Salary: " + getBasicSalary() +"\n" +
                "Department: " + getDepartment() + "\n" +
                "Allowance: " + getAllowance() + "\n" +
                "Bonus: " + getBonus() + "\n" +
                "Deduction: " + getDeduction() + "\n" +
                "Total salary: " + String.format("%.2f", calculateSalary());

        System.out.println(paySlip);

    }


}
