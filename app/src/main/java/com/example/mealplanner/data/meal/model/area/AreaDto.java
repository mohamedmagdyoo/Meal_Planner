package com.example.mealplanner.data.meal.model.area;

import com.google.gson.annotations.SerializedName;

public class AreaDto {

    @SerializedName("strArea")
    private String area;

    public String getArea() {
        return area;
    }
}
