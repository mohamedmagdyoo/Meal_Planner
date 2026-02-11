package com.example.mealplanner.presentation.calendar.presenter;

import com.example.mealplanner.data.calendarMeals.model.CalendarMeal;

import java.util.List;

public interface CalendarPresenter {

    void getAllMealsInDay(long day);
    void deleteMeal(CalendarMeal calendarMeal);
    void getMealByName(String mealName);

//    void addMealToDB(CalendarMeal calendarMeal);
//    void deleteMealFromDB(CalendarMeal calendarMeal);
//    void addMealToDB(CalendarMeal calendarMeal);

}
