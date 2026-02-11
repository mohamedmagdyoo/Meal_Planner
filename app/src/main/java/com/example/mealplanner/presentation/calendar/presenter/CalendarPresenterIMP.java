package com.example.mealplanner.presentation.calendar.presenter;

import android.content.Context;
import android.util.Log;

import com.example.mealplanner.data.calendarMeals.CalendarMealRepo;
import com.example.mealplanner.data.calendarMeals.model.CalendarMeal;
import com.example.mealplanner.data.favMeals.MealRepository;
import com.example.mealplanner.presentation.calendar.view.CalendarFragment;
import com.example.mealplanner.presentation.calendar.view.CalendarView;

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
        //todo handle error types
        Log.d("asd -->", "handleError C.M: " + error.getMessage());
    }
}
