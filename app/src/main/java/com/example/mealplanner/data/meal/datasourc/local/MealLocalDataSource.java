package com.example.mealplanner.data.meal.datasourc.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mealplanner.data.db.AppDataBase;
import com.example.mealplanner.data.meal.model.meal.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

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

}
