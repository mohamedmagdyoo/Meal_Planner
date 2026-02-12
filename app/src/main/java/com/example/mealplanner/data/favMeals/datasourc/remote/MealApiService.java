package com.example.mealplanner.data.favMeals.datasourc.remote;


import com.example.mealplanner.data.favMeals.model.area.AreasResponse;
import com.example.mealplanner.data.favMeals.model.category.CategoriesResponse;
import com.example.mealplanner.data.favMeals.model.ingredient.IngredientsResponse;
import com.example.mealplanner.data.favMeals.model.meal.MealsResponseDto;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealApiService {
    @GET("search.php?f=a")
    Single<MealsResponseDto> getAllMeals();

    @GET("search.php")
    Single<MealsResponseDto> getMealByName(
            @Query("s") String mealName
    );

    @GET("random.php")
    Single<MealsResponseDto> getRandomMeal();

    @GET("categories.php")
    Single<CategoriesResponse> getCategorise();

    @GET("list.php?a=list")
    Single<AreasResponse> getAreas();

    @GET("list.php?i=list")
    Single<IngredientsResponse> getIngredients();



}
