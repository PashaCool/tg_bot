package pawel.cool.kit;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EntityScan(basePackages = "pawel.cool.kit.model")
@ComponentScan(basePackages = {
        "pawel.cool.kit.repository", "pawel.cool.kit.service"
})
@Configuration
public class TestConfiguration {
}
