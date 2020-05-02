package pawel.cool.kit.process;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestConfiguration.class)
class ProcessTextMessageTest {

    @Autowired
    private ProcessInputMessage processInputMessage;

    @ParameterizedTest(name = "Тест на возвращение [{index}] {arguments}")
    @DisplayName("Нахождения обращения к Паше в тексте")
    @ValueSource(strings = {"Павлик", "павлик", "павел", "паша", "павлин"})
    void randomInRange(String input) {
        InputMessageType process = processInputMessage.process(input);
        assertThat(process).isEqualTo(InputMessageType.APPEAL_TO_PAWEL);
    }
}