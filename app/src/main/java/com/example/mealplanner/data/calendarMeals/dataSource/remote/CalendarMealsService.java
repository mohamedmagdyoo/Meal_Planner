package com.example.mealplanner.data.calendarMeals.dataSource.remote;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.mealplanner.data.calendarMeals.model.CalendarMeal;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class CalendarMealsService {
    private FirebaseFirestore firestore;
    private SharedPreferences sharedPreferences;
    private String userId;

    public CalendarMealsService(Context context) {
        firestore = FirebaseFirestore.getInstance();
        sharedPreferences = context.getSharedPreferences("app_info", MODE_PRIVATE);
        userId = sharedPreferences.getString("userId", "");
    }

    public void addCalendarMeal(CalendarMeal calendarMeal) {
        firestore.collection("users")
                .document(userId)
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
    public void deleteCalendarMeal(CalendarMeal calendarMeal){
        firestore.collection("users")
                .document(userId)
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
}
