package shumi.SpringBookBot.service;


import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import shumi.SpringBookBot.config.BotConfig;
import shumi.SpringBookBot.logic.LogicBot;

import java.util.concurrent.TimeUnit;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        var logic = new LogicBot();
        if ( update.hasMessage() && update.getMessage().hasText()) {
            var text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            var answer = "";
            switch (text) {
                case "/byAuthor":
                    sendMessage(chatId, "Книгу какого автора " +
                            "вы бы хотели почитать? Введите имя и фамилию");
                    //TimeUnit.SECONDS.sleep(40);
                    answer = logic.handle(text, "Федор Достоевский");
                    break;
                default:
                    answer = logic.handle(text, update.getMessage().getChat().getFirstName());
            }
            sendMessage(chatId, answer);
        }
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
