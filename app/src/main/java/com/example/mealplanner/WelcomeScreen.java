package com.example.mealplanner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class WelcomeScreen extends Fragment {
    //    private Handler handler = new ;
    private NavDirections direction;
    private NavController controller;

    private Button navHomeScreenBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        direction = WelcomeScreenDirections.actionWelcomeScreenToHomeScreen(22);
        navHomeScreenBtn = view.findViewById(R.id.nav_home_screen_btn);
        controller = NavHostFragment.findNavController(this);


        navHomeScreenBtn.setOnClickListener((v -> {
            direction = WelcomeScreenDirections.actionWelcomeScreenToHomeScreen(22);

            controller.navigate(direction);
        }));
    }
}