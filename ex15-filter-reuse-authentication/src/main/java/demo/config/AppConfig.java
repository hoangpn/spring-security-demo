package demo.config;

import demo.auth.CustomAuthenticationProvider;
import demo.filter.ThirdPartyAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class AppConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(customAuthenticationProvider)
                .addFilterAt(thirdPartyAuthenticationFilter(), BasicAuthenticationFilter.class);
    }

    ThirdPartyAuthenticationFilter thirdPartyAuthenticationFilter() throws Exception {
        ThirdPartyAuthenticationFilter filter = new ThirdPartyAuthenticationFilter("/**");
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }
}
