package com.example.mealplanner.presentation.homescreen.view;

import com.example.mealplanner.data.favMeals.model.meal.MealDto;

import java.util.List;

public interface MealView {
    void setAllMeals(List<MealDto> data);
    void setRandomMeal(MealDto randomMeal);
    void noData();
}