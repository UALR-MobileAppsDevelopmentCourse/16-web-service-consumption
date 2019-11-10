package edu.ualr.networking.network;

import java.io.IOException;

import edu.ualr.networking.network.session.Session;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by irconde on 2019-10-26.
 */

public class AuthenticationInterceptor implements Interceptor {

    private Session session;

    public AuthenticationInterceptor(Session session) {
        this.session = session;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request newRequest  = originalRequest.newBuilder()
                .header("Authorization", "Bearer " + this.session.getAuthToken())
                .build();
        return chain.proceed(newRequest);
    }
}
