package com.example.mealplanner.presentation.homescreen.view;


import static android.view.View.GONE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.mealplanner.R;
import com.example.mealplanner.data.favMeals.model.meal.MealDto;
import com.example.mealplanner.presentation.homescreen.prsenter.HomePresenterIMP;

import java.util.List;

public class HomeScreen extends Fragment implements MealView, OnClickOnMealItem {

    private HomePresenterIMP presenter;
    private RecyclerView recyclerView;
    private MealAdapter adapter;
    private MealDto randomMeal;
    private ImageView suggestedMealImage;
    private TextView suggestedMealName;
    private NavDirections directions;
    private NavController controller;
    private LottieAnimationView lottieAnimationView;
    private static boolean noInternetScreen = false;
    private CardView randoMealCardView;
    private ConstraintLayout constraintLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        randoMealCardView = view.findViewById(R.id.random_meal_card_view);
        lottieAnimationView = view.findViewById(R.id.no_connection_lottie_cont);
        controller = NavHostFragment.findNavController(this);
        constraintLayout = view.findViewById(R.id.random_meal_card_home_screen);

        recyclerView = view.findViewById(R.id.meals_recycler_view);
        suggestedMealImage = view.findViewById(R.id.suggested_meal_image);
        suggestedMealName = view.findViewById(R.id.suggested_meal_name);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new MealAdapter(this);
        recyclerView.setAdapter(adapter);

        presenter = new HomePresenterIMP(this, requireContext().getApplicationContext());
        presenter.getAllMeals();
        presenter.getRandomMeal();

        randoMealCardView.setOnClickListener(view1 ->{
            directions = HomeScreenDirections.actionHomeScreenToMealDetailsScreen(randomMeal);
            controller.navigate(directions);
        });
    }

    @Override
    public void setAllMeals(List<MealDto> data) {
        adapter.setData(data);
    }

    @Override
    public void setRandomMeal(MealDto randomMeal) {
        this.randomMeal = randomMeal;
        Glide.with(requireContext())
                .load(randomMeal.getMealImage())
                .centerCrop()
                .placeholder(R.drawable.meal_icon)
                .into(suggestedMealImage);
        suggestedMealName.setText(randomMeal.getMealName());

    }

    @Override
    public void noData() {
        if(!noInternetScreen){
            constraintLayout.setVisibility(GONE);
            lottieAnimationView.setVisibility(LottieAnimationView.VISIBLE);
        }
    }

    @Override
    public void clickedOnMealItem(MealDto meal) {
        directions = HomeScreenDirections.actionHomeScreenToMealDetailsScreen(meal);
        Log.d("asd -->", "clickedOnMealItem: " + meal.getMealName());
        controller.navigate(directions);

    }
}
