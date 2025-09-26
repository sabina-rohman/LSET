package librarymanagement.uk.lset;

import java.io.Serializable;

public class Book implements Serializable {
    private String isbn;
    private String title;
    private String genre;
    private int totalCopies;
    private int availableCopies;

    public Book(String isbn, String title, String genre, int totalCopies, int availableCopies){
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void borrowBook(){
        if(getAvailableCopies() > 0){
            availableCopies --;
        }
    }

    public void returnBook(){
        availableCopies++;
    }

    @Override
    public String toString() {
        return "Book[" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", totalCopies=" + totalCopies +
                ", availableCopies=" + availableCopies +
                ']';
    }
}
