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
                .password("1111") // can not be null
                .authorities("read") // can not be null
                .build();

        UserDetails userDetails2 = User
                .withUsername("bao.nguyen10")
                .password("1111") // can not be null
                .authorities("write") // can not be null
                .build();

        // role user must have prefix ROLE_
        UserDetails userDetails3 = User
                .withUsername("phuong.nguyen1")
                .password("1111") // can not be null
                .authorities("ROLE_admin") // can not be null
                .build();

        userDetailsService.createUser(userDetails);
        userDetailsService.createUser(userDetails2);
        userDetailsService.createUser(userDetails3);

        return userDetailsService;
    }

    // passwordEncoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
