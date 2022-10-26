package shumi.SpringBookBot.service;

import java.util.Scanner;

import shumi.SpringBookBot.logic.LogicBot;

public class ConsoleBot {
    public String name;

    public static void onUpdateReceived() {
        var logic = new LogicBot();
        System.out.println("What's your name?");
        Scanner text = new Scanner(System.in);
        var name = text.nextLine();
        ;

        while (true) {
            var instruction = text.nextLine();
            sendMessage(logic.handle(instruction, name));
        }
    }

    private static void sendMessage(String textToSend) {
        System.out.println(textToSend);
    }
}
