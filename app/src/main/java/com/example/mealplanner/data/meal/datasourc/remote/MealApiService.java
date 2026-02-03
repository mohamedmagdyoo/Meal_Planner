package com.example.mealplanner.data.meal.datasourc.remote;

import com.example.mealplanner.data.meal.model.MealsResponseDto;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealApiService {
    @GET("search.php?f=a")

    Call<MealsResponseDto> getAllMeals();

    @GET("random.php")
    Call<MealsResponseDto> getRandomMeal();
}
