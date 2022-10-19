package shumi.SpringBookBot.service;

import java.util.Scanner;
import shumi.SpringBookBot.logic.logicClass;

public class ConsoleBot {
    public String name;
    public static void onUpdateReceived() {
        System.out.println("What's your name?");
        Scanner text = new Scanner(System.in);
        var name = text.nextLine();
        while (true) {
            var message = text.nextLine();
            switch (message) {
                case "/start":
                   String a = logicClass.startCommandReceived(name);
                    sendMessage(a);
                    break;
                case "/help":
                    String b = logicClass.helpCommandReceived(name);
                    sendMessage(b);
                    break;
                default:
                    sendMessage("Sorry, I don't understand you");
            }
        }
    }

    /*private static void startCommandReceived() {

        var answerStart = "Hi, You don't know what to read today? Don't worry!" +
                " We will try to help you ~ \"";
        sendMessage(answerStart);
    }

    private String getsName() {
        Scanner text = new Scanner(System.in);
        var name = text.nextLine();
        this.name = name;
        return name;
    }

    private static void helpCommandReceived(String name) {
        String answerHelp = name + " can: ";
        sendMessage(answerHelp);

    } */

    private static void sendMessage(String textToSend) {
        System.out.println(textToSend);
    }
}
