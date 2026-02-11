package com.example.mealplanner.data.favMeals.datasourc.local;

import android.content.Context;
import android.util.Log;

import com.example.mealplanner.data.db.AppDataBase;
import com.example.mealplanner.data.favMeals.model.meal.Meal;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealLocalDataSource {
    private MealDAO mealDAO;
    private AppDataBase room;

    public MealLocalDataSource(Context context) {
        room = AppDataBase.getInstance(context);
        mealDAO = room.getMealDAO();
    }

    public Flowable<List<Meal>> getAllFavMeals() {
        return mealDAO.getAllFavMeals();
    }

    public void deleteFromFavMeals(Meal meal) {
        new Thread(() -> {
            mealDAO.deleteFromFavMeals(meal);

        }).start();
    }

    public void addMealToFav(Meal meal) {
        new Thread(() -> {
            mealDAO.addToFavMeals(meal);

        }).start();
    }

    public Completable insertAll(List<Meal> meals) {
        return mealDAO.insertAll(meals);
    }


    public void clearAllTables() {
        room.clearAllTables();
    }
}
