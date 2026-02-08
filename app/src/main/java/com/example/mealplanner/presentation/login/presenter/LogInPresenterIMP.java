package com.example.mealplanner.presentation.login.presenter;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.mealplanner.data.auth.dataSource.AuthRemotDataSource;
import com.example.mealplanner.data.auth.FirebaserResponse;
import com.example.mealplanner.presentation.login.view.LogInScreen;
import com.example.mealplanner.presentation.login.view.LogInView;

import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Single;

public class LogInPresenterIMP implements LogInPresenter {

    private AuthRemotDataSource authRemotDataSource;
    private LogInView view;

    public LogInPresenterIMP(LogInScreen logInScreen, Context context) {
        authRemotDataSource = new AuthRemotDataSource(context);
        view = logInScreen;
    }

    @Override
    public void logInUser(String email, String pass) {
        authRemotDataSource.login(email, pass).subscribe(
                user -> view.onSuccess(user),
                throwable -> view.onFailed(throwable.getMessage())
        );
    }

    @Override
    public void logInUserWithGoogle(String idToken) {

        authRemotDataSource.loginWithGoogle(idToken).subscribe(
                user -> view.onSuccess(user),
                throwable -> view.onFailed(throwable.getMessage())
        );
    }


}
