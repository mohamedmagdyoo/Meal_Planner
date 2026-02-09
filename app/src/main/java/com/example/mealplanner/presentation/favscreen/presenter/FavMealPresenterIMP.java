package com.example.mealplanner.presentation.favscreen.presenter;


import android.content.Context;
import android.util.Log;

import com.example.mealplanner.data.meal.MealRepository;
import com.example.mealplanner.data.meal.model.meal.Meal;
import com.example.mealplanner.presentation.favscreen.view.FavoriteFragment;
import com.example.mealplanner.presentation.favscreen.view.FavoMealView;

public class FavMealPresenterIMP implements FavoMealPresenter {

    private FavoMealView view;
    private MealRepository repository;

    public FavMealPresenterIMP(FavoriteFragment favoriteFragment, Context context) {
        repository = new MealRepository(context);
        view = favoriteFragment;
    }

    @Override
    public void getFavMeals() {

        repository.getAllFavMeals()
                .subscribe(data -> {
                            if (!data.isEmpty())
                                view.setData(data);
                            else
                                view.noDataInDB();
                        },
                        throwable -> {
                            Log.d("asd -->", "getFavMeals: E:" + throwable.getMessage());
                        });
    }

    @Override
    public void deleteFromFavMeals(Meal meal) {
        repository.deleteMealFromFavMeals(meal);
    }

    @Override
    public void getMealByName(String mealName) {
        repository.getMealByName(mealName)
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(0))
                .subscribe(
                        mealDto ->{
                            view.setMeal(mealDto);
                        }
                );
    }
}
