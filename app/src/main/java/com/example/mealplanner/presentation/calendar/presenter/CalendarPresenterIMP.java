package com.example.mealplanner.presentation.calendar.presenter;

import android.content.Context;
import android.util.Log;

import com.example.mealplanner.data.calendarMeals.CalendarMealRepo;
import com.example.mealplanner.data.calendarMeals.model.CalendarMeal;
import com.example.mealplanner.data.favMeals.MealRepository;
import com.example.mealplanner.presentation.calendar.view.CalendarFragment;
import com.example.mealplanner.presentation.calendar.view.CalendarView;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.io.IOException;

public class CalendarPresenterIMP implements CalendarPresenter {
    private CalendarMealRepo calendarMealRepo;
    private MealRepository mealRepository;
    private CalendarView view;

    public CalendarPresenterIMP(Context context, CalendarFragment fragment) {
        calendarMealRepo = new CalendarMealRepo(context);
        mealRepository = new MealRepository(context);
        view = fragment;
    }

    @Override
    public void getAllMealsInDay(long day) {
        calendarMealRepo.getAllMealsInDay(day)
                .subscribe(
                        calendarMeals -> {
                            view.setData(calendarMeals);
                            Log.d("asd -->", "getAllMealsInDay: meals number = " + calendarMeals.size());
                        },
                        error -> handleError(error)
                );
    }

    @Override
    public void deleteMeal(CalendarMeal calendarMeal) {
        calendarMealRepo.deleteCalendarMeal(calendarMeal)
                .subscribe(
                        () -> view.mealDeleted(),
                        throwable -> handleError(throwable)
                );
        calendarMealRepo.deleteCalendarMealFromFireStore(calendarMeal);

    }

    @Override
    public void getMealByName(String mealName) {
        mealRepository.getMealByName(mealName)
                .map(list -> list != null ? list.get(0) : null)
                .subscribe(meal -> view.setMealInfo(meal),
                        throwable -> handleError(throwable));


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
