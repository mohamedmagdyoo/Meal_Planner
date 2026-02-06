package com.example.mealplanner.presentation.favscreen.view;

import com.example.mealplanner.data.meal.model.meal.Meal;

import java.util.List;

public interface FavoMealView {
    void setData(List<Meal> data);
    void noDataInDB();
}
