package demo.auth;

import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return sha512(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(sha512(rawPassword));
    }

    private String sha512(CharSequence raw){
        return Base64Utils.encodeToString(Sha512DigestUtils.sha(String.valueOf(raw)));
    }
}
