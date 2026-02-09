package com.example.mealplanner.presentation.mealDetailScreen.presenter;

import com.example.mealplanner.data.favMeals.model.meal.MealDto;

public interface MealDetailsPresenter {
    void getMealIngredients(MealDto mealDto);
    void addToFavMeals(MealDto mealDto);
}
