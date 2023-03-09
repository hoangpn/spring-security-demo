package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class BeanConfig {

    // userDeitailsService
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        UserDetails userDetails = User
                .withUsername("hoang.pham2")
                .password("MydaiqSOqRi9U6kYGql18Vqw0GRTmPWRigBtCGdcHLJ9XGRdvQhO7lbmdeJbpAGfLs6jfKnimVtJ/LEsCWoDLg==") // can not be null
                .authorities("read") // can not be null
                .build();

        userDetailsService.createUser(userDetails);

        return userDetailsService;
    }
}
