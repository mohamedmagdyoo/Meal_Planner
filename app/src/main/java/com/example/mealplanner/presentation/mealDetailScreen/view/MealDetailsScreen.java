package com.example.mealplanner.presentation.mealDetailScreen.view;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mealplanner.R;
import com.example.mealplanner.data.calendarMeals.model.CalendarMeal;
import com.example.mealplanner.data.favMeals.model.meal.Ingredient;
import com.example.mealplanner.data.favMeals.model.meal.MealDto;
import com.example.mealplanner.databinding.FragmentMealDetailsScreenBinding;
import com.example.mealplanner.presentation.mealDetailScreen.presenter.MealDetailsPresenter;
import com.example.mealplanner.presentation.mealDetailScreen.presenter.MealDetailsPresenterIMP;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.Calendar;
import java.util.List;


public class MealDetailsScreen extends Fragment implements MealDetailsView {

    private MealDto mealDto;
    private FragmentMealDetailsScreenBinding binding;
    private MealsAdapter adapter;
    private MealDetailsPresenter presenterIMP;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMealDetailsScreenBinding.inflate(inflater, container, false);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mealDto = MealDetailsScreenArgs.fromBundle(getArguments()).getMealDetails();
        Log.d("asd -->", "onViewCreated: " + mealDto.getMealImage());

        Glide.with(this)
                .load(mealDto.getMealImage())
                .centerCrop()
                .placeholder(R.drawable.meal_icon)
                .into(binding.imageMealDetails);

        binding.nestedScrollViewDetails.post(() -> {
            binding.nestedScrollViewDetails.scrollTo(0, 0);
        });

        binding.mealNameMDetails.setText(mealDto.getMealName());
        binding.typeMealNameDetails.setText(mealDto.getCategory());
        binding.countryMealNameDetails.setText(mealDto.getArea());
        binding.instructionsTextViewDts.setText(mealDto.getInstructions());
        adapter = new MealsAdapter();
        binding.recyclerViewIngredientsDetails.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerViewIngredientsDetails.setAdapter(adapter);
        presenterIMP = new MealDetailsPresenterIMP(this, requireContext().getApplicationContext());
        presenterIMP.getMealIngredients(mealDto);

        getLifecycle().addObserver(binding.youtubePlayer);
        binding.youtubePlayer.addYouTubePlayerListener(
                new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String id = mealDto.getYoutubeURL().
                                substring(mealDto.getYoutubeURL().indexOf("=") + 1);
                        youTubePlayer.cueVideo(id, 0);
                    }
                }
        );

        //Fav Btn
        binding.addToFavMeals.setOnClickListener(v -> {
            presenterIMP.addToFavMeals(mealDto);
        });

        //Calendar Btn
        binding.addToCalenderBtn.setOnClickListener(v2 -> {
            showDatePicker();
        });
    }

    private void showDatePicker() {

        Calendar now = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(
                requireContext(),
                (view, year, month, dayOfMonth) -> {

                    Calendar cal = Calendar.getInstance();
                    cal.set(year, month, dayOfMonth, 0, 0, 0);
                    cal.set(Calendar.MILLISECOND, 0);

                    long selectedDay = cal.getTimeInMillis();

                    presenterIMP.addToCalendarMeals(mapToCalendarMeal(selectedDay));
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dialog.getDatePicker().setMinDate(System.currentTimeMillis());

        dialog.show();
    }

    private CalendarMeal mapToCalendarMeal(long date) {
        return new CalendarMeal(mealDto.getMealId(), mealDto.getMealName(), mealDto.getMealImage(), date);

    }

    @Override
    public void setMealIngredients(List<Ingredient> data) {
        if (data != null && !data.isEmpty()) {
            adapter.setData(data);
        }
    }

    @Override
    public void mealAddedToFav() {
        Toast.makeText(requireContext(), "Meal Added to Fav", Toast.LENGTH_SHORT).show();
        binding.addToFavMeals.setImageResource(R.drawable.love_red);
    }

    @Override
    public void mealAddedToCalendar() {
        Toast.makeText(requireContext(), "Meal Added to Calendar", Toast.LENGTH_SHORT).show();
        binding.addToCalenderBtn.setImageResource(R.drawable.calendar_creen);
    }

    @Override
    public void userIsGuest() {
        Toast.makeText(requireContext(), "You are in the guest mode", Toast.LENGTH_SHORT).show();

    }
}
