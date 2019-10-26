package edu.ualr.networking.viewmodel;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import edu.ualr.networking.model.Recipe;
import edu.ualr.networking.repository.Repository;

/**
 * Created by irconde on 2019-10-25.
 */
public class RecipeViewModel extends androidx.lifecycle.ViewModel {
    private Repository mRepository;
    // Proxy that observes several LiveData objects and react on OnChanged events from them.
    private MediatorLiveData<List<Recipe>> recipesListObservable = new MediatorLiveData<>();

    public RecipeViewModel() {
        super();
        mRepository = new Repository();
        //subscribe to Livedata of the repository and pass it along to the view (activity - fragment etc)
        recipesListObservable.addSource(mRepository.getRecipesListObservable(),
                new Observer<List<Recipe>>() {
                    @Override
                    public void onChanged(@Nullable List<Recipe> recipes) {
                        recipesListObservable.setValue(recipes);
                    }
                });
    }

    public void getData(){
        mRepository.fetchData();
    }

    public LiveData<List<Recipe>> getRecipesListObservable() {
        return recipesListObservable;
    }
}
