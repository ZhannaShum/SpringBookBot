package shumi.SpringBookBot.logic;

import shumi.SpringBookBot.DataBase.BookRepository;

import java.util.ArrayList;

public class LogicBot {

    public static BookRepository data = new BookRepository();
    public String startCommandReceived(String name) {

        var answerStart = "Hi, " + name + "!" + " You don't know " +
                "what to read today? Don't worry! We will try to help you ~ ";

        return answerStart;
    }

    public String helpCommandReceived(String name) {

        var answerHelp = name +
                ", we will help you choose a book";

        return answerHelp;
    }

    public String authorCommandReceived(String author) {
        ArrayList<String> books = data.GetBookWithAuthor(author);
        String answer;
        if (books.size() != 0) {
             answer = String.join("\n", books);
        } else {
             answer = "Книг с таким автором нет";
        }
        return answer;
    }

    public String genreCommandReceived(String genre) {
        ArrayList<String> books = data.GetBookWithGenre(genre);
        String answer;
        if (books.size() != 0) {
            answer = String.join("\n", books);
        } else {
            answer = "Книг с таким жанром нет";
        }
        return answer;
    }

    public String handle(String text, String name) {
        switch (text) {
            case "/start":
                return startCommandReceived(name);
            case "/help":
                return helpCommandReceived(name);
            case "/byAuthor":
                return authorCommandReceived(name);
            case "/byGenre":
                return genreCommandReceived(name);
            default:
                return "Sorry, I don't understand you";
        }
    }
}
