package demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import demo.filter.RequestLoggingFilter;
import demo.filter.RequestValidationFilter;
import demo.filter.ThirdPartyAuthenticationFilter;

@Configuration
public class AppConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ThirdPartyAuthenticationFilter thirdPartyAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) {
        http.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter(new RequestLoggingFilter(), BasicAuthenticationFilter.class);
        http.addFilterAt(thirdPartyAuthenticationFilter, BasicAuthenticationFilter.class);
    }
}
