package com.example.mealplanner.data.calendarMeals.dataSource.local;

import android.content.Context;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mealplanner.data.calendarMeals.model.CalendarMeal;
import com.example.mealplanner.data.db.AppDataBase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalendarMealLocalDataSource {
    private AppDataBase roomInstance;
    private CalendarMealDAO calendarMealDAO;

    public CalendarMealLocalDataSource(Context context){
        roomInstance = AppDataBase.getInstance(context);

        calendarMealDAO = roomInstance.getCalendarMealDAO();
    }

    public Completable addCalendarMeal(CalendarMeal calendarMeal){
        return calendarMealDAO.addCalendarMeal(calendarMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Completable deleteCalendarMeal(CalendarMeal calendarMeal){
        return calendarMealDAO.deleteCalendarMeal(calendarMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<List<CalendarMeal>> getAllMealsInDay(long day){
        return calendarMealDAO.getAllMealsInDay(day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
