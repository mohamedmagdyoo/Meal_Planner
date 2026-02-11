package com.example.mealplanner.data.calendarMeals;

import android.content.Context;

import com.example.mealplanner.data.calendarMeals.dataSource.local.CalendarMealLocalDataSource;
import com.example.mealplanner.data.calendarMeals.dataSource.remote.CalendarMealRemoteDataSource;
import com.example.mealplanner.data.calendarMeals.model.CalendarMeal;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalendarMealRepo {
    private CalendarMealLocalDataSource localDataSource;
    private CalendarMealRemoteDataSource calendarMealRemoteDataSource;


    public CalendarMealRepo(Context context) {
        localDataSource = new CalendarMealLocalDataSource(context);
        calendarMealRemoteDataSource = new CalendarMealRemoteDataSource(context);

    }

    //local
    public Completable addCalendarMeal(CalendarMeal calendarMeal) {
        return localDataSource.addCalendarMeal(calendarMeal)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable deleteCalendarMeal(CalendarMeal calendarMeal) {
        return localDataSource.deleteCalendarMeal(calendarMeal);
    }

    public Observable<List<CalendarMeal>> getAllMealsInDay(long date) {
        return localDataSource.getAllMealsInDay(date);
    }

    //remot
    public void addCalendarMealToFireStore(CalendarMeal calendarMeal) {
        calendarMealRemoteDataSource.addCalendarMeal(calendarMeal);
    }


    public void deleteCalendarMealFromFireStore(CalendarMeal calendarMeal) {
        calendarMealRemoteDataSource.deleteCalendarMeal(calendarMeal);
    }
}
