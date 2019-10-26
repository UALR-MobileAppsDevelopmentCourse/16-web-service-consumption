package edu.ualr.networking.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by irconde on 2019-10-25.
 */
public class RetrofitModule {

    private static Retrofit retrofitClient;
    final static String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/";

    private RetrofitModule() {}

    private static Retrofit getClient() {
        if (retrofitClient == null) {
            retrofitClient = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitClient;
    }

    public static WebAPI getRecipesService() {
        return getClient().create(WebAPI.class);
    }

}
