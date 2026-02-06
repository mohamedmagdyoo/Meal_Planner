package com.example.mealplanner.presentation.favscreen.presenter;

import com.example.mealplanner.data.meal.model.meal.Meal;

public interface FavoMealPresenter {
    void getFavMeals();

    void deleteFromFavMeals(Meal meal);
}
