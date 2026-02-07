package com.example.mealplanner.presentation.login.presenter;

import com.example.mealplanner.presentation.login.view.LogInScreen;
import com.example.mealplanner.presentation.login.view.LogInView;
import com.google.firebase.auth.FirebaseAuth;

public class LogInPresenterIMP implements LogInPresenter {

    private FirebaseAuth auth;
    private LogInView view;

    public LogInPresenterIMP(LogInScreen logInScreen) {
        auth = FirebaseAuth.getInstance();
        view = logInScreen;
    }

    @Override
    public void logInUser(String email, String pass) {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                view.onSuccess(auth.getCurrentUser());
            }else{
                view.onFailed();
            }
        });
    }

}
