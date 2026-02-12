package com.example.mealplanner.data.calendarMeals.dataSource.remote;

import android.content.Context;
import android.util.Log;

import com.example.mealplanner.data.calendarMeals.model.CalendarMeal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class CalendarMealsService {
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;

    public CalendarMealsService(Context context) {
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public void addCalendarMeal(CalendarMeal calendarMeal) {
        firestore.collection("users")
                .document(auth.getUid())
                .collection("calendarMeals")
                .document(calendarMeal.getMealId())
                .set(calendarMeal)
                .addOnSuccessListener(s -> {
                    Log.d("asd -->", "addCalendarMeal: success");

                })
                .addOnFailureListener(e -> {
                    Log.d("asd -->", "addCalendarMeal: failure");
                });

    }

    public void deleteCalendarMeal(CalendarMeal calendarMeal) {
        firestore.collection("users")
                .document(auth.getUid())
                .collection("calendarMeals")
                .document(calendarMeal.getMealId())
                .delete()
                .addOnSuccessListener(s -> {
                    Log.d("asd -->", "deleteCalendarMeal: success");

                })
                .addOnFailureListener(e -> {
                    Log.d("asd -->", "deleteCalendarMeal: failure");
                });
    }

    Single<List<CalendarMeal>> fetchCalendarMeals() {
        Single<List<CalendarMeal>> observable = Single.create(emitter -> {

            List<CalendarMeal> data = new ArrayList<>();

            firestore.collection("users")
                    .document(auth.getUid())
                    .collection("calendarMeals")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {

                        for (DocumentSnapshot val : queryDocumentSnapshots) {
                            data.add(val.toObject(CalendarMeal.class));
                        }
                        emitter.onSuccess(data);
                    });
        });

        return observable;
    }
}
