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
    private final static String AUTH_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiI4YTFlZ" +
            "GFlOC1hZDMwLTRlNGItYWM4OC0zOTg5YjFkYjc3ZjYiLCJleHAiOjE1NzI2Mjc3NTYsImlhdCI6MTU3MjYyMDU1N" +
            "iwiaXNzIjoiYmlmcm9zdCIsInN1YiI6ImFwazpmMTM3ZWU1NC1lY2U0LTQzYWYtYTgwNi00MTkxMmJkNTk5NmIifQ." +
            "12Hbb-ySwXrbZE7TlwhmuZpIKuH0l97Hct713Wm4P6BBZKz2T2ZEpHvbZDGMNys3-DGgPtk5cp5zMAczolHrw7SAQpd" +
            "wRZAgAnY6JDn770e4XSsSzx3Jr_6ORn8-NfqEGsP9vObXI3KdEoQ5M6BcN0OOlTBkGgmHNNzE5pJ2x00WA3GBFoqLx4" +
            "YyHW3AlqfCHGrnLE08swEpcU2QozY_yQjEhdFa2VRLAwgetubldob6sNqKtCu9SSVfzoXrvbJCiayMekjkWh2GnP5BBmd" +
            "ZPiRoiy9xry3qJWJJemVzrj85etenLYMCimvWJ6PAOGWVERSbaWARf6TUkP3cOnTKSw";

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
