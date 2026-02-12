package com.example.mealplanner.presentation.lunch.presenter;

import android.content.Context;

import com.example.mealplanner.data.auth.dataSource.AuthRemotDataSource;

public class LunchPresenterIMP implements LunchPresenter{
    private AuthRemotDataSource authRemotDataSource;

    public LunchPresenterIMP(Context context){
        authRemotDataSource = new AuthRemotDataSource(context);
    }
    @Override
    public void logInAsGuest() {
        authRemotDataSource.logInAsGuest();
    }
}
