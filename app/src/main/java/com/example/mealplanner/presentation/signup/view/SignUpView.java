package com.example.mealplanner.presentation.signup.view;

import com.google.firebase.auth.FirebaseUser;

public interface SignUpView {
    void onCreateNewAccountSuccess(FirebaseUser user);
    void onCreateNewAccountFailed();
}
