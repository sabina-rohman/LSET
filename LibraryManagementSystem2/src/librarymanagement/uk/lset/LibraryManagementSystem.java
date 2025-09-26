package librarymanagement.uk.lset;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.io.*;
import java.time.temporal.ChronoUnit;

public class LibraryManagementSystem {
    private static ArrayList<Book> books = readBooks();
    private static  ArrayList<Member> members = readMembers();
    private static ArrayList<Loan> loans = readLoans();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;

        while(isRunning){
            displayMainMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    displayBookMenu();
                    break;
                case 2:
                    displayMembersMenu();
                    break;
                case 3:
                    displayLoanMenu();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Please enter valid options (1-4)");
            }

        }
        writeBooks();
        writeMembers();
        writeLoans();
        scanner.close();
    }

    private static void searchBook() {
        System.out.println("Please enter the title of the book you are looking for");
        String title = scanner.nextLine();

        for(Book book : books){
            if(book.getTitle().equals(title)){
                if(book.getAvailableCopies() > 0){
                    System.out.println("Yes, the book is available in the library: " + book);
                }else{
                    System.out.println("Sorry, we don't have the book available in our library, Please try next time!");
                }
            }else {
                System.out.println("Sorry, we don't have the book available in our library");
            }
        }
    }

    private static void addBook() {
        System.out.println("Please enter the book isbn");
        String isbn = scanner.nextLine();

        System.out.println("Please enter book title");
        String title = scanner.nextLine();

        System.out.println("Please enter book genre");
        String genre = scanner.nextLine();

        System.out.println("Please enter total copies");
        int totalCopies = scanner.nextInt();

        System.out.println("Please enter available copies");
        int availableCopies = scanner.nextInt();

        Book book = new Book(isbn, title, genre, totalCopies, availableCopies);
        books.add(book);
    }

    private static void displayMainMenu(){
        System.out.println("--------Welcome to Library----------");
        System.out.println("Select one option");
        System.out.println("1. Manage Books");
        System.out.println("2. Manage Members");
        System.out.println("3. Manage Loans");
        System.out.println("4. Exit");
    }

    private static void displayBookMenu(){
        while(true){
            System.out.println("Choose one of the following options (1-4)");
            System.out.println("1. Add book");
            System.out.println("2. Search book");
            System.out.println("3. View all books");
            System.out.println("4. Return to the main menu");

            int bookOption = scanner.nextInt();
            scanner.nextLine();

            switch(bookOption){
                case 1:
                    addBook();
                    break;
                case 2:
                    searchBook();
                    break;
                case 3:
                    System.out.println("Here is all the books available");
                    for(Book book : books){
                        System.out.println(book.toString());
                    }
                    break;
                case 4:
                    System.out.println("Returning to the main menu");
                    return;
                default:
                    System.out.println("You entered invalid option. Please enter option 1-4");
            }
        }

    }

    private static void displayMembersMenu(){
        while(true){
            System.out.println("Choose one of the following options:");
            System.out.println("1. Add member");
            System.out.println("2. Search member");
            System.out.println("3. View all members");
            System.out.println("4. return to the main menu");

            int memberOption = scanner.nextInt();
            scanner.nextLine();

            switch (memberOption){
                case 1:
                    addMember();
                    break;
                case 2:
                    searchMember();
                    break;
                case 3:
                    System.out.println("List of all members in the library");
                    for (Member member : members){
                        System.out.println(member.toString());
                    }
                    break;
                case 4:
                    System.out.println("Returning to the main menu");
                    return;
                default:
                    System.out.println("You entered invalid option. Please enter option 1-4");
            }
        }
    }

    private static void searchMember() {
        System.out.println("Please enter member's name");
        String name = scanner.nextLine();

        for(Member member : members){
            if(member.getName().equals(name)){
                System.out.println("Member found: " + member.toString());
            }else{
                System.out.println("Member doesn't exists in our library. Please try with another name");
            }
        }
    }

    private static void addMember() {
        System.out.println("Enter member id:");
        String memberId = scanner.nextLine();

        for(Member member : members){
            if(member.getMemberId().equals(memberId)){
                System.out.println("error: Member ID already exists. Please try again!");
                return;
            }
        }

        System.out.println("Enter name:");
        String name = scanner.nextLine();

        System.out.println("Enter email address");
        String email = scanner.nextLine();

        if(!email.contains("@")){
            System.out.println("Error: Invalid email address");
            return;
        }

        Member member = new Member(memberId, name, email);
        members.add(member);
    }

    private static void displayLoanMenu(){
        while(true){
            System.out.println("Choose one of the following options:");
            System.out.println("1. Lend a book");
            System.out.println("2. Return a book");
            System.out.println("3. View active loans");
            System.out.println("4. Return to the main menu");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch(option){
                case 1:
                    lendABook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    for(Loan loan : loans){
                        if(loan.getReturnDate().equals("Not returned")){
                            System.out.println(loan.toString());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Returning to the main menu");
                    return;
                default:
                    System.out.println("You entered invalid option. Please enter option 1-4");
            }
        }
    }

    private static void returnBook() {
        System.out.println("Enter book ISBN:");
        String isbn = scanner.nextLine();

        for (Loan loan : loans){
            if(loan.getBookIsbn().equals(isbn)){
                System.out.println("Found the active loan: " + loan.toString());

                System.out.println("Enter return date (DD/MM/YYYY):");
                String returnDateStr = scanner.nextLine();

//                LocalDate currentDate = LocalDate.now();
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//                String currentDateStr = formatter.format(currentDate);
                if(isDateValid(returnDateStr)){
                    loan.setReturnDate(returnDateStr);

                    for(Book book : books){
                        if(book.getIsbn().equals(isbn)){
                            book.returnBook();
                        }
                    }

                    calculateFine(loan, returnDateStr);
                }
            }else{
                System.out.println("The loan you entered is inactive/invalid!");
            }
        }
    }

    private static void calculateFine(Loan loan, String returnDateStr) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dueDate = LocalDate.parse(loan.getDueDate(), formatter);
            LocalDate returnDate = LocalDate.parse(returnDateStr, formatter);

            //compare dates
            if (returnDate.isAfter(dueDate)) {
                System.out.println("The book is returned late!");
                long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);

                if(daysLate > 0){
                    double fine = daysLate * 0.20;
                    System.out.println("The book was " + daysLate + " days overdue. Fine payable: " + fine);
                }
            }else{
                System.out.println("Book returned successfully.");
            }
        }catch(DateTimeParseException e){
            System.out.println("Error parsing due date: " + loan.getDueDate());
        }
    }

    private static void lendABook() {
        System.out.println("Please enter book ISBN");
        String isbn = scanner.nextLine();

        System.out.println("Please enter member ID");
        String memberId = scanner.nextLine();

        boolean isbookValid = false;
        boolean isMemberIdValid = false;

        for(Book book : books){
            if(book.getIsbn().equals(isbn)){
                System.out.println("Book ISBN is valid");
                isbookValid = true;
                if(book.getAvailableCopies() > 0){
                    System.out.println("Book is available!");
                    book.borrowBook();
                    System.out.println("Book has been borrowed!");
                }else{
                    System.out.println("Book isn't available!");
                }
                break;
            }
        }

        if (!isbookValid) {
            System.out.println("No book found with ISBN: " + isbn);
        }

        for(Member member : members){
            if (member.getMemberId().equals(memberId)) {
                isMemberIdValid = true;
                break;
            }
        }

        if(isbookValid && isMemberIdValid){
            LocalDate loanDate = LocalDate.now();

            //Due date = 14 days later
            LocalDate duedate = loanDate.plusDays(14);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String loanDateStr = formatter.format(loanDate);
            String dueDateStr = formatter.format(duedate);

            Loan loan = new Loan(isbn, memberId, loanDateStr, dueDateStr);
            loans.add(loan);
        }
    }

    private static void writeBooks(){
        String path = "D:\\work\\LSET\\Read_Write\\Books.dat";
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))){
            outputStream.writeObject(books);
            System.out.println("All books saved!");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<Book> readBooks() {
        ArrayList<Book> booksFromFile = new ArrayList<>();
        String path = "D:\\work\\LSET\\Read_Write\\Books.dat";

        try {
            File bookFile = new File(path);
            if (!bookFile.exists()) {
                bookFile.createNewFile();
                return booksFromFile;
            }

            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
                booksFromFile = (ArrayList<Book>) inputStream.readObject();

                System.out.println("Book loaded: " + booksFromFile.size());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while reading books");
            e.printStackTrace();
        }

        return booksFromFile;
    }

    private static void writeMembers(){
        String path = "D:\\work\\LSET\\Read_Write\\Members.dat";
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))){
            outputStream.writeObject(members);
            System.out.println("All members saved!");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<Member> readMembers() {
        ArrayList<Member> memebersFromFile = new ArrayList<>();
        String path = "D:\\work\\LSET\\Read_Write\\Members.dat";

        try {
            File membersFile = new File(path);
            if (!membersFile.exists()) {
                membersFile.createNewFile();
                return memebersFromFile;
            }

            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
                memebersFromFile = (ArrayList<Member>) inputStream.readObject();
                System.out.println("Book loaded: " + memebersFromFile.size());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while reading members");
            e.printStackTrace();
        }

        return memebersFromFile;
    }

    private static void writeLoans(){
        String path = "D:\\work\\LSET\\Read_Write\\Loans.dat";
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))){
            outputStream.writeObject(loans);
            System.out.println("All loans saved");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<Loan> readLoans(){
        ArrayList<Loan> loansfromFile = new ArrayList<>();
        String path = "D:\\work\\LSET\\Read_Write\\Loans.dat";

        try{
            File loansFile = new File(path);
            if(!loansFile.exists()){
                loansFile.createNewFile();
                return loansfromFile;
            }

            try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))){
                loansfromFile = (ArrayList<Loan>) inputStream.readObject();

                System.out.println("Loan loaded: " + loansfromFile.size());

            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Errors found while reading loans");
            e.printStackTrace();
        }

        return loansfromFile;
    }

    private static boolean isDateValid(String dateStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
            LocalDate.parse(dateStr, formatter);
            return true;
        }catch (DateTimeParseException e){
            return false;
        }
    }

}
