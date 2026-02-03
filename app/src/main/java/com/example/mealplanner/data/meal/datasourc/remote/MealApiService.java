package com.example.mealplanner.data.meal.datasourc.remote;


import com.example.mealplanner.data.meal.model.MealsResponseDto;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface MealApiService {
    @GET("search.php?f=a")
    Single<MealsResponseDto> getAllMeals();

    @GET("random.php")
    Single<MealsResponseDto> getRandomMeal();
}
