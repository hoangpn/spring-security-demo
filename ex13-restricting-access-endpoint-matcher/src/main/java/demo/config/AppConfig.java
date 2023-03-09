package demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

        // mvcMatchers
        http.authorizeRequests().mvcMatchers("/hello").hasAuthority("read");
        // mvcMatchers with method
        http.authorizeRequests().mvcMatchers(HttpMethod.POST,"/hello").hasAuthority("read");
        // mvcMatchers with regex
        http.authorizeRequests().mvcMatchers("/hello/**").hasAuthority("read");
        http.authorizeRequests().mvcMatchers("/hello/{code:^[0-9]*$}").hasAuthority("read"); //code:regex
        http.authorizeRequests().mvcMatchers("/hello/**/test").hasAuthority("read");

        // antMatchers: same as mvcMatchers but "/hello/" will work -> not recommend
        http.authorizeRequests().antMatchers("/hello").hasAuthority("read");

        // regexMatchers
        // /brand/{brand_name}?is_active=true
        http.authorizeRequests().regexMatchers("(/brand/)([^/]+)(\\?is_active=true)").hasAuthority("read");

    }
}
