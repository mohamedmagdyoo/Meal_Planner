package com.example.mealplanner.presentation.calendar.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealplanner.data.calendarMeals.model.CalendarMeal;
import com.example.mealplanner.data.favMeals.model.meal.MealDto;
import com.example.mealplanner.databinding.FragmentCalendarBinding;
import com.example.mealplanner.presentation.calendar.presenter.CalendarPresenter;
import com.example.mealplanner.presentation.calendar.presenter.CalendarPresenterIMP;

import java.util.Calendar;
import java.util.List;

public class CalendarFragment extends Fragment implements CalendarView {

    private FragmentCalendarBinding binding;
    private CalendarPresenter presenter;
    private CalendarMealsAdapter calendarMealsAdapter;
    private NavDirections directions;
    private NavController controller;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new CalendarPresenterIMP(requireContext(), this);
        binding.recyclerViewCalendar.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        calendarMealsAdapter = new CalendarMealsAdapter(this);
        binding.recyclerViewCalendar.setAdapter(calendarMealsAdapter);
        controller = NavHostFragment.findNavController(this);
        binding.calendarView.setMinDate(System.currentTimeMillis());

        Calendar startOfDay = Calendar.getInstance();
        startOfDay.set(Calendar.HOUR_OF_DAY, 0);
        startOfDay.set(Calendar.MINUTE, 0);
        startOfDay.set(Calendar.SECOND, 0);
        startOfDay.set(Calendar.MILLISECOND, 0);

        long startTimeMillis = startOfDay.getTimeInMillis();

        presenter.getAllMealsInDay(startTimeMillis);


        //take the target day
        binding.calendarView.setOnDateChangeListener(new android.widget.CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull android.widget.CalendarView view, int year, int month, int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, dayOfMonth, 0, 0, 0);
                cal.set(Calendar.MILLISECOND, 0);

                long normalizedDay = cal.getTimeInMillis();
                presenter.getAllMealsInDay(normalizedDay);
            }
        });

    }

    @Override
    public void setData(List<CalendarMeal> data) {
        calendarMealsAdapter.setData(data);
    }

    @Override
    public void setMealInfo(MealDto mealDto) {
        directions = CalendarFragmentDirections.actionCalendarFragmentToMealDetailsScreen(mealDto);
        controller.navigate(directions);
    }

    @Override
    public void mealDeleted() {
        Toast.makeText(requireContext(), "Meal Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteMealFromCalendar(CalendarMeal calendarMeal) {
        presenter.deleteMeal(calendarMeal);
    }

    @Override
    public void onClickOnCalendarMeal(String mealName) {
        presenter.getMealByName(mealName);
    }
}

