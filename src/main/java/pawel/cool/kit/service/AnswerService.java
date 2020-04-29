package pawel.cool.kit.service;

import org.springframework.stereotype.Service;
import pawel.cool.kit.repository.AnswersRepo;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AnswerService {

    private AnswersRepo answers;
    private Map<Integer, String> answersMap = new HashMap<>();
    private int totalAnswers;

    public AnswerService(AnswersRepo answers) {
        this.answers = answers;
    }

    @PostConstruct
    public void setUp() {
        answers.findAll().forEach(entity -> answersMap.put(entity.getId(), entity.getAnswer()));
        totalAnswers = answersMap.size();
    }

    public String getRandomTextAnswer() {
        return answersMap.get(getRandom());
    }

    public int getRandom() {
        return ThreadLocalRandom.current().nextInt(0, totalAnswers + 1);
    }
}
