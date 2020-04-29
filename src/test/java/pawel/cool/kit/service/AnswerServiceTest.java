package pawel.cool.kit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pawel.cool.kit.TestConfiguration;
import pawel.cool.kit.model.Answer;
import pawel.cool.kit.repository.AnswersRepo;

import java.util.Collections;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TestConfiguration.class)
class AnswerServiceTest {

    @MockBean
    private AnswersRepo answersRepo;
    @InjectMocks
    private AnswerService answerService;
    private static final String REPLY = "Санек, ты чего?";

    @BeforeEach
    public void setUp() {
        Iterable<Answer> answers = Collections.singleton(createAnswer());
        when(answersRepo.findAll()).thenReturn(answers);
    }

    @Test
    @DisplayName("тест на рандомное значение")
    void randomInRange() {
        assertThat(answerService.getRandom()).isGreaterThan(-1).isLessThan(2);
    }

    private Answer createAnswer() {
        Answer answer = new Answer();
        answer.setId(new Random().nextInt());
        answer.setAnswer(REPLY);
        return answer;
    }

}