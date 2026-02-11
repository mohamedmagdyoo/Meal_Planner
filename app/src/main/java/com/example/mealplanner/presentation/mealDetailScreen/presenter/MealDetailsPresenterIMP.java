package com.example.mealplanner.presentation.mealDetailScreen.presenter;

import android.content.Context;
import android.util.Log;

import com.example.mealplanner.data.calendarMeals.CalendarMealRepo;
import com.example.mealplanner.data.calendarMeals.model.CalendarMeal;
import com.example.mealplanner.data.favMeals.MealRepository;
import com.example.mealplanner.data.favMeals.model.meal.GetIngredients;
import com.example.mealplanner.data.favMeals.model.meal.Ingredient;
import com.example.mealplanner.data.favMeals.model.meal.Meal;
import com.example.mealplanner.data.favMeals.model.meal.MealDto;
import com.example.mealplanner.presentation.mealDetailScreen.view.MealDetailsScreen;
import com.example.mealplanner.presentation.mealDetailScreen.view.MealDetailsView;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.io.IOException;
import java.util.List;

public class MealDetailsPresenterIMP implements MealDetailsPresenter {

    private MealDetailsView view;
    private MealRepository repo;
    private CalendarMealRepo calendarMealRepo;

    public MealDetailsPresenterIMP(MealDetailsScreen view, Context context) {
        repo = new MealRepository(context);
        calendarMealRepo = new CalendarMealRepo(context);
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
        repo.addFavMealToFirestore(mapToMeal(mealDto));
        view.mealAddedToFav();
    }

    @Override
    public void addToCalendarMeals(CalendarMeal calendarMeal) {
        // todo add to firestore
        calendarMealRepo.addCalendarMeal(calendarMeal)
                .subscribe(
                        () -> view.mealAddedToFav(),
                        throwable -> handleError(throwable)
                );
        calendarMealRepo.addCalendarMealToFireStore(calendarMeal);
    }

    private Meal mapToMeal(MealDto mealDto) {
        Meal meal =
                new Meal(mealDto.getMealId(),
                        mealDto.getMealName(),
                        mealDto.getCategory(),
                        mealDto.getArea(), mealDto.getInstructions(), mealDto.getMealImage(), mealDto.getYoutubeURL());

        return meal;
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
