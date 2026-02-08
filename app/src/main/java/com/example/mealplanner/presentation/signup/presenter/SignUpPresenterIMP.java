package com.example.mealplanner.presentation.signup.presenter;

import android.content.Context;

import com.example.mealplanner.data.auth.dataSource.AuthRemotDataSource;
import com.example.mealplanner.data.auth.FirebaserResponse;
import com.example.mealplanner.presentation.signup.view.SignUpView;
import com.example.mealplanner.presentation.signup.view.SignupScreen;
import com.google.firebase.auth.FirebaseUser;

public class SignUpPresenterIMP implements SignUpPresenter{

    private AuthRemotDataSource authRemotDataSource;
    private SignUpView view;

    public SignUpPresenterIMP(SignupScreen signupScreen, Context context){
        authRemotDataSource = new AuthRemotDataSource(context);
        view = signupScreen;
    }

    @Override
    public void greatNewAccount(String username, String email, String password) {
        authRemotDataSource.register(email, password).subscribe(
                user -> view.onCreateNewAccountSuccess(user),
                throwable -> view.onCreateNewAccountFailed()
        );
    }
}
