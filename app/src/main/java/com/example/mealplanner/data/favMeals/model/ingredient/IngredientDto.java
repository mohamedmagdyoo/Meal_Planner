package com.example.mealplanner.data.favMeals.model.ingredient;

import com.google.gson.annotations.SerializedName;

public class IngredientDto {

    @SerializedName("idIngredient")
    private String id;

    @SerializedName("strIngredient")
    private String name;

    @SerializedName("strDescription")
    private String description;

    @SerializedName("strThumb")
    private String thumbUrl;

    @SerializedName("strType")
    private String type;

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public String getType() {
        return type;
    }
}
