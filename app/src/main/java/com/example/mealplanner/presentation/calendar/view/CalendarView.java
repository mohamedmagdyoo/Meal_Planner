package com.example.mealplanner.presentation.calendar.view;

import com.example.mealplanner.data.calendarMeals.model.CalendarMeal;
import com.example.mealplanner.data.favMeals.model.meal.MealDto;

import java.util.List;

public interface CalendarView {
    void setData(List<CalendarMeal> data);
    void setMealInfo(MealDto mealDto);
    void mealDeleted();
    void onDeleteMealFromCalendar(CalendarMeal calendarMeal);
    void onClickOnCalendarMeal(String mealName);
}
