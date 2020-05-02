package pawel.cool.kit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVoice;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import pawel.cool.kit.process.InputMessageType;
import pawel.cool.kit.process.ProcessInputMessage;

import java.io.File;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Component
public class Resolver {

    private static final String ORACLE_PAWEL_VOICE = "src/main/resources/static/audio/oraclePawel.ogg";
    private File oraclePawelVoice;
    private final SendMessage message = new SendMessage();
    @Autowired
    private AnswerService answerService;
    @Autowired
    private ProcessInputMessage processor;

    public PartialBotApiMethod<Message> getAnswer(Update update) {
        if (update.hasMessage()) {
            return constructAnswer(update.getMessage());
        } else return message;
    }

    public PartialBotApiMethod<Message> constructAnswer(Message inputMessage) {
        if (inputMessage.hasText()) {
            InputMessageType process = processor.process(inputMessage.getText());
            Long chatId = inputMessage.getChatId();
            switch (process) {
                case ANOTHER:
                    return constructAnswerMessage(chatId, answerService::getRandomTextAnswer);
                case APPEAL_TO_PAWEL:
                    return constructAudioMessage(chatId, this::getOraclePawelVoice);
            }
        }
        return message;
    }

    private SendVoice constructAudioMessage(Long chaId, Supplier<File> supplier) {
        SendVoice audioMessage = new SendVoice();
        audioMessage.setChatId(chaId);
        audioMessage.setVoice(supplier.get());
        return audioMessage;
    }

    private File getOraclePawelVoice() {
        if (oraclePawelVoice == null) {
            this.oraclePawelVoice = new File(ORACLE_PAWEL_VOICE);
        }
        return oraclePawelVoice;
    }

    private SendMessage constructAnswerMessage(Long chatId, Supplier<String> supplier) {
        message.setChatId(chatId);
        message.setText(supplier.get());
        return message;
    }
}
