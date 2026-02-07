package com.example.mealplanner.data.meal.datasourc.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mealplanner.data.meal.model.meal.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealDAO {

    @Query("Select * from MEAL")
    Flowable<List<Meal>> getAllFavMeals();
    @Delete
    void deleteFromFavMeals(Meal Meal);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addToFavMeals(Meal meal);

}
