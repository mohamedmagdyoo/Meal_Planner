package com.example.mealplanner.presentation.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityMainBinding;


import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavHostFragment navHostFragment;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.fragmentContainerView);

        navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(
                binding.bottomNavigationView,
                navController
        );

        handleBottomNavBar();
        handelBackAction();
        handleActionOnNavBarBottom();
    }

    private void handelBackAction() {

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Set<Integer> outersScreens = new HashSet<>();
                outersScreens.add(R.id.homeScreen);
                outersScreens.add(R.id.lunchScreen);
                outersScreens.add(R.id.welcomeScreen);

                NavDestination directions = navController.getCurrentDestination();

                if (directions != null && outersScreens.contains(directions.getId())) {
                    finish();
                } else {
                    navController.popBackStack();
                }

            }
        });
    }

    private void handleBottomNavBar() {
        Set<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.homeScreen);
        topLevelDestinations.add(R.id.searchFragment);
        topLevelDestinations.add(R.id.calendarFragment);
        topLevelDestinations.add(R.id.favoriteFragment);
        topLevelDestinations.add(R.id.profileFragment);
        topLevelDestinations.add(R.id.noInternetScreen);
        topLevelDestinations.add(R.id.noDataScreen);
        topLevelDestinations.add(R.id.searchMeal);

        navController.addOnDestinationChangedListener((controller, destination, args) -> {
            binding.bottomNavigationView.setVisibility(
                    topLevelDestinations.contains(destination.getId())
                            ? View.VISIBLE
                            : View.GONE

            );
        });
    }

    private void handleActionOnNavBarBottom() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (navController.getCurrentDestination() != null && item.getItemId() == navController.getCurrentDestination().getId()) {
                return false;
            }

            NavOptions options = new NavOptions.Builder()
                    .setLaunchSingleTop(true)
                    .setRestoreState(true)
                    .setPopUpTo(R.id.homeScreen, false, true)
                    .build();

            navController.navigate(item.getItemId(), null, options);
            return true;
        });
    }
}