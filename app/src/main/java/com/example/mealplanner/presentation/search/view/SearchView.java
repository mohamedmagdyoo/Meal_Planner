package com.example.mealplanner.presentation.search.view;

import java.util.List;

public interface SearchView {

    void setData(List<SearchItem> data, String flag);
    void onError(String message);
}
