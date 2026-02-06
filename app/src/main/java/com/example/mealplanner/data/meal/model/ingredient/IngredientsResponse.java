package com.example.mealplanner.data.meal.model.ingredient;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class IngredientsResponse {

    @SerializedName("meals")
    private List<IngredientDto> ingredients;

    public List<IngredientDto> getIngredients() {
        return ingredients == null ? Collections.emptyList() : ingredients;
    }
}
