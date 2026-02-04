package com.example.mealplanner.presentation.favscreen.view;

import androidx.lifecycle.LiveData;

import com.example.mealplanner.data.meal.model.Meal;
import com.example.mealplanner.data.meal.model.MealDto;

import java.util.List;

public interface FavoMealView {
    void setData(List<Meal> data);
}
