package demo.controllers;

import demo.auth.CustomAuthentication;
import demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        helloService.hello();
        helloService.helloAsync();
        return "Hello";
    }
}
