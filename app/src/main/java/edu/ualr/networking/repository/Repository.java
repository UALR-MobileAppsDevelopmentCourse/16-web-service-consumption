package edu.ualr.networking.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import edu.ualr.networking.model.Recipe;
import edu.ualr.networking.network.RetrofitModule;
import edu.ualr.networking.network.WebAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by irconde on 2019-10-25.
 */
public class Repository {

    private static final String TAG = Repository.class.getSimpleName();
    private MutableLiveData<List<Recipe>> recipesListObservable = new MutableLiveData<>();
    private WebAPI recipeApi;

    public Repository() {
        recipeApi = RetrofitModule.getRecipesService();
    }

    public void fetchData() {
        List<Recipe> loadingList = null;
        if (recipesListObservable.getValue()!=null){
            loadingList=recipesListObservable.getValue();
        } else {
            getRecipesFromWeb();
        }
    }

    public MutableLiveData<List<Recipe>> getRecipesListObservable() {
        return recipesListObservable;
    }

    private void getRecipesFromWeb(){
        Log.d(TAG, "getRecipesFromWeb");
        recipeApi.getRecipesFromWeb().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (response.isSuccessful()) {
                    setRecipesListObservableData(response.body());
                } else {
                    switch (response.code()) {
                        case 404:
                            Log.d(TAG, "not found");
                            break;
                        case 500:
                            Log.d(TAG, "not logged in or server broken");
                            break;
                        default:
                            Log.d(TAG, "unknown error");
                            break;
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    /**
     * This method changes the observable's LiveData data without changing the status
     * @param mRecipesList the data that need to be updated
     */
    private void setRecipesListObservableData(List<Recipe> mRecipesList) {
        Log.d(TAG, "setRecipesListObservableData");
        recipesListObservable.setValue(mRecipesList);
    }


}
