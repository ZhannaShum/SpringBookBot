package shumi.SpringBookBot.DataBase.Entity;

import lombok.Data;

@Data
public class Book {
    private String author;
    private String bookName;
    private String genre;

    public Book() {
    }

    public Book(String author, String bookName, String genre) {
        this.author = author;
        this.bookName = bookName;
        this.genre = genre;
    }

}
