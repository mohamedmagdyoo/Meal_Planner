package com.example.mealplanner.presentation.search.view;

import com.example.mealplanner.data.meal.model.category.CategoryDto;

import java.util.List;

public interface SearchView {

    void setData(List<SearchItem> data, String flag);
    void onError(String message);
}
