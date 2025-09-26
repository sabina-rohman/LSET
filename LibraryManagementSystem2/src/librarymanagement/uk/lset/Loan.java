package librarymanagement.uk.lset;

public class Loan {
    private static int serialLoan = 1;
    private int loanId;
    private String bookIsbn;
    private String memberId;
    private String loanDate;
    private String dueDate;
    private String returnDate = "Not returned";

    public Loan(String bookIsbn, String memberId, String loanDate, String dueDate){
        this.loanId = serialLoan++;
        this.bookIsbn = bookIsbn;
        this.memberId = memberId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public static int getSerialLoan() {
        return serialLoan;
    }

    public int getLoadId() {
        return loanId;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Loan[" +
                "loanId=" + loanId +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", memberId='" + memberId + '\'' +
                ", loanDate='" + loanDate + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ']';
    }
}
