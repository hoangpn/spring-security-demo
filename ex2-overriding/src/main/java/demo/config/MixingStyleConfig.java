//package demo.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.demo.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.demo.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//public class MixingStyleConfig extends WebSecurityConfigurerAdapter {
//
//    // passwordEncoder
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder demo.auth) throws Exception {
//        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//
//        // userDeitailsService
//        UserDetails userDetails = User
//                .withUsername("hoang.pham2")
//                .password("1111")
//                .authorities("read")
//                .build();
//        userDetailsService.createUser(userDetails);
//
//        demo.auth
//                .userDetailsService(userDetailsService);
//    }
//
//}
