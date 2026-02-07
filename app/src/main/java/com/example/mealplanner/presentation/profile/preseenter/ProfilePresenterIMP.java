package com.example.mealplanner.presentation.profile.preseenter;

import com.example.mealplanner.presentation.profile.view.ProfileFragment;
import com.example.mealplanner.presentation.profile.view.ProfileView;
import com.google.firebase.auth.FirebaseAuth;

public class ProfilePresenterIMP implements ProfilePresenter {
    private FirebaseAuth auth;
    private ProfileView view;

    public ProfilePresenterIMP(ProfileFragment profileFragment) {
        auth = FirebaseAuth.getInstance();
        view = profileFragment;
    }

    @Override
    public void logOut() {
        auth.signOut();
        view.logOutDone();
    }
}
