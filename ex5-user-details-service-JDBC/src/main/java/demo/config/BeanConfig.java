package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class BeanConfig {

    // userDeitailsService
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        String usersByUsernameQuery = "select email, password, status from users where email = ?";
        String authsByUserQuery = "select users.email, p.name\n" +
                "from users\n" +
                "         join user_group ug on users.id = ug.user_id\n" +
                "join group_authorized_scopes gas on ug.group_id = gas.group_id\n" +
                "join permission_role pr on gas.role_id = pr.role_id\n" +
                "join permissions p on pr.permission_id = p.id\n" +
                "where email = ?";
        var userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
        return userDetailsManager;
    }

    // passwordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
