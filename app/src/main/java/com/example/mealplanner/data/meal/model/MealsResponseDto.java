package com.example.mealplanner.data.meal.model;

import java.util.List;

public class MealsResponseDto {

    private List<MealDto> meals;

    public List<MealDto> getMeals() {
        return meals;
    }

    public void setMeals(List<MealDto> meals) {
        this.meals = meals;
    }
}
