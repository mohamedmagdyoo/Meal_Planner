package com.example.mealplanner.data.favMeals.datasourc.remote;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.mealplanner.data.favMeals.model.meal.Meal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class FavMealsFirebaseStoreService {
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;

    public FavMealsFirebaseStoreService(Context context) {
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

    }

    public void addFavMeal(Meal meal) {
        Log.d("asd -->", "addFavMeal: userId" + auth.getUid());
        firestore.collection("users")
                .document(auth.getUid())
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
                .document(auth.getUid())
                .collection("favMeals")
                .document(mealId)
                .delete();
        Log.d("asd -->", "deleteFavMeal: ");
    }

    Single<List<Meal>> fetchFavMeals() {
        Single<List<Meal>> observable = Single.create(emitter -> {

            List<Meal> data = new ArrayList<>();

            firestore.collection("users")
                    .document(auth.getUid())
                    .collection("favMeals")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {

                        for (DocumentSnapshot val : queryDocumentSnapshots) {
                            data.add(val.toObject(Meal.class));
                        }
                        emitter.onSuccess(data);
                    });
        });

        return observable;
    }

}
