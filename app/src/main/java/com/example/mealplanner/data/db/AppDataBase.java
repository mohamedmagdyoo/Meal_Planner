package com.example.mealplanner.data.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mealplanner.data.favMeals.datasourc.local.MealDAO;
import com.example.mealplanner.data.favMeals.model.meal.Meal;

@Database(entities = {Meal.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract MealDAO getMealDAO();

    private static AppDataBase instance;
    public static AppDataBase getInstance(Context context){
        if (instance == null){
            instance = Room
                    .databaseBuilder(context.getApplicationContext(),AppDataBase.class,"app_db")
                    .build();
        }

        return instance;
    }
    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
