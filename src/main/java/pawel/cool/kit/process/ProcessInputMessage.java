package pawel.cool.kit.process;

@FunctionalInterface
public interface ProcessInputMessage {

    InputMessageType process(String text);
}
