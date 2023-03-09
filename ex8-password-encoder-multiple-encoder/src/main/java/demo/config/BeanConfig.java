package demo.config;

import demo.auth.BcryptPasswordEncoder;
import demo.auth.Sha512PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BeanConfig {

    // userDeitailsService
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        UserDetails userDetails = User
                .withUsername("hoang.pham2")
                .password("{sha}MydaiqSOqRi9U6kYGql18Vqw0GRTmPWRigBtCGdcHLJ9XGRdvQhO7lbmdeJbpAGfLs6jfKnimVtJ/LEsCWoDLg==") // can not be null
                .authorities("read") // can not be null
                .build();

        UserDetails userDetails2 = User
                .withUsername("hoang.pham3")
                .password("{bcrypt}$2a$10$nQhetC9p2k31DKOq7/sOeOpQfYJJfJqDrgXzBfbTFGWk.Jxnz7z7a") // can not be null
                .authorities("read") // can not be null
                .build();

        userDetailsService.createUser(userDetails);
        userDetailsService.createUser(userDetails2);

        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("bcrypt", new BcryptPasswordEncoder());
        encoders.put("sha", new Sha512PasswordEncoder());
        return new DelegatingPasswordEncoder("sha", encoders);
    }
}
