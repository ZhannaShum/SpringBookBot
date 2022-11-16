package shumi.SpringBookBot.DataBase;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import shumi.SpringBookBot.DataBase.Entity.Book;

import java.util.ArrayList;

public class BookRepository implements Repository<String> {
    public MongoCollection<Book> collection;

    public BookRepository() {
        this.collection = MongoConnection.mongo.getCollection("BookCollection", Book.class);
    }

    public void AddBook(String author, String bookName, String genre) {
        collection.insertOne(new Book(
                author,
                bookName,
                genre));
    }
    public ArrayList<String> GetBookWithAuthor(String author){
        FindIterable<Book> findIt = collection.find(Filters.eq("author", author));
        ArrayList<String> arr = new ArrayList<>();
        for(Book doc : findIt) {
            arr.add(doc.getBookName());
        }
        return arr;
    }
    public ArrayList<String> GetBookWithGenre(String genre){
        FindIterable<Book> findIt = collection.find(Filters.eq("genre", genre));
        ArrayList<String> arr = new ArrayList<>();
        for(Book doc : findIt) {
            arr.add(doc.getBookName());
        }
        return arr;
    }

}
