package com.example.mealplanner.presentation.profile.preseenter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;


import com.example.mealplanner.data.auth.dataSource.AuthRemotDataSource;
import com.example.mealplanner.data.favMeals.MealRepository;
import com.example.mealplanner.presentation.profile.view.ProfileFragment;
import com.example.mealplanner.presentation.profile.view.ProfileView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenterIMP implements ProfilePresenter {
    private AuthRemotDataSource authRemotDataSource;
    private ProfileView view;
    private ProfileFragment profileFragment;
    private MealRepository mealRepository;


    public ProfilePresenterIMP(ProfileFragment profileFragment, Context context) {
        authRemotDataSource = new AuthRemotDataSource(context);
        this.profileFragment = profileFragment;
        view = profileFragment;
        mealRepository = new MealRepository(context);
    }

    @Override
    public void logOut() {
        authRemotDataSource.signOut();

        Completable.fromAction(() -> {
                    mealRepository.clearAllTables();

                    SharedPreferences sp = profileFragment.requireActivity()
                            .getSharedPreferences("app_info", MODE_PRIVATE);
                    sp.edit().putBoolean("is_guest", false).apply();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.logOutDone()
                );
    }


}
