package com.example.mealplanner.data.calendarMeals.dataSource.remote;

import android.content.Context;

import com.example.mealplanner.data.calendarMeals.model.CalendarMeal;

public class CalendarMealRemoteDataSource {
    private CalendarMealsService calendarMealsService;

    public CalendarMealRemoteDataSource(Context context) {
        calendarMealsService = new CalendarMealsService(context);
    }

    public void addCalendarMeal(CalendarMeal calendarMeal) {
        calendarMealsService.addCalendarMeal(calendarMeal);
    }


    public void deleteCalendarMeal(CalendarMeal calendarMeal) {
        calendarMealsService.deleteCalendarMeal(calendarMeal);
    }
}
