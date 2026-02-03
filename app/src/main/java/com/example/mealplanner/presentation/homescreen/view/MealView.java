package com.example.mealplanner.presentation.homescreen.view;

import com.example.mealplanner.data.meal.model.MealDto;

import java.util.List;

public interface MealView {
    void setAllMeals(List<MealDto> data);

    void setRandomMeal(MealDto randomMeal);
}