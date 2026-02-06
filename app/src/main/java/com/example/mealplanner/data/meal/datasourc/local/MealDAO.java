package com.example.mealplanner.data.meal.datasourc.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mealplanner.data.meal.model.meal.Meal;

import java.util.List;

@Dao
public interface MealDAO {

    @Query("Select * from MEAL")
    LiveData<List<Meal>> getAllFavMeals();
    @Delete
    void deleteFromFavMeals(Meal Meal);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addToFavMeals(Meal meal);

}
