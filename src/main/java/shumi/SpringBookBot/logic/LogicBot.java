package shumi.SpringBookBot.logic;


public class LogicBot {
    public String startCommandReceived(String name) {

        var answerStart = "Hi, " + name + "!";

        return answerStart;
    }

    public String helpCommandReceived(String name) {

        var answerHelp = "Hi, " + name + "!" + " You don't know what to read today? Don't worry! We will try to help you ~ ";

        return answerHelp;
    }

    public String handle(String text, String name) {
        switch (text) {
            case "/start":
                return startCommandReceived(name);
            case "/help":
                return helpCommandReceived(name);
            default:
                return "Sorry, I don't understand you";
        }
    }
}
