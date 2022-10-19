package shumi.SpringBookBot.service;


import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import shumi.SpringBookBot.config.BotConfig;
import shumi.SpringBookBot.logic.logicClass;

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

        if(update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {

                case "/start":
                    var s = logicClass.startCommandReceived(update.getMessage().getChat().getFirstName());
                    sendMessage(chatId, s);
                    break;
                case "/help":
                    var d = logicClass.helpCommandReceived(update.getMessage().getChat().getFirstName());
                    sendMessage(chatId, d);
                    break;
                default:
                    sendMessage(chatId, "Sorry, I don't understand you");
            }
        }

    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try{
            execute(message);
        }
        catch (TelegramApiException e) {

        }
    }
}
