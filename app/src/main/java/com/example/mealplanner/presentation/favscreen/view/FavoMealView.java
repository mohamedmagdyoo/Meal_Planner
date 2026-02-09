package com.example.mealplanner.presentation.favscreen.view;

import com.example.mealplanner.data.favMeals.model.meal.Meal;
import com.example.mealplanner.data.favMeals.model.meal.MealDto;

import java.util.List;

public interface FavoMealView {
    void setData(List<Meal> data);
    void noDataInDB();
    void noInternetError();
    void setMeal(MealDto mealDTO);
}
