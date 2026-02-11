package com.example.mealplanner.presentation.profile.preseenter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;


import com.example.mealplanner.data.auth.dataSource.AuthRemotDataSource;
import com.example.mealplanner.presentation.profile.view.ProfileFragment;
import com.example.mealplanner.presentation.profile.view.ProfileView;

public class ProfilePresenterIMP implements ProfilePresenter {
    private AuthRemotDataSource authRemotDataSource;
    private ProfileView view;
    private ProfileFragment profileFragment;


    public ProfilePresenterIMP(ProfileFragment profileFragment, Context context) {
        authRemotDataSource = new AuthRemotDataSource(context);
        this.profileFragment = profileFragment;
        view = profileFragment;

    }

    @Override
    public void logOut() {
        authRemotDataSource.signOut();
        view.logOutDone();
        SharedPreferences sharedPreferences =
                profileFragment.requireActivity().getSharedPreferences("app_info", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("userId");
        editor.commit();
    }
}
