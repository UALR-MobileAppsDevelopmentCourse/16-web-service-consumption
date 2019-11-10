package edu.ualr.networking.network;

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
    // TODO 28. Define a constant to hold the authentication token
    // Be careful this token expires
    private final static String AUTH_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiI4YTFlZGFlOC1hZDMwLTRlNGItYWM4OC0zOTg5YjFkYjc3ZjYiLCJleHAiOjE1NzM0MTU1MDksImlhdCI6MTU3MzQwODMwOSwiaXNzIjoiYmlmcm9zdCIsInN1YiI6ImFwazpmMTM3ZWU1NC1lY2U0LTQzYWYtYTgwNi00MTkxMmJkNTk5NmIifQ.eaVcchvLnQCMTcZqTXiwdwxMoMMCMQUygeRPj5GalNpV9v6WnyUIhSEmWuf5y8utyZAPSP4_PuX7EI98GUp1Du7tbgE1pJnLiImv8sc0_gY_W5Hda-XGiQS-VjqnT9oDvJYKozjOPwzSuGfBnSMe6dDvPxGr-CZNJbYOALsRAXuX3J4gB5kBA6wH8W6maP0hYe5HwaZzYM2hqD4jvY0ogxWel1pjQWwySa-zDaWXeef-7wpt6TRRIMBzF0EmdxYf5fgXd8Q8xVPaAduef4V6YFBYHL1twume3-I708OygSea3W21GUjjvi0GcMtiGMnn5VDJvyZ2Gx3aLEUwrpgPgw";

    private WebServiceManager() {}

    // TODO 08. We define a method called getService to create a new instance of the client API if
    //  hasn't been created yet
    public static WebAPI getService() {
        if (service == null) {

            // TODO 26. We create a logging interceptor to log http messages internally handled by Retrofit
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            // TODO 26.01 We use the BODY log level to get as much information as possible
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    // TODO 28. Add the authentication interceptor to the Http client
                    .addInterceptor(new AuthenticationInterceptor(AUTH_TOKEN))
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
        }
        return service;
    }

}
