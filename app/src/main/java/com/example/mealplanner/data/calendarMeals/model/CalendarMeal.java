package com.example.mealplanner.data.calendarMeals.model;

import com.example.mealplanner.data.favMeals.model.meal.Meal;

public class CalendarMeal extends Meal {
    private String date;

    public CalendarMeal(String date) {
        this.date = date;
    }

    public CalendarMeal(String mealId, String mealName, String category, String area, String instructions, String mealImage, String youtubeURL, String date) {
        super(mealId, mealName, category, area, instructions, mealImage, youtubeURL);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
