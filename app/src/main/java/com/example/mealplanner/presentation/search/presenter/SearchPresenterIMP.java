package com.example.mealplanner.presentation.search.presenter;

import android.content.Context;
import android.util.Log;

import com.example.mealplanner.data.meal.MealRepository;
import com.example.mealplanner.data.meal.model.area.AreaDto;
import com.example.mealplanner.data.meal.model.category.CategoryDto;
import com.example.mealplanner.data.meal.model.ingredient.IngredientDto;
import com.example.mealplanner.presentation.search.view.SearchFragment;
import com.example.mealplanner.presentation.search.view.SearchItem;
import com.example.mealplanner.presentation.search.view.SearchView;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenterIMP implements SearchPresenter {

    private MealRepository repo;
    private SearchView searchView;
    private List<SearchItem> data;
    private String flag;


    public SearchPresenterIMP(SearchFragment fragment, Context context) {
        repo = new MealRepository(context);
        searchView = fragment;
    }

    @Override
    public void getAllCategories() {
        repo.getCategorise()
                .map(this::mapListOfCategoryToListOfItem)
                .subscribe(item -> {
                            data = item;
                            searchView.setData(item, flag = "c");
                        },
                        throwable -> handleError(throwable));
    }

    public void getAllCountries() {
        repo.getAreas()
                .map(this::mapListOfCountriesToListOfItem)
                .subscribe(item -> {
                            data = item;
                            searchView.setData(item, flag = "a");
                        },
                        throwable -> handleError(throwable)
                );
    }


    public void getAllIngredients() {
        repo.getIngredients()
                .map(this::mapListOfIngredientsToListOfItem)
                .subscribe(
                        item -> {
                            data = item;
                            searchView.setData(item, flag = "i");
                        },
                        throwable -> handleError(throwable)
                );
    }

    private void handleError(Throwable throwable) {
        searchView.onError(throwable.getMessage());
    }

    private List<SearchItem> mapListOfCategoryToListOfItem(List<CategoryDto> listCategoryDto) {
        List<SearchItem> temp = new ArrayList<>();
        for (CategoryDto val : listCategoryDto) {
            SearchItem item = new SearchItem(val.getIdCategory(), val.getStrCategory(), val.getStrCategoryThumb());
            temp.add(item);
        }
        return temp;
    }

    private List<SearchItem> mapListOfIngredientsToListOfItem(List<IngredientDto> listCategoryDto) {
        List<SearchItem> temp = new ArrayList<>();
        for (IngredientDto val : listCategoryDto) {
            SearchItem item = new SearchItem(val.getId(), val.getName(), val.getThumbUrl());
            temp.add(item);
        }
        return temp;
    }

    private List<SearchItem> mapListOfCountriesToListOfItem(List<AreaDto> listCategoryDto) {
        List<SearchItem> temp = new ArrayList<>();
        for (AreaDto val : listCategoryDto) {
            SearchItem item = new SearchItem(null, val.getArea(), null); // null till now
            temp.add(item);
        }
        return temp;
    }

    public void filterer(String query) {
        List<SearchItem> temp = new ArrayList<>();

        if (data != null) {
            for (SearchItem val : data) {
                if (val.getItemName().startsWith(query))
                    temp.add(val);
            }
        }
        searchView.setData(temp, flag);
    }


}
