package com.example.mealplanner.presentation.homescreen.prsenter;



import android.util.Log;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import com.example.mealplanner.data.meal.MealRepository;
import com.example.mealplanner.data.meal.model.MealsResponseDto;
import com.example.mealplanner.presentation.homescreen.view.HomeScreen;
import com.example.mealplanner.presentation.homescreen.view.MealView;


public class HomePresenterIMP implements HomePresenter {

    private MealRepository repository;
    private MealView view;

    public HomePresenterIMP(HomeScreen homeScreen) {
        repository = new MealRepository();
        view = homeScreen;
    }

    @Override
    public void getAllMeals() {
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
                view.setAllMeals(mealsResponseDto.getMeals());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("asd -->", "onError: ");

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
