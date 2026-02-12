package com.example.mealplanner.data.calendarMeals.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.mealplanner.data.favMeals.model.meal.Meal;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "CalenderMeals",
        primaryKeys = {"mealId", "day"}
)
public class CalendarMeal {
    @NotNull
    private String mealId;
    @ColumnInfo(name = "Name")
    private String mealName;
    @ColumnInfo(name = "ImageUrl")
    private String mealImage;
    @ColumnInfo(name = "day")
    private long day;

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealImage() {
        return mealImage;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }

    public CalendarMeal() {
    }

    public CalendarMeal(String mealId, String mealName, String mealImage, long day) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealImage = mealImage;
        this.day = day;
    }
}
