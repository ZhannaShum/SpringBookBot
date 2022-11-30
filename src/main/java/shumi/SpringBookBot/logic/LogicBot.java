package shumi.SpringBookBot.logic;

import shumi.SpringBookBot.DataBase.BookRepository;
import shumi.SpringBookBot.client.Client;
import shumi.SpringBookBot.logic.stateMachine.Application;
import shumi.SpringBookBot.logic.stateMachine.ClientEvent;

import java.util.ArrayList;

public class LogicBot {

    public static BookRepository data = new BookRepository();
    public String startCommandReceived(String name) {

        var answerStart = name + ", привет!" + " Ты не знаешь, " +
                "что хочешь почитать? Не переживай, мы постараемся помочь тебе ~ ";

        return answerStart;
    }

    public String helpCommandReceived(String name) {

        var answerHelp = name +
                ", мы поможем тебе выбрать книгу!";

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

    Client client = new Client(123);
    public String handle(String text, String name) {
        //if (client.getChatID() == 0)
        //Client client = new Client(12);
        int needElement = 0;
        switch (text) {
            case "/start":
                //stateMachine.sendEvent(ClientEvent.ENTER_SIMPLE_COMMAND);
                //stateMachine.sendEvent(ClientEvent.SEND_ANSWER)
                return startCommandReceived(name);
            case "/help":
                //stateMachine.sendEvent(ClientEvent.ENTER_SIMPLE_COMMAND);
                //stateMachine.sendEvent(ClientEvent.SEND_ANSWER)
                return helpCommandReceived(name);
            case "/byAuthor":
                stateMachine.sendEvent(ClientEvent.ENTER_AUTHOR_COMMAND);
                client.addData(text);
                needElement = 2;
                String answer = "";
                //String answer = askAuthor();
                return answer;
            case "/byGenre":
                stateMachine.sendEvent(ClientEvent.ENTER_GENRE_COMMAND);
                client.addData(text);
                needElement = 2;
                answer = "";
                //String answer = askGenre();
                return answer;
            default:
                if (client.size() != 0) {
                    stateMachine.sendEvent(ClientEvent.ENTER_DATA);
                    client.addData(text);
                    if ((stateMachine.getState() == WAITING_ANSWER ) || (client.size() == needElement)) {
                        // таблица сколько элементов необходимо
                        ArrayList<String> data = client.getInformation(needElement);
                        String command = data.get(0);
                        data.remove(0);
                        switch (command) {
                            default:
                                stateMachine.sendEvent(ClientEvent.SEND_ANSWER);
                                client.deleteData(needElement);
                            case "/byAuthor":
                                return authorCommandReceived(data);
                            case "/byGenre":
                                return genreCommandReceived(data);
                        }
                    }
                } else {
                    return "Sorry, I don't understand you";
                }
        }
    }

}
