package edu.ualr.networking.network;

import edu.ualr.networking.network.session.Session;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by irconde on 2019-10-25.
 */

// TODO 05. We create the WebServiceManager class tha will be responsible for creating a new instance
//  of the API client

public class WebServiceManager {

    // TODO 06. API member. We use a singleton pattern to make sure we only have one instance of the API
    private static WebAPI service;
    // TODO 07. Define a constant to hold the base URL of the REST API
    private final static String BASE_URL = "https://8a1edae8-ad30-4e4b-ac88-3989b1db77f6.app.jexia.com/";

    private WebServiceManager() {}

    // TODO 08. We define a method called getService to create a new instance of the client API if
    //  hasn't been created yet
    public static WebAPI getService() {
        if (service == null) {

            // TODO 26. We create a logging interceptor to log http messages internally handled by Retrofit
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            // TODO 26.01 We use the BODY log level to get as much information as possible
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

            ApiServiceHolder apiServiceHolder = new ApiServiceHolder();
            Session session = new Session();
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    // TODO 28. Add the authentication interceptor to the Http client
                    .addInterceptor(new AuthenticationInterceptor(session))
                    .authenticator(new TokenRefreshAuthenticator(apiServiceHolder,session))
                    // TODO 26.02 Add the logging interceptor as last interceptor of the Http client
                    .addInterceptor(loggingInterceptor)
                    .build();

            // TODO 09. We create a new API client based on Retrofit, using GSon for the conversion
            //  of the data exchanged
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
