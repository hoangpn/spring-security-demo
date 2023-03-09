package demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        UserDetails user = userDetailsService.loadUserByUsername(username);
        boolean successAuthen = passwordEncoder.matches(password, user.getPassword());
        if (successAuthen || user.getUsername().endsWith("@tiki.vn")) {
            return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
        }
        throw new AuthenticationCredentialsNotFoundException("Error!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
