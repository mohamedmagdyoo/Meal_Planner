package com.example.mealplanner.data.calendarMeals.dataSource.remote;

import android.content.Context;

import com.example.mealplanner.data.calendarMeals.model.CalendarMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

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

    public Single<List<CalendarMeal>> fetchAllCalendarMeals(){
        return calendarMealsService.fetchCalendarMeals();
    }
}
