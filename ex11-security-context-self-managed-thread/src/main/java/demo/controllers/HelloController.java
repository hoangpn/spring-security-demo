package demo.controllers;

import demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
//        helloService.hello();
//        helloService.helloAsync();
//        helloService.helloExecutorService();
//        Callable<String> task = () -> {
//            SecurityContext context = SecurityContextHolder.getContext();
//            return context.getAuthentication().getName();
//        };
//        ExecutorService e = Executors.newCachedThreadPool();
//        try {
//            var contextTask = new DelegatingSecurityContextCallable<>(task);
//            return e.submit(contextTask).get();
//        } catch (ExecutionException ex) {
//            throw new RuntimeException(ex);
//        } finally {
//            e.shutdown();
//        }
        Runnable a = new Runnable() {
            @Override
            public void run() {
            SecurityContext context = SecurityContextHolder.getContext();
            context.getAuthentication().getName();
            context.getAuthentication().getName();

            }
        };
        Thread t = new Thread(a);
        t.start();
        return "";
    }
}
