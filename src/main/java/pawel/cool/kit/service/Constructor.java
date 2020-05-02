package pawel.cool.kit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class Constructor {

    private static final String ORACLE_PAWEL_VOICE = "src/main/resources/static/audio/oraclePawel.ogg";
    private File oraclePawelVoice;
    private AnswerService answerService;

    public Constructor(@Autowired AnswerService answerService) {
        this.answerService = answerService;
    }

    public String getRandomTextAnswer() {
        return answerService.getRandomTextAnswer();
    }

    public File getOraclePawelVoice() {
        if (oraclePawelVoice == null) {
            this.oraclePawelVoice = new File(ORACLE_PAWEL_VOICE);
        }
        return oraclePawelVoice;
    }
}
