package pawel.cool.kit.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@EnableConfigurationProperties
@ConfigurationProperties
public class KitLoh extends TelegramLongPollingBot {

    @Value("${bot.kit.botUserName}")
    private String botUserName;
    @Value("${bot.kit.botToken}")
    private String botToken;
    @Value("${bot.kit.answer1}")
    private String answer1;

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            SendMessage replyMessage = new SendMessage();
            replyMessage.enableMarkdown(true);
            replyMessage.setText(answer1);
            replyMessage.setChatId(message.getChatId());
            try {
                execute(replyMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else throw new IllegalStateException("Empty Message");
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }
}
