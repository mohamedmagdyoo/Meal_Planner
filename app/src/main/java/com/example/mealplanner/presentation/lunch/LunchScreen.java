package com.example.mealplanner.presentation.lunch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.FragmentLunchScreenBinding;
import com.google.firebase.analytics.FirebaseAnalytics;

public class LunchScreen extends Fragment {
    private FragmentLunchScreenBinding binding;
    private NavDirections directions;
    private NavController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLunchScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = NavHostFragment.findNavController(this);


        //Login btn
        binding.loginLunchScreenBtn.setOnClickListener(view1 -> {

            // Firebase Analytics instance
            FirebaseAnalytics analytics = FirebaseAnalytics.getInstance(requireContext());

            analytics.setAnalyticsCollectionEnabled(true);
            analytics.setUserProperty("debug_mode", "true");

            Bundle bundle = new Bundle();
            bundle.putString("test_key", "test_value");
            analytics.logEvent("test_event", bundle);


            view1.postDelayed(() -> {
                directions = LunchScreenDirections.actionLunchScreenToLogInScreen();
                controller.navigate(directions);
            }, 500);
        });

        binding.signupLunchScreenBtn.setOnClickListener(view1 -> {
            directions = LunchScreenDirections.actionLunchScreenToSignupScreen();
            controller.navigate(directions);
        });
    }
}