package co.uk.lset.payrollmanagement;

public class EmployeeNotFoundException extends Exception{

    public EmployeeNotFoundException(String errorMessage, Throwable error){
        super(errorMessage, error);
    }
}
