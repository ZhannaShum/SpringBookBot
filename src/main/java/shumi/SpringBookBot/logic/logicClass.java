package shumi.SpringBookBot.logic;


public class logicClass {
    public static String startCommandReceived(String name) {

        var answerStart = "Hi, " + name + "!";

        return answerStart;
    }

    public static String helpCommandReceived(String name) {

        var answerHelp = "Hi, " + name + "!" + " You don't know what to read today? Don't worry! We will try to help you ~ ";

        return answerHelp;
    }
}
