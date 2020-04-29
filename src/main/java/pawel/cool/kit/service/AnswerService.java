package pawel.cool.kit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawel.cool.kit.repository.AnswersRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AnswerService {

    private AnswersRepo answers;
    private Map<Integer, String> answersMap = new HashMap<>();
    private int totalAnswers = 0;

    public AnswerService(AnswersRepo answers) {
        this.answers = answers;
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
