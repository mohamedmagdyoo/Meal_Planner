package com.example.mealplanner.presentation.login.view;

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
import android.widget.Toast;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.FragmentLogInScreenBinding;
import com.example.mealplanner.presentation.login.presenter.LogInPresenterIMP;
import com.google.firebase.auth.FirebaseUser;

public class LogInScreen extends Fragment implements LogInView {
    private FragmentLogInScreenBinding binding;
    private NavDirections directions;
    private NavController controller;
    private LogInPresenterIMP presenter;
    private String email;
    private String pass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLogInScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = NavHostFragment.findNavController(this);
        presenter = new LogInPresenterIMP(this);

        binding.signinBtn.setOnClickListener(view1 -> {
            getUserInfo();

            if(email != null && !email.isEmpty() &&
                    pass != null && !pass.isEmpty()) {

                presenter.logInUser(email, pass);

            } else {
                Toast.makeText(getContext(), "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
            }

        });

        binding.googleLoginBtn.setOnClickListener(view2 ->{

        });

        binding.backBtnLogInScreen.setOnClickListener(v -> {
            controller.popBackStack();
        });
    }


    private void getUserInfo() {
        email = binding.emailInputLogInScreen.getText().toString();
        pass = binding.passInputLoginScreen.getText().toString();
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        directions = LogInScreenDirections.actionLogInScreenToWelcomeScreen();
        controller.navigate(directions);
    }

    @Override
    public void onFailed() {
        Toast.makeText(requireContext(), "Failed Auth", Toast.LENGTH_SHORT).show();
    }
}