package edu.ualr.networking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import edu.ualr.networking.adapter.RecipesListAdapter;
import edu.ualr.networking.model.Recipe;
import edu.ualr.networking.viewmodel.RecipeViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecipesListView;
    private RecipesListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecipesListView = findViewById(R.id.recipes_list_rv);
        mAdapter = new RecipesListAdapter(new ArrayList<Recipe>());
        mRecipesListView.setAdapter(mAdapter);
        mRecipesListView.setLayoutManager(new LinearLayoutManager(this));
        final RecipeViewModel recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        recipeViewModel.getRecipesListObservable().observe(MainActivity.this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                mAdapter.setItems(recipes);
            }
        });
        recipeViewModel.getData();
    }
}
