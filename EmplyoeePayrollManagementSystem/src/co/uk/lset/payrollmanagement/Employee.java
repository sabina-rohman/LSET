package co.uk.lset.payrollmanagement;

public abstract class Employee implements Payable{
    private final int ID;
    private final String name;
    private String department;
    private double basicSalary;

    public Employee(int ID, String name, String department, double basicSalary){
        this.ID = ID;
        this.name = name;
        this.department = department;
        this.basicSalary = basicSalary;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getBasicSalary() {
        return basicSalary;
    }


    public void setDepartment(String department) {
        this.department = department;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    @Override
    public double calculateSalary() {
        return basicSalary;
    }

    public abstract void displayPaySlip();

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", basicSalary=" + basicSalary +
                '}';
    }
}
