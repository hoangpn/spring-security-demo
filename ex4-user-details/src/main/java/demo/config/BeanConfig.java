package demo.config;

import demo.auth.CustomGrantedAuthority;
import demo.auth.CustomUser;
import demo.auth.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.Set;

@Configuration
public class BeanConfig {

    // userDeitailsService
    @Bean
    public UserDetailsService userDetailsService(CustomUserDetailsService userDetailsService) {

        Set<GrantedAuthority> grantedAuthorities = Set.of(
                new CustomGrantedAuthority("read","readddd"),
                new CustomGrantedAuthority("write","writeeee")
        );

        UserDetails userDetails = new CustomUser(
                "hoang.pham2",
                "1111",
                grantedAuthorities,
                "hoang.pham2@tiki.vn",
                "091234567"
        );

        userDetailsService.addUser(userDetails);

        return userDetailsService;
    }

        // passwordEncoder
        @Bean
        public PasswordEncoder passwordEncoder () {
            return NoOpPasswordEncoder.getInstance();
        }
    }
