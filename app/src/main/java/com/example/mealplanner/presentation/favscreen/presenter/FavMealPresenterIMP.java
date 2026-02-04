package com.example.mealplanner.presentation.favscreen.presenter;


import android.content.Context;
import android.util.Log;

import com.example.mealplanner.data.meal.MealRepository;
import com.example.mealplanner.data.meal.model.Meal;
import com.example.mealplanner.presentation.favscreen.view.FavoriteFragment;
import com.example.mealplanner.presentation.favscreen.view.FavoMealView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavMealPresenterIMP implements FavoMealPresenter {

    private FavoMealView view;
    private MealRepository repository;

    public FavMealPresenterIMP(FavoriteFragment favoriteFragment, Context context) {
        repository = new MealRepository(context);
        view = favoriteFragment;
    }

    @Override
    public void getFavMeals() {
        repository.getAllFavMeals().observe((FavoriteFragment)view,data ->{
            view.setData(data);
        });
    }

    @Override
    public void deleteFromFavMeals(Meal meal) {
        repository.deleteMealFromFavMeals(meal);

    }
}
