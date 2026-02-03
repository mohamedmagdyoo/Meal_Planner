package com.example.mealplanner.data.meal;

import android.database.Observable;

import com.example.mealplanner.data.meal.datasourc.remote.MealRemoteDataSource;
import com.example.mealplanner.data.meal.datasourc.remote.RetrofitCallBack;
import com.example.mealplanner.data.meal.model.MealsResponseDto;

import io.reactivex.rxjava3.core.Single;

public class MealRepository {
    private MealRemoteDataSource remote;

    public MealRepository(){
        remote = new MealRemoteDataSource();
    }

    public Single<MealsResponseDto> getAllMeals(){
        return remote.getAllMeals();
    }

    public Single<MealsResponseDto> getRandomMeal(){
        return remote.getRandomMeal();
    }

}
