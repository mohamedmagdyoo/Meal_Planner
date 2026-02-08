package com.example.mealplanner.presentation.mealDetailScreen.view;

import android.os.Bundle;

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
import com.example.mealplanner.data.meal.model.meal.Ingredient;
import com.example.mealplanner.data.meal.model.meal.MealDto;
import com.example.mealplanner.databinding.FragmentMealDetailsScreenBinding;
import com.example.mealplanner.presentation.mealDetailScreen.presenter.MealDetailsPresenterIMP;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.List;


public class MealDetailsScreen extends Fragment implements MealDetailsView {

    private MealDto mealDto;
    private FragmentMealDetailsScreenBinding binding;
    private MealsAdapter adapter;
    private MealDetailsPresenterIMP presenterIMP;


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

        binding.addToFavMeals.setOnClickListener(v -> {
            presenterIMP.addToFavMeals(mealDto);
        });

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
    }
}