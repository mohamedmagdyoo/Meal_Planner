package com.example.mealplanner.presentation.mealDetailScreen.view;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.mealplanner.R;
import com.example.mealplanner.data.meal.model.Ingredient;
import com.example.mealplanner.data.meal.model.MealDto;
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

        Glide.with(requireContext())
                .load(mealDto.getMealImage())
                .centerCrop()
                .placeholder(R.drawable.meal_icon)
                .into(binding.imageMealDetails);

        binding.mealNameMDetails.setText(mealDto.getMealName());
        binding.typeMealNameDetails.setText(mealDto.getCategory());
        binding.countryMealNameDetails.setText(mealDto.getArea());
        binding.instructionsTextViewDts.setText(mealDto.getInstructions());
        adapter = new MealsAdapter();
        binding.recyclerViewIngredientsDetails.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerViewIngredientsDetails.setAdapter(adapter);
        presenterIMP = new MealDetailsPresenterIMP(this);

        presenterIMP.getMealIngredients(mealDto);

        //        getLifecycle().addObserver(binding.videoView);
//        binding.videoView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                String urlToLoad = mealDto.getYoutubeURL();
//                String[] url = urlToLoad.split("watch?v=");
//                youTubePlayer.loadVideo(url[1], 0);
//            }
//        });
    }

    @Override
    public void setMealIngredients(List<Ingredient> data) {
        if (data != null && !data.isEmpty()) {
            adapter.setData(data);
        }
    }
}