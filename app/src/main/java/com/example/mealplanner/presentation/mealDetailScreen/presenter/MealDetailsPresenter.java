package com.example.mealplanner.presentation.mealDetailScreen.presenter;

import com.example.mealplanner.data.meal.model.Meal;
import com.example.mealplanner.data.meal.model.MealDto;

public interface MealDetailsPresenter {
    void getMealIngredients(MealDto mealDto);
    void addToFavMeals(MealDto mealDto);
}
