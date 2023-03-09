package demo.service;

import demo.auth.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class HelloService {
    @Autowired
    Executor executor;

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
            System.out.println("HelloService async: " + authentication.getDetails().toString());
        } catch (NullPointerException ex) {
            System.out.println("HelloService async: " + authentication);
        }
    }

    public void helloExecutorService() {
//        CompletableFuture.runAsync(() -> {
//            SecurityContext context = SecurityContextHolder.getContext();
//            CustomAuthentication authentication = (CustomAuthentication) context.getAuthentication();
//            try {
//                System.out.println("HelloService CompletableFuture: " + authentication.getDetails().toString());
//            } catch (NullPointerException ex) {
//                System.out.println("HelloService CompletableFuture: " + authentication);
//            }
//        }, executor);
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            CustomAuthentication authentication = (CustomAuthentication) context.getAuthentication();
            return authentication.getDetails().toString();
        };
        ExecutorService e = Executors.newCachedThreadPool();
//        e = new DelegatingSecurityContextExecutorService(e);
        try {
            e.submit(task).get();
        } catch (ExecutionException ex) {
            throw new RuntimeException(ex);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        } finally {
            e.shutdown();
        }
    }
}
