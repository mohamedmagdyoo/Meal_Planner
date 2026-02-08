package com.example.mealplanner.presentation.profile.preseenter;

import android.content.Context;


import com.example.mealplanner.data.auth.dataSource.AuthRemotDataSource;
import com.example.mealplanner.presentation.profile.view.ProfileFragment;
import com.example.mealplanner.presentation.profile.view.ProfileView;

public class ProfilePresenterIMP implements ProfilePresenter {
    private AuthRemotDataSource authRemotDataSource;
    private ProfileView view;


    public ProfilePresenterIMP(ProfileFragment profileFragment, Context context) {
        authRemotDataSource = new AuthRemotDataSource(context);
        view = profileFragment;
    }

    @Override
    public void logOut() {

        authRemotDataSource.signOut();
        view.logOutDone();
    }
}
