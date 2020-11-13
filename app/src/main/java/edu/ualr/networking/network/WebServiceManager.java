package edu.ualr.networking.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by irconde on 2019-10-25.
 */

public class WebServiceManager {

    private static WebAPI service;
    private final static String BASE_URL = "https://8a1edae8-ad30-4e4b-ac88-3989b1db77f6.app.jexia.com/";
    private final static String AUTH_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiI4YTFlZGFlOC1hZDMwLTRlNGItYWM4OC0zOTg5YjFkYjc3ZjYiLCJleHAiOjE1NzM0MTU1MDksImlhdCI6MTU3MzQwODMwOSwiaXNzIjoiYmlmcm9zdCIsInN1YiI6ImFwazpmMTM3ZWU1NC1lY2U0LTQzYWYtYTgwNi00MTkxMmJkNTk5NmIifQ.eaVcchvLnQCMTcZqTXiwdwxMoMMCMQUygeRPj5GalNpV9v6WnyUIhSEmWuf5y8utyZAPSP4_PuX7EI98GUp1Du7tbgE1pJnLiImv8sc0_gY_W5Hda-XGiQS-VjqnT9oDvJYKozjOPwzSuGfBnSMe6dDvPxGr-CZNJbYOALsRAXuX3J4gB5kBA6wH8W6maP0hYe5HwaZzYM2hqD4jvY0ogxWel1pjQWwySa-zDaWXeef-7wpt6TRRIMBzF0EmdxYf5fgXd8Q8xVPaAduef4V6YFBYHL1twume3-I708OygSea3W21GUjjvi0GcMtiGMnn5VDJvyZ2Gx3aLEUwrpgPgw";

    private WebServiceManager() {}

    public static WebAPI getService() {
        if (service == null) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
            // TODO 01. The mail goal is create a new class in charge of refreshing the
            //  authorization token once it has expired. TokenRefreshAuthenticator
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new AuthenticationInterceptor(AUTH_TOKEN))
                    .addInterceptor(loggingInterceptor)
                    .build();
            service = new retrofit2.Retrofit.Builder()
                    .client(httpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(WebAPI.class);
        }
        return service;
    }

}
