package com.example.mealplanner.data.meal;

import com.example.mealplanner.data.meal.datasourc.remote.MealRemoteDataSource;
import com.example.mealplanner.data.meal.datasourc.remote.RetrofitCallBack;

public class MealRepository {
    private MealRemoteDataSource remote;

    public MealRepository(){
        remote = new MealRemoteDataSource();
    }

    public void getAllMeals(RetrofitCallBack callBack){
        remote.getAllMeals(callBack);
    }

    public void getRandomMeal(RetrofitCallBack callBack){
        remote.getRandomMeal(callBack);
    }

}
