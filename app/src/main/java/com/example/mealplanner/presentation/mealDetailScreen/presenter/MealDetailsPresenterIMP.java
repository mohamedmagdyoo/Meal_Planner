package com.example.mealplanner.presentation.mealDetailScreen.presenter;

import android.content.Context;
import android.view.contentcapture.ContentCaptureCondition;

import com.example.mealplanner.data.meal.MealRepository;
import com.example.mealplanner.data.meal.model.Ingredient;
import com.example.mealplanner.data.meal.model.Meal;
import com.example.mealplanner.data.meal.model.MealDto;
import com.example.mealplanner.presentation.mealDetailScreen.view.MealDetailsScreen;
import com.example.mealplanner.presentation.mealDetailScreen.view.MealDetailsView;

import java.net.ContentHandler;
import java.util.ArrayList;
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


        ingredientsList = new ArrayList<>();

        String[] ingredientNames = {
                mealDto.getStrIngredient1(),
                mealDto.getStrIngredient2(),
                mealDto.getStrIngredient3(),
                mealDto.getStrIngredient4(),
                mealDto.getStrIngredient5(),
                mealDto.getStrIngredient6(),
                mealDto.getStrIngredient7(),
                mealDto.getStrIngredient8(),
                mealDto.getStrIngredient9(),
                mealDto.getStrIngredient10(),
                mealDto.getStrIngredient11(),
                mealDto.getStrIngredient12(),
                mealDto.getStrIngredient13(),
                mealDto.getStrIngredient14(),
                mealDto.getStrIngredient15(),
                mealDto.getStrIngredient16(),
                mealDto.getStrIngredient17(),
                mealDto.getStrIngredient18(),
                mealDto.getStrIngredient19(),
                mealDto.getStrIngredient20()
        };

        String[] ingredientMeasures = {
                mealDto.getStrMeasure1(),
                mealDto.getStrMeasure2(),
                mealDto.getStrMeasure3(),
                mealDto.getStrMeasure4(),
                mealDto.getStrMeasure5(),
                mealDto.getStrMeasure6(),
                mealDto.getStrMeasure7(),
                mealDto.getStrMeasure8(),
                mealDto.getStrMeasure9(),
                mealDto.getStrMeasure10(),
                mealDto.getStrMeasure11(),
                mealDto.getStrMeasure12(),
                mealDto.getStrMeasure13(),
                mealDto.getStrMeasure14(),
                mealDto.getStrMeasure15(),
                mealDto.getStrMeasure16(),
                mealDto.getStrMeasure17(),
                mealDto.getStrMeasure18(),
                mealDto.getStrMeasure19(),
                mealDto.getStrMeasure20()
        };

        for (int i = 0; i < 20; i++) {
            if (ingredientNames[i] != null && !ingredientNames[i].isEmpty()) {
                ingredientsList.add(new Ingredient(
                        ingredientNames[i],
                        ingredientMeasures[i],
                        "https://www.themealdb.com/images/ingredients/" + ingredientNames[i] + ".png"
                ));
            }
        }


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
