package com.example.mealplanner.data.meal.model.category;

import java.util.List;

public class CategoriesResponse {
    private List<CategoryDto> categories;

    public CategoriesResponse() {}

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }
}
