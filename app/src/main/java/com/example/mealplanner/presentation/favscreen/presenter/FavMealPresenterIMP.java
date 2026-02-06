package com.example.mealplanner.presentation.favscreen.presenter;


import android.content.Context;

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

        repository.getAllFavMeals().observe((FavoriteFragment)view,data ->{
            if (! data.isEmpty())
                view.setData(data);
            else
                view.noDataInDB();

        });
    }

    @Override
    public void deleteFromFavMeals(Meal meal) {
        repository.deleteMealFromFavMeals(meal);

    }
}
