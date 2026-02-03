package com.example.mealplanner.data.meal.datasourc.remote;

import com.example.mealplanner.data.meal.model.MealsResponseDto;
import com.example.mealplanner.data.network.RetrofitClint;

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

    public void getAllMeals(RetrofitCallBack callBack){
        api.getAllMeals().enqueue(new Callback<MealsResponseDto>() {
            @Override
            public void onResponse(Call<MealsResponseDto> call, Response<MealsResponseDto> response) {
                if (response.isSuccessful() && response.body() != null){
                    callBack.onSuccess(response.body().getMeals());
                }
                else{
                    callBack.onError("Error while receiving the data");
                }
            }

            @Override
            public void onFailure(Call<MealsResponseDto> call, Throwable t) {
                callBack.onNoInternet();
            }
        });
    }

    public void getRandomMeal(RetrofitCallBack callBack){
        api.getRandomMeal().enqueue(new Callback<MealsResponseDto>() {
            @Override
            public void onResponse(Call<MealsResponseDto> call, Response<MealsResponseDto> response) {
                if (response.isSuccessful() && response.body() != null)
                    callBack.onSuccess(response.body().getMeals());
                else {
                    callBack.onError("Error");
                }
            }

            @Override
            public void onFailure(Call<MealsResponseDto> call, Throwable t) {
                callBack.onNoInternet();
            }
        });
    }
}
