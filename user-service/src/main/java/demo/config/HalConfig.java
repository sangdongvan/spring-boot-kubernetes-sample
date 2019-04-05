package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.hal.HalConfiguration;

@Configuration
public class HalConfig {

    @Bean
    public HalConfiguration halConfiguration() {
        return new HalConfiguration();
    }
}
