package edu.ualr.networking.network;

import java.util.List;

import edu.ualr.networking.model.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by irconde on 2019-10-25.
 */
public interface WebAPI {
    @GET("2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getRecipesFromWeb();
}
