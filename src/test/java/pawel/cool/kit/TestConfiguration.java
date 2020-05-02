package pawel.cool.kit;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import pawel.cool.kit.service.Resolver;

@EntityScan(basePackages = "pawel.cool.kit.model")
@ComponentScan(basePackages = {
        "pawel.cool.kit.repository", "pawel.cool.kit.service"
}, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = Resolver.class))
@Configuration
public class TestConfiguration {
}
