package com.example.mealplanner.data.favMeals.model.meal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "MEAL")
public class Meal {
    @PrimaryKey
    @NotNull
    private String mealId;

    @ColumnInfo(name = "Name")
    private String mealName;
    @ColumnInfo(name = "Category")
    private String category;
    @ColumnInfo(name = "Area")
    private String area;
    @ColumnInfo(name = "Instructions")
    private String instructions;
    @ColumnInfo(name = "ImageUrl")
    private String mealImage;
    @ColumnInfo(name = "YoutubeUrl")
    private String youtubeURL;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getMealImage() {
        return mealImage;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public void setYoutubeURL(String youtubeURL) {
        this.youtubeURL = youtubeURL;
    }

    public Meal() {
    }

    public Meal(String mealId, String mealName, String category, String area, String instructions, String mealImage, String youtubeURL) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.mealImage = mealImage;
        this.youtubeURL = youtubeURL;
    }
}
