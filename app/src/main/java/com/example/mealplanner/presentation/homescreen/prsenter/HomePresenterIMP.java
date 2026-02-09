package com.example.mealplanner.presentation.homescreen.prsenter;


import android.content.Context;
import android.util.Log;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import com.example.mealplanner.data.favMeals.MealRepository;
import com.example.mealplanner.data.favMeals.model.meal.MealDto;
import com.example.mealplanner.data.favMeals.model.meal.MealsResponseDto;
import com.example.mealplanner.presentation.homescreen.view.HomeScreen;
import com.example.mealplanner.presentation.homescreen.view.MealView;

import java.util.List;


public class HomePresenterIMP implements HomePresenter {

    private MealRepository repository;
    private MealView view;
    private List<MealDto> meals;

    public HomePresenterIMP(HomeScreen homeScreen, Context context) {
        repository = new MealRepository(context);
        view = homeScreen;
    }

    @Override
    public void getAllMeals() {

        if (meals != null && !meals.isEmpty()) {
            view.setAllMeals(meals);
            return;
        }

        Single<MealsResponseDto> mealsResponse = repository.getAllMeals();

        mealsResponse
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<MealsResponseDto>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull MealsResponseDto mealsResponseDto) {
                        Log.d("asd -->", "onSuccess: data here (all meals)");
                        meals = mealsResponseDto.getMeals();

                        if (!meals.isEmpty()) {
                            view.setAllMeals(meals);
                        }
                        else{
                            view.noData();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("asd -->", "onError: ");
                        view.noData();
                    }
                });
    }

    @Override
    public void getRandomMeal() {
        Single<MealsResponseDto> mealsResponse = repository.getRandomMeal();

        mealsResponse
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<MealsResponseDto>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@NonNull MealsResponseDto mealsResponseDto) {
                        Log.d("asd -->", "onSuccess: data here (random meal)");

                        view.setRandomMeal(mealsResponseDto.getMeals().get(0));

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("asd -->", "onError: ");

                    }
                });
    }
}
