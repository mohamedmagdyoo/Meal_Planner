package com.example.mealplanner.presentation.login.view;

import com.google.firebase.auth.FirebaseUser;

public interface LogInView {
    void onSuccess(FirebaseUser user);
    void onFailed();
}
