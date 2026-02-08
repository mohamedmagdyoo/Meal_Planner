package com.example.mealplanner.presentation.login.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.FragmentLogInScreenBinding;
import com.example.mealplanner.presentation.login.presenter.LogInPresenter;
import com.example.mealplanner.presentation.login.presenter.LogInPresenterIMP;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.FirebaseUser;


public class LogInScreen extends Fragment implements LogInView {
    private FragmentLogInScreenBinding binding;
    private NavDirections directions;
    private NavController controller;
    private LogInPresenter presenter;
    private String email;
    private String pass;

    private GoogleSignInClient googleClient;
    private ActivityResultLauncher<Intent> googleLauncher;


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
        presenter = new LogInPresenterIMP(this,requireActivity());

        handleLogIn();
        handleGoogleLogin();


        binding.backBtnLogInScreen.setOnClickListener(v -> {
            controller.popBackStack();
        });
    }

    private void handleGoogleLogin() {
        setupGoogle();

        binding.googleLoginBtn.setOnClickListener(view2 -> {
            googleLauncher.launch(googleClient.getSignInIntent());
        });

    }

    private void setupGoogle() {
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .build();

        googleClient = GoogleSignIn.getClient(requireActivity(), gso);

        googleLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == android.app.Activity.RESULT_OK) {
                                try {
                                    GoogleSignInAccount acc =
                                            GoogleSignIn.getSignedInAccountFromIntent(result.getData())
                                                    .getResult(ApiException.class);
                                    presenter.logInUserWithGoogle(acc.getIdToken());
                                } catch (ApiException e) {
                                    onFailed("Google login failed");
                                }
                            }
                        });
    }

    private void handleLogIn() {
        binding.signinBtn.setOnClickListener(view1 -> {
            getUserInfo();

            if (email != null && !email.isEmpty() &&
                    pass != null && !pass.isEmpty()) {

                presenter.logInUser(email, pass);

            } else {
                Toast.makeText(getContext(), "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
            }

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
    public void onFailed(String error) {
        Toast.makeText(requireContext(), "Failed Auth", Toast.LENGTH_SHORT).show();
    }
}