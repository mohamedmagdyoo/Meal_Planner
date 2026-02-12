package com.example.mealplanner.presentation.lunch.view;

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

import com.example.mealplanner.databinding.FragmentLunchScreenBinding;
import com.example.mealplanner.presentation.lunch.presenter.LunchPresenter;
import com.example.mealplanner.presentation.lunch.presenter.LunchPresenterIMP;

public class LunchScreen extends Fragment {
    private FragmentLunchScreenBinding binding;
    private NavDirections directions;
    private NavController controller;
    private LunchPresenter presenter;

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
        presenter = new LunchPresenterIMP(requireContext());
        controller = NavHostFragment.findNavController(this);


        //Login btn
        binding.loginLunchScreenBtn.setOnClickListener(view1 -> {
            directions = LunchScreenDirections.actionLunchScreenToLogInScreen();
            controller.navigate(directions);
        });

        binding.signupLunchScreenBtn.setOnClickListener(view1 -> {
            directions = LunchScreenDirections.actionLunchScreenToSignupScreen();
            controller.navigate(directions);
        });

        binding.guestImage.setOnClickListener(v -> {
            presenter.logInAsGuest();
            directions = LunchScreenDirections.actionLunchScreenToWelcomeScreen();
            controller.navigate(directions);
        });

        binding.guestTextView.setOnClickListener(v -> {
            presenter.logInAsGuest();
            directions = LunchScreenDirections.actionLunchScreenToWelcomeScreen();
            controller.navigate(directions);
        });

    }
}