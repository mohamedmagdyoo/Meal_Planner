package com.example.mealplanner.data.favMeals.datasourc.remote;

import android.content.Context;

import com.example.mealplanner.data.favMeals.model.area.AreasResponse;
import com.example.mealplanner.data.favMeals.model.category.CategoriesResponse;
import com.example.mealplanner.data.favMeals.model.ingredient.IngredientsResponse;
import com.example.mealplanner.data.favMeals.model.meal.Meal;
import com.example.mealplanner.data.favMeals.model.meal.MealsResponseDto;
import com.example.mealplanner.data.network.RetrofitClint;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;

public class MealRemoteDataSource {
    private Retrofit retrofit;
    private MealApiService api;
    private FavMealsFirebaseStoreService firebaseFirestoreService;

    public MealRemoteDataSource(Context context) {
        retrofit = RetrofitClint.getRetrofitInstance();
        api = retrofit.create(MealApiService.class);
        firebaseFirestoreService = new FavMealsFirebaseStoreService(context);
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

    public Single<MealsResponseDto> getMealByName(String mealName) {
        return api.getMealByName(mealName);
    }

    public void addFavMealToFirestore(Meal meal) {
        firebaseFirestoreService.addFavMeal(meal);
    }

    public void deleteFavMealFromFirestore(String mealId) {
        firebaseFirestoreService.deleteFavMeal(mealId);
    }
}
