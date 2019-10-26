package edu.ualr.networking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by irconde on 2019-10-25.
 */
public class Recipe {

    @Expose
    private Integer id;

    public String getName() {
        return name;
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ingredients")
    @Expose
    private List<Ingredient> ingredients = null;
    @SerializedName("steps")
    @Expose
    private List<Step> steps = null;
    @SerializedName("servings")
    @Expose
    private Integer servings;
    @SerializedName("image")
    @Expose
    private String image;

    public String getIngredientsDescription() {
        String ingredientsDescription = "";
        for (Ingredient ingredient : ingredients) {
            ingredientsDescription += String.format("· %s : %f %s\n", ingredient.getIngredient(),
                    ingredient.getQuantity(), ingredient.getMeasure());
        }
        return ingredientsDescription;
    }

    public String getStepsDescription() {
        String stepsDescription = "";
        for (Step step : steps) {
            stepsDescription += String.format("%s\n", step.getDescription());
        }
        return stepsDescription;
    }

}
