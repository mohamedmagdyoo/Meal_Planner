package com.example.mealplanner.presentation.searchmeal.view;

import com.example.mealplanner.data.meal.model.meal.Meal;
import com.example.mealplanner.data.meal.model.meal.MealDto;
import com.example.mealplanner.presentation.search.view.SearchItem;

import java.util.List;

public interface SearchMealView {
    void setData(List<SearchItem> data);
    void setMealInfo(List<MealDto> mealInfo);
    void onError();
}
