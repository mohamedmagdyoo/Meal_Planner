package com.example.mealplanner.presentation.login.presenter;

public interface LogInPresenter {
    void logInUser(String email,String pass);
    void logInUserWithGoogle(String idToken);
}
