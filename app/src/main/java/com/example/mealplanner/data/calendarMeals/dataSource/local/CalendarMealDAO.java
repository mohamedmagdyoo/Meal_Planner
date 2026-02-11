package com.example.mealplanner.data.calendarMeals.dataSource.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mealplanner.data.calendarMeals.model.CalendarMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface CalendarMealDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable addCalendarMeal(CalendarMeal calendarMeal);
    @Delete
    Completable deleteCalendarMeal(CalendarMeal calendarMeal);

    @Query("select * from  CalenderMeals where  day = :day")
    Observable<List<CalendarMeal>> getAllMealsInDay(long day);


}
