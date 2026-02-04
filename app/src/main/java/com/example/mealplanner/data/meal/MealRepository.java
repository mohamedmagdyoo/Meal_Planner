package com.example.mealplanner.data.meal;

import android.content.Context;
import android.database.Observable;
import android.hardware.lights.LightsManager;

import androidx.lifecycle.LiveData;
import androidx.navigation.CollectionNavType;

import com.example.mealplanner.data.meal.datasourc.local.MealLocalDataSource;
import com.example.mealplanner.data.meal.datasourc.remote.MealRemoteDataSource;
import com.example.mealplanner.data.meal.datasourc.remote.RetrofitCallBack;
import com.example.mealplanner.data.meal.model.Meal;
import com.example.mealplanner.data.meal.model.MealsResponseDto;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class MealRepository {
    private MealRemoteDataSource remote;
    private MealLocalDataSource local;

    public MealRepository(Context context){
        local = new MealLocalDataSource(context);
        remote = new MealRemoteDataSource();
    }

    public Single<MealsResponseDto> getAllMeals(){
        return remote.getAllMeals();
    }

    public Single<MealsResponseDto> getRandomMeal(){
        return remote.getRandomMeal();
    }


    public void addMealToFavMeals(Meal meal){
        local.addMealToFav(meal);
    }

    public void deleteMealFromFavMeals(Meal meal){
        local.deleteFromFavMeals(meal);
    }

    public LiveData<List<Meal>> getAllFavMeals(){
        return local.getAllFavMeals();
    }

}
