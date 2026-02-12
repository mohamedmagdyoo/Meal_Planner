package com.example.mealplanner.presentation.signup.view;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealplanner.databinding.FragmentSignupScreenBinding;
import com.example.mealplanner.presentation.signup.presenter.SignUpPresenter;
import com.example.mealplanner.presentation.signup.presenter.SignUpPresenterIMP;
import com.google.firebase.auth.FirebaseUser;


public class SignupScreen extends Fragment implements SignUpView {
    private FragmentSignupScreenBinding binding;
    private NavDirections directions;
    private NavController controller;
    private SignUpPresenterIMP presenter;
    private String username;
    private String email;
    private String pass;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignupScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = NavHostFragment.findNavController(this);
        presenter = new SignUpPresenterIMP(this,requireActivity());

        binding.signupBtn.setOnClickListener(view1 -> {
            getUserInfo();

            if (email != null && !email.isEmpty() &&
                    pass != null && !pass.isEmpty() && !username.isEmpty() && username != null) {

                presenter.greatNewAccount(username, email, pass);

            } else {
                Toast.makeText(getContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        binding.backBtnLogInScreen.setOnClickListener(v -> {
            controller.popBackStack();
        });
    }

    private void getUserInfo() {
        username = binding.username.getText().toString();
        email = binding.emailInputSignupScreen.getText().toString();
        pass = binding.passInputSignupScreen.getText().toString();
    }

    @Override
    public void onCreateNewAccountSuccess(FirebaseUser user) {
        setUserInfo(user);
        directions = SignupScreenDirections.actionSignupScreenToWelcomeScreen();
        controller.navigate(directions);
    }

    public void setUserInfo(FirebaseUser userInfo) {
        Log.d("asd -->", "setUserInfo: username= " + userInfo.getDisplayName());

        SharedPreferences sharedPreferences =
                requireActivity().getSharedPreferences("app_info",MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userId",userInfo.getUid());
        editor.apply();
    }

    @Override
    public void onCreateNewAccountFailed() {
        Toast.makeText(requireContext(), "Failed Auth", Toast.LENGTH_SHORT).show();
        ;
    }
}