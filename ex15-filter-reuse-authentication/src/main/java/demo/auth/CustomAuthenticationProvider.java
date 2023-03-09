package demo.auth;

import okhttp3.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // ignored
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String callResult = callACP(authentication.getCredentials().toString());
        return new User(callResult,authentication.getCredentials().toString(), Arrays.asList());
    }



     String callACP(String token){
        RequestBody body = RequestBody.create("", MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url("http://access-control-provider.dev.tiki.services/v1/authenticate")
                .addHeader("authorization", token)
                .addHeader("X-Source", "katana")
                .post(body)
                .build();
        OkHttpClient client = new OkHttpClient();
         try {
             Response response = client.newCall(request).execute();
             return response.body().string();
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }
}
