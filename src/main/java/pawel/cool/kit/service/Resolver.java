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

@Component
public class Resolver {

    private static final String ORACLE_PAWEL_VOICE = "src/main/resources/static/audio/oraclePawel.ogg";
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
                    return constructAnswerMessage(chatId, getRandomTextAnswer());
                case APPEAL_TO_PAWEL:
                    return constructAudioMessage(chatId);
            }
        }
        return message;
    }

    private SendVoice constructAudioMessage(Long chaId) {
        SendVoice audioMessage = new SendVoice();
        audioMessage.setChatId(chaId);
        audioMessage.setVoice(new File(ORACLE_PAWEL_VOICE));
        return audioMessage;
    }

    private String getRandomTextAnswer() {
        return answerService.getRandomTextAnswer();
    }

    private SendMessage constructAnswerMessage(Long chatId, String answerText) {
        message.setChatId(chatId);
        message.setText(answerText);
        return message;
    }
}
