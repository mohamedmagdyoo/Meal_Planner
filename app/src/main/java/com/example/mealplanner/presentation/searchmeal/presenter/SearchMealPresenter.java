package com.example.mealplanner.presentation.searchmeal.presenter;

public interface SearchMealPresenter {
    void getAllMeals(String key, String flag);
    void getMealInfo(String mealId);
}
