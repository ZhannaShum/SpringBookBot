package shumi.SpringBookBot.DataBase;

import java.util.ArrayList;

public interface Repository<K> {

    ArrayList<K> GetBookWithAuthor(K author);
    ArrayList<K> GetBookWithGenre(K genre);
    void AddBook(K author, K bookName, K genre);

}
