package com.example.mealplanner.presentation.favscreen.view;

import com.example.mealplanner.data.favMeals.model.meal.Meal;

public interface OnFavMealsAdapterCallBack {
    void onDeleteFromFavMeals(Meal meal);
    void onItemViewClick(String mealName);
}
