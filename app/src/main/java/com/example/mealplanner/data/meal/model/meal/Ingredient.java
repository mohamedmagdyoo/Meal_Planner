package com.example.mealplanner.data.meal.model.meal;

public class Ingredient {
    private String name;
    private String measure;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getUrl() {
        return "https://www.themealdb.com/images/ingredients/" + getName().replace(" ","%20") +".png";
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Ingredient(String name, String measure, String url) {
        this.name = name;
        this.measure = measure;
        this.url = url;
    }
}
