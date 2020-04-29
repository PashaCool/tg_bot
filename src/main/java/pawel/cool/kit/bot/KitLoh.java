package pawel.cool.kit.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pawel.cool.kit.resolver.AuthorResolver;

@Component
@EnableConfigurationProperties
@ConfigurationProperties
public class KitLoh extends TelegramLongPollingBot {

    @Value("${bot.kit.botUserName}")
    private String botUserName;
    @Value("${bot.kit.botToken}")
    private String botToken;
    @Autowired
    private AuthorResolver resolver;

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage randomAnswer = resolver.getAnswer(update);
        sendMessage(randomAnswer);
    }

    private synchronized void sendMessage(SendMessage randomAnswer) {
        try {
            execute(randomAnswer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }
}
