package demo.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return brcypt(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println("use bcrypt password encoder");
        return BCrypt.verifyer().verify(rawPassword.toString().toCharArray(),encodedPassword).verified;
    }

    private String brcypt(CharSequence raw) {
        // 1111 = $2a$10$nQhetC9p2k31DKOq7/sOeOpQfYJJfJqDrgXzBfbTFGWk.Jxnz7z7a
        return BCrypt.withDefaults().hashToString(10, raw.toString().toCharArray());
    }

}
