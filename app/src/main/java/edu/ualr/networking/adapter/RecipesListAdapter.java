package edu.ualr.networking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.ualr.networking.R;
import edu.ualr.networking.model.Recipe;

/**
 * Created by irconde on 2019-10-25.
 */
public class RecipesListAdapter extends RecyclerView.Adapter {

    private List<Recipe> mItems;

    public RecipesListAdapter(List<Recipe> items) {
        this.mItems = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Recipe recipe = this.mItems.get(position);
        RecipeViewHolder recipeViewHolder = (RecipeViewHolder) holder;
        recipeViewHolder.ingredients.setText(recipe.getIngredientsDescription());
        recipeViewHolder.title.setText(recipe.getName());
        recipeViewHolder.steps.setText(recipe.getStepsDescription());
    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    public void setItems (List<Recipe> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    private class RecipeViewHolder extends RecyclerView.ViewHolder {

        public TextView ingredients, steps, title;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.recipe_title);
            ingredients = itemView.findViewById(R.id.ingredients_content);
            steps = itemView.findViewById(R.id.steps_content);
        }
    }
}
