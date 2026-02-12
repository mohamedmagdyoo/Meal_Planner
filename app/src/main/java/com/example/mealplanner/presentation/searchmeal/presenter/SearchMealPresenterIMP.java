package com.example.mealplanner.presentation.searchmeal.presenter;

import android.content.Context;
import android.util.Log;

import com.example.mealplanner.data.favMeals.MealRepository;
import com.example.mealplanner.data.favMeals.model.meal.GetIngredients;
import com.example.mealplanner.data.favMeals.model.meal.Ingredient;
import com.example.mealplanner.data.favMeals.model.meal.MealDto;
import com.example.mealplanner.presentation.search.view.SearchItem;
import com.example.mealplanner.presentation.searchmeal.view.SearchMeal;
import com.example.mealplanner.presentation.searchmeal.view.SearchMealView;

import java.util.ArrayList;
import java.util.List;

public class SearchMealPresenterIMP implements SearchMealPresenter {
    private SearchMealView view;
    private MealRepository repository;
    private String key;
    private String flag;
    private List<Ingredient> ingredientsList;


    public SearchMealPresenterIMP(SearchMeal fragment, Context context) {
        repository = new MealRepository(context);
        view = fragment;
    }

    @Override
    public void getAllMeals(String key, String flag) {
        this.key = key;
        this.flag = flag;


        repository.getAllMeals()
                .map(obj -> obj.getMeals())
                .map(list -> filter(list))
                .map(this::mapToSearchMealItem)
                .subscribe(
                        list -> {
                            Log.d("asd -->", "getAllMeals size = " + list.size());
                            view.setData(list);
                        },
                        throwable -> {
                            Log.e("asd -->", "getAllMeals error", throwable);
                        }
                );

    }

    @Override
    public void getMealInfo(String mealName) {
        repository.getMealByName(mealName)
                .subscribe(list -> view.setMealInfo(list),
                        error -> {
                            view.onError();
                            Log.d("asd -->", "getMealInfo: " + error.getMessage());
                        });
    }

    private List<MealDto> filter(List<MealDto> data) {

        List<MealDto> temp = new ArrayList<>();

        for (MealDto val : data) {

            if ("c".equals(flag)) {
                if (key.equals(val.getCategory())) {
                    temp.add(val);
                }

            } else if ("a".equals(flag)) {
                if (key.equals(val.getArea())) {
                    temp.add(val);
                }
            }else if ("i".equals(flag)){
                if (isTheMealContaintThatIngredients(val))
                    temp.add(val);
            }
        }
        return temp;
    }

    boolean isTheMealContaintThatIngredients(MealDto mealDto){
        List<Ingredient> ingredients = GetIngredients.getAllIngredients(mealDto);

        for(Ingredient val : ingredients){
            if (val.getName().equals(key))
                return true;
        }
        return false;
    }


    private List<SearchItem> mapToSearchMealItem(List<MealDto> data) {
        List<SearchItem> temp = new ArrayList<>();

        for (MealDto val : data) {
            SearchItem item = new SearchItem(val.getMealId(), val.getMealName(), val.getMealImage());
            temp.add(item);
        }

        return temp;
    }

}
