package com.example.mealplanner.presentation.mealDetailScreen.view;

import com.example.mealplanner.data.favMeals.model.meal.Ingredient;

import java.util.List;

public interface MealDetailsView {
    void setMealIngredients(List<Ingredient> data);
    void mealAddedToFav();
}
