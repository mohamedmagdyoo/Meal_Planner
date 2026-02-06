package com.example.mealplanner.data.meal.datasourc.remote;

import com.example.mealplanner.data.meal.model.area.AreasResponse;
import com.example.mealplanner.data.meal.model.category.CategoriesResponse;
import com.example.mealplanner.data.meal.model.ingredient.IngredientsResponse;
import com.example.mealplanner.data.meal.model.meal.MealsResponseDto;
import com.example.mealplanner.data.network.RetrofitClint;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;

public class MealRemoteDataSource {
    private Retrofit retrofit;
    private MealApiService api;

    public MealRemoteDataSource() {
        retrofit = RetrofitClint.getRetrofitInstance();
        api = retrofit.create(MealApiService.class);
    }

    public Single<MealsResponseDto> getAllMeals() {
        return api.getAllMeals();
    }

    public Single<MealsResponseDto> getRandomMeal() {
        return api.getRandomMeal();
    }

    public Single<CategoriesResponse> getCategorise() {
        return api.getCategorise();
    }

    public Single<AreasResponse> getAreas() {
        return api.getAreas();
    }

    public Single<IngredientsResponse> getIngredients() {
        return api.getIngredients();
    }

    public Single<MealsResponseDto> getMealByName(String mealName){
        return api.getMealByName(mealName);

    }

}
