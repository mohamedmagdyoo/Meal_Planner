package com.example.mealplanner.data.favMeals.model.meal;

import java.util.ArrayList;
import java.util.List;

public class GetIngredients {

    public static List<Ingredient> getAllIngredients(MealDto mealDto) {

        List<Ingredient> temp = new ArrayList<>();

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
                temp.add(new Ingredient(
                        ingredientNames[i],
                        ingredientMeasures[i],
                        "https://www.themealdb.com/images/ingredients/" + ingredientNames[i] + ".png"
                ));
            }
        }


        return temp;
    }
}
