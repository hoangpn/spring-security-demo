package demo.controllers;

import demo.auth.CustomAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        SecurityContext context = SecurityContextHolder.getContext();
        CustomAuthentication authentication = (CustomAuthentication) context.getAuthentication();
        return authentication.getDetails().toString();
    }
}
