package edu.ualr.networking.network;

import edu.ualr.networking.network.session.Session;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by irconde on 2019-10-25.
 */

public class WebServiceManager {

    private static WebAPI service;
    private final static String BASE_URL = "https://8a1edae8-ad30-4e4b-ac88-3989b1db77f6.app.jexia.com/";

    private WebServiceManager() {}

    public static WebAPI getService() {
        if (service == null) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

            ApiServiceHolder apiServiceHolder = new ApiServiceHolder();
            Session session = new Session();
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new AuthenticationInterceptor(session))
                    .authenticator(new TokenRefreshAuthenticator(apiServiceHolder,session))
                    .addInterceptor(loggingInterceptor)
                    .build();

            service = new retrofit2.Retrofit.Builder()
                    .client(httpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(WebAPI.class);

            apiServiceHolder.set(service);
        }
        return service;
    }

}
