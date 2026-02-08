package com.example.mealplanner.data.auth.dataSource;

import android.content.Context;

import com.example.mealplanner.data.auth.AuthService;
import com.example.mealplanner.data.auth.FirebaserResponse;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AuthRemotDataSource {

    private AuthService authService;

    public AuthRemotDataSource(Context context) {
        authService = new AuthService(context);
    }

    public Single<FirebaseUser> login(String email, String password) {
            return authService.login(email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

    }

    public Single<FirebaseUser> register(String email, String password) {
        return authService.register(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<FirebaseUser> loginWithGoogle(String idToken) {
        return authService.loginWithGoogle(idToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void signOut() {
        authService.signOut();
    }


}
