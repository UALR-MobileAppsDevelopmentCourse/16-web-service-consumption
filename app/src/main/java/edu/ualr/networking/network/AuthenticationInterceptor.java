package edu.ualr.networking.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by irconde on 2019-10-26.
 */

// TODO 27. We create a new Interceptor class to add the authentication token to the header of the
//  Http requests we send to the server

public class AuthenticationInterceptor implements Interceptor {

    private String token;

    public AuthenticationInterceptor(String token) {
        this.token = token;
    }

    // TODO 27.01 We implement the body of the intercept method.
    // TODO 27.02 We get the current request from the chain of requests
    // TODO 27.03. We use the Request.Builder to modify the original request and set
    //  the Authentication header with the desired token value
    // TODO 27.04 The resulting request is sent
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request newRequest  = originalRequest.newBuilder()
                .header("Authorization", "Bearer " + token)
                .build();
        return chain.proceed(newRequest);
    }
}
