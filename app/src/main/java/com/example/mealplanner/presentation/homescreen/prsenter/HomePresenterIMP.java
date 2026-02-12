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
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.io.IOException;
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
                        meals = mealsResponseDto.getMeals();

                        if (!meals.isEmpty()) {
                            view.setAllMeals(meals);
                        } else {
                            view.noData();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        handleError(e);
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
                        view.setRandomMeal(mealsResponseDto.getMeals().get(0));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        handleError(e);
                    }
                });
    }
    void handleError(Throwable error) {
        if (error instanceof InstantiationError) {
            Log.e("FavoriteMeals", "InstantiationError: " + error.getMessage(), error);
        } else if (error instanceof FirebaseFirestoreException) {
            Log.e("FavoriteMeals", "Firestore error: " + error.getMessage(), error);
        } else if (error instanceof IOException) {
            Log.e("FavoriteMeals", "Network error: " + error.getMessage(), error);
        } else {
            Log.e("FavoriteMeals", "Unknown error: " + error.getMessage(), error);
        }
    }
}
