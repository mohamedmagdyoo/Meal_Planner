package com.example.mealplanner.presentation.login.presenter;

import android.content.Context;

import com.example.mealplanner.data.auth.dataSource.AuthRemotDataSource;
import com.example.mealplanner.presentation.login.view.LogInScreen;
import com.example.mealplanner.presentation.login.view.LogInView;

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
