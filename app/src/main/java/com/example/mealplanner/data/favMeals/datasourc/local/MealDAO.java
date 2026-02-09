package com.example.mealplanner.data.favMeals.datasourc.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mealplanner.data.favMeals.model.meal.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealDAO {

    @Query("Select * from MEAL")
    Flowable<List<Meal>> getAllFavMeals();
    @Delete
    void deleteFromFavMeals(Meal Meal);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addToFavMeals(Meal meal);

}
