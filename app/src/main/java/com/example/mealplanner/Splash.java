package com.example.mealplanner;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.databinding.FragmentSplashBinding;
import com.google.firebase.auth.FirebaseAuth;


public class Splash extends Fragment {

    private FragmentSplashBinding binding;
    private FirebaseAuth auth;
    private NavDirections direction;
    private NavController controller;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth = FirebaseAuth.getInstance();
        controller = NavHostFragment.findNavController(this);

        binding.lottieAnimationSplashScreen.setProgress(0.5f);
        binding.lottieAnimationSplashScreen.setSpeed(5f);
        binding.lottieAnimationSplashScreen.playAnimation();

        binding.lottieAnimationSplashScreen.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (auth.getCurrentUser() != null){
                    NavOptions options = new NavOptions.Builder()
                            .setPopUpTo(R.id.main_nav_graph,true,false)
                            .build();
                    controller.navigate(R.id.welcomeScreen,null,options);
                }else{
                    direction = SplashDirections.actionSplashToLunchScreen();
                    controller.navigate(direction);
                }
            }
        });
    }
}