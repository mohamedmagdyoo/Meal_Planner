package com.example.mealplanner.presentation.mealDetailScreen.presenter;

import android.content.Context;

import com.example.mealplanner.data.meal.MealRepository;
import com.example.mealplanner.data.meal.model.meal.GetIngredients;
import com.example.mealplanner.data.meal.model.meal.Ingredient;
import com.example.mealplanner.data.meal.model.meal.Meal;
import com.example.mealplanner.data.meal.model.meal.MealDto;
import com.example.mealplanner.presentation.mealDetailScreen.view.MealDetailsScreen;
import com.example.mealplanner.presentation.mealDetailScreen.view.MealDetailsView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MealDetailsPresenterIMP implements MealDetailsPresenter {

    private MealDetailsView view;
    private MealRepository repo;

    public MealDetailsPresenterIMP(MealDetailsScreen view, Context context) {
        repo = new MealRepository(context);
        this.view = view;
    }

    private List<Ingredient> ingredientsList;

    @Override
    public void getMealIngredients(MealDto mealDto) {

        if (ingredientsList != null && !ingredientsList.isEmpty()) {
            view.setMealIngredients(ingredientsList);
            return;
        }

        ingredientsList = GetIngredients.getAllIngredients(mealDto);

        view.setMealIngredients(ingredientsList);
    }

    @Override
    public void addToFavMeals(MealDto mealDto) {
        repo.addMealToFavMeals(mapToMeal(mealDto));
        view.mealAddedToFav();
    }

    private Meal mapToMeal(MealDto mealDto) {
        Meal meal =
                new Meal(mealDto.getMealId(),
                        mealDto.getMealName(),
                        mealDto.getCategory(),
                        mealDto.getArea(), mealDto.getInstructions(), mealDto.getMealImage(), mealDto.getYoutubeURL());

        return meal;
    }
}
