package shumi.SpringBookBot.service;


import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import shumi.SpringBookBot.config.BotConfig;

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
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "/help":
                    helpCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    sendMessage(chatId, "Sorry, I don't understand you");
            }
        }

    }

    private void startCommandReceived(long chatId, String name) {

        // var
        String answerStart = "Hi, " + name + "!";

        sendMessage(chatId, answerStart);
    }

    private void helpCommandReceived(long chatId, String name) {

        String answerHelp = "Hi, " + name + "!" + " You don't know what to read today? Don't worry! We will try to help you ~ ";

        sendMessage(chatId, answerHelp);
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
