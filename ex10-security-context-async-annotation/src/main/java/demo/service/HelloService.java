package demo.service;

import demo.auth.CustomAuthentication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public void hello() {
        SecurityContext context = SecurityContextHolder.getContext();
        CustomAuthentication authentication = (CustomAuthentication) context.getAuthentication();
        System.out.println("HelloService: " + authentication.getDetails().toString());
    }

    @Async
    public void helloAsync() throws InterruptedException {
        Thread.sleep(2000);
        SecurityContext context = SecurityContextHolder.getContext();
        CustomAuthentication authentication = (CustomAuthentication) context.getAuthentication();
        try {
            System.out.println("HelloService: " + authentication.getDetails().toString());
        } catch (NullPointerException ex) {
            System.out.println("HelloService: " + authentication);
        }
    }
}
