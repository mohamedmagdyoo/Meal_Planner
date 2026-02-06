package com.example.mealplanner.data.meal;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mealplanner.data.meal.datasourc.local.MealLocalDataSource;
import com.example.mealplanner.data.meal.datasourc.remote.MealRemoteDataSource;
import com.example.mealplanner.data.meal.model.area.AreaDto;
import com.example.mealplanner.data.meal.model.area.AreasResponse;
import com.example.mealplanner.data.meal.model.category.CategoriesResponse;
import com.example.mealplanner.data.meal.model.category.CategoryDto;
import com.example.mealplanner.data.meal.model.ingredient.IngredientDto;
import com.example.mealplanner.data.meal.model.ingredient.IngredientsResponse;
import com.example.mealplanner.data.meal.model.meal.Meal;
import com.example.mealplanner.data.meal.model.meal.MealDto;
import com.example.mealplanner.data.meal.model.meal.MealsResponseDto;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealRepository {
    private MealRemoteDataSource remote;
    private MealLocalDataSource local;

    public MealRepository(Context context) {
        local = new MealLocalDataSource(context);
        remote = new MealRemoteDataSource();
    }

    //Remote
    public Single<MealsResponseDto> getAllMeals() {
        return
                remote.getAllMeals()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<MealsResponseDto> getRandomMeal() {
        return remote.getRandomMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<CategoryDto>> getCategorise() {
        return remote.getCategorise()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(obj -> obj.getCategories());
    }

    public Single<List<AreaDto>> getAreas() {
        return remote.getAreas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(obj -> obj.getAreas());
    }

    public Single<List<IngredientDto>> getIngredients() {
        return remote.getIngredients()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(obj -> obj.getIngredients());
    }
    public Single<List<MealDto>> getMealByName(String mealName){
        return remote.getMealByName(mealName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(obj -> obj.getMeals());
    }



    //Local
    public void addMealToFavMeals(Meal meal) {
        local.addMealToFav(meal);
    }

    public void deleteMealFromFavMeals(Meal meal) {
        local.deleteFromFavMeals(meal);
    }

    public LiveData<List<Meal>> getAllFavMeals() {
        return local.getAllFavMeals();
    }


}
