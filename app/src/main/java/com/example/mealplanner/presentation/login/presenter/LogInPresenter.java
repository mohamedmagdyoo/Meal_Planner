package com.example.mealplanner.presentation.login.presenter;

import com.google.firebase.auth.FirebaseUser;

public interface LogInPresenter {
    void logInUser(String email,String pass);
    void logInUserWithGoogle(String idToken);
}
