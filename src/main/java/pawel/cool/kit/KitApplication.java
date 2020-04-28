package pawel.cool.kit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class KitApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(KitApplication.class, args);
    }

}
