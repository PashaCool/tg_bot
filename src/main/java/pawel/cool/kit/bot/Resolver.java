package pawel.cool.kit.bot;

import org.springframework.stereotype.Component;

@Component
public class Resolver {

    private static final String PAWEL = "PawelI";
    private static final String BARADAUKIN = "baradaukin";
    private static final String MEKL_CHERNYI = "Mekl_chernyi";
    private static final String ARSENIJ = "Arsenij1994";

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
}
