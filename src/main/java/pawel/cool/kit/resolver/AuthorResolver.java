package pawel.cool.kit.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import pawel.cool.kit.service.AnswerService;

@Component
public class AuthorResolver {

    private static final String PAWEL = "PawelI";
    private static final String BARADAUKIN = "baradaukin";
    private static final String MEKL_CHERNYI = "Mekl_chernyi";
    private static final String ARSENIJ = "Arsenij1994";
    private final SendMessage message = new SendMessage();
    @Autowired
    private AnswerService answerService;

    public SendMessage getAnswer(Update update) {
        String randomTextAnswer = getRandomTextAnswer();
        return constructAnswerMessage(update.getMessage().getChatId(), randomTextAnswer);
    }

    private String getRandomTextAnswer() {
        return answerService.getRandomTextAnswer();
    }

    private SendMessage constructAnswerMessage(Long chatId, String answerText) {
        message.setChatId(chatId);
        message.setText(answerText);
        return message;
    }

    public String answerForUser(String userName) {
        switch (userName) {
            case PAWEL:
                return "Отличное предложение";
            case ARSENIJ:
                return "Эту хуйню можно читать";
            case MEKL_CHERNYI:
                return "Дагестанские москвичи";
        }
        return "default";
    }

    public boolean isKitOrMitya(String userName) {
        return !(userName.equals(PAWEL) || userName.equals(BARADAUKIN) || userName.equals(MEKL_CHERNYI) ||
                userName.equals(ARSENIJ));
    }
}
