package com.example.mealplanner.presentation.mealDetailScreen.presenter;

import com.example.mealplanner.data.calendarMeals.model.CalendarMeal;
import com.example.mealplanner.data.favMeals.model.meal.MealDto;
import com.example.mealplanner.presentation.calendar.presenter.CalendarPresenter;

public interface MealDetailsPresenter {
    void getMealIngredients(MealDto mealDto);
    void addToFavMeals(MealDto mealDto);
    void addToCalendarMeals(CalendarMeal calendarMeal);
}
