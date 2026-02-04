package com.example.mealplanner.presentation.favscreen.presenter;

import com.example.mealplanner.data.meal.model.Meal;

public interface FavoMealPresenter {
    void getFavMeals();

    void deleteFromFavMeals(Meal meal);
}
