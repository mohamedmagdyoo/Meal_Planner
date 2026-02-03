package com.example.mealplanner.data.meal.datasourc.remote;

import android.database.Observable;

import com.example.mealplanner.data.meal.model.MealsResponseDto;
import com.example.mealplanner.data.network.RetrofitClint;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MealRemoteDataSource {
    private Retrofit retrofit;
    private MealApiService api;

    public MealRemoteDataSource(){
        retrofit = RetrofitClint.getRetrofitInstance();
        api = retrofit.create(MealApiService.class);
    }

    public Single<MealsResponseDto> getAllMeals(){
       return api.getAllMeals();
    }

    public Single<MealsResponseDto> getRandomMeal(){
       return api.getRandomMeal();
    }
}
