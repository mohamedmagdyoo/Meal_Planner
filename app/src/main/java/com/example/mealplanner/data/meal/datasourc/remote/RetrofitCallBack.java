package com.example.mealplanner.data.meal.datasourc.remote;

import com.example.mealplanner.data.meal.model.meal.MealDto;

import java.util.List;

public interface RetrofitCallBack {
    void onSuccess(List<MealDto> data);
    void onError(String errorMessage);

    void onNoInternet();
}
