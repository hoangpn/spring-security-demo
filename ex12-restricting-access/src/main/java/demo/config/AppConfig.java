package demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        // apply all
//        http.authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests().anyRequest().denyAll();

        // has something
        http.authorizeRequests().anyRequest().hasAuthority("read");
//        http.authorizeRequests().anyRequest().hasAnyAuthority("read","write");
//        http.authorizeRequests().anyRequest().hasIpAddress();
//        http.authorizeRequests().anyRequest().hasRole("admin");

        // authentication
//        http.authorizeRequests().anyRequest().authenticated();
//        http.authorizeRequests().anyRequest().anonymous();

        // SpEL based
//        String expression = "hasAuthority('read') OR hasAuthority('write')";
//        http.authorizeRequests().anyRequest().access(expression);

    }
}
