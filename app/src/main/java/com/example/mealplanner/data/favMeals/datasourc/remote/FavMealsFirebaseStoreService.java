package com.example.mealplanner.data.favMeals.datasourc.remote;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.mealplanner.data.favMeals.model.meal.Meal;
import com.google.firebase.firestore.FirebaseFirestore;

public class FavMealsFirebaseStoreService {
    private FirebaseFirestore firestore;
    private String userId;

    public FavMealsFirebaseStoreService(Context context) {
        firestore = FirebaseFirestore.getInstance();

        SharedPreferences sharedPreferences = context.getSharedPreferences("app_info", MODE_PRIVATE);
        userId = sharedPreferences.getString("userId", null);
    }

    public void addFavMeal(Meal meal) {
        Log.d("asd -->", "addFavMeal: userId" + userId);
        firestore.collection("users")
                .document(userId)
                .collection("favMeals")
                .document(meal.getMealId())
                .set(meal)
                .addOnSuccessListener(s -> {
                    Log.d("asd -->", "addFavMeal: success");

                })
                .addOnFailureListener(e -> {
                    Log.d("asd -->", "addFavMeal: failure");
                });
    }

    public void deleteFavMeal(String mealId) {
        firestore.collection("users")
                .document(userId)
                .collection("favMeals")
                .document(mealId)
                .delete();
        Log.d("asd -->", "deleteFavMeal: ");
    }

}
