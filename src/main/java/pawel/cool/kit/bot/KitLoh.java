package pawel.cool.kit.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVoice;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pawel.cool.kit.service.Resolver;

@Component
@EnableConfigurationProperties
@ConfigurationProperties
public class KitLoh extends TelegramLongPollingBot {

    @Value("${bot.kit.botUserName}")
    private String botUserName;
    @Value("${bot.kit.botToken}")
    private String botToken;
    @Autowired
    private Resolver resolver;

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        PartialBotApiMethod<Message> randomAnswer = resolver.getAnswer(update);
        sendMessage(randomAnswer);
    }

    private synchronized void sendMessage(PartialBotApiMethod<Message> randomAnswer) {
        try {
            if (randomAnswer instanceof SendMessage) {
                execute((SendMessage) randomAnswer);
            } else if (randomAnswer instanceof SendVoice) {
                execute((SendVoice) randomAnswer);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }
}
