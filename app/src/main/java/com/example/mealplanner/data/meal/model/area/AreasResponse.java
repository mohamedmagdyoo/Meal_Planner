package com.example.mealplanner.data.meal.model.area;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AreasResponse {

    @SerializedName("meals")
    private List<AreaDto> areas;

    public List<AreaDto> getAreas() {
        return areas;
    }
}
