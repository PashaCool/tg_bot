package pawel.cool.kit.process;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ProcessTextMessage implements ProcessInputMessage {

    private static final String APPEAL_TO_PAWEL = "Па[в,ш]";
    private static final String QUESTION = "\\?";
    private Pattern appealToPawel;
    private Pattern questionPattern;

    public ProcessTextMessage() {
        appealToPawel = Pattern.compile(APPEAL_TO_PAWEL, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        questionPattern = Pattern.compile(QUESTION);
    }

    @Override
    public InputMessageType process(String text) {
        InputMessageType messageType;
        if (appealToPawel.matcher(text).find()) {
            messageType = InputMessageType.APPEAL_TO_PAWEL;
        } else if (questionPattern.matcher(text).find()) {
            messageType = InputMessageType.QUESTION;
        }
        else messageType = InputMessageType.ANOTHER;
        return messageType;
    }
}
