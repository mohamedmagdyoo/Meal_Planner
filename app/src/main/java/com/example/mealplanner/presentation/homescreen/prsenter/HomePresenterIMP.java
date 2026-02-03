package com.example.mealplanner.presentation.homescreen.prsenter;

import com.example.mealplanner.data.meal.MealRepository;
import com.example.mealplanner.data.meal.datasourc.remote.RetrofitCallBack;
import com.example.mealplanner.data.meal.model.MealDto;
import com.example.mealplanner.presentation.homescreen.view.HomeScreen;
import com.example.mealplanner.presentation.homescreen.view.MealView;

import java.util.List;

public class HomePresenterIMP implements HomePresenter {

    private MealRepository repository;
    private MealView view;

    public HomePresenterIMP(HomeScreen homeScreen) {
        repository = new MealRepository();
        view = homeScreen;
    }

    @Override
    public void getAllMeals() {
        repository.getAllMeals(new RetrofitCallBack() {
            @Override
            public void onSuccess(List<MealDto> data) {
                view.setAllMeals(data);
            }

            @Override
            public void onError(String errorMessage) {

            }

            @Override
            public void onNoInternet() {

            }
        });
    }

    @Override
    public void getRandomMeal() {
        repository.getRandomMeal(new RetrofitCallBack() {
            @Override
            public void onSuccess(List<MealDto> data) {
                view.setRandomMeal(data.get(0));
            }

            @Override
            public void onError(String errorMessage) {

            }

            @Override
            public void onNoInternet() {

            }
        });
    }
}
