package demo.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class AppConfig {
    @Bean
    public InitializingBean initializingBean() {
        //or spring.security.strategy=MODE_INHERITABLETHREADLOCAL
        return () -> SecurityContextHolder.setStrategyName( SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
}
