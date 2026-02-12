package com.example.mealplanner.data.auth.dataSource;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mealplanner.data.auth.AuthService;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AuthRemotDataSource {

    private AuthService authService;
    private SharedPreferences sharedPreferences;


    public AuthRemotDataSource(Context context) {
        authService = new AuthService(context);
        sharedPreferences = context.getApplicationContext().getSharedPreferences("app_info",MODE_PRIVATE);

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

    public void logInAsGuest(){
        sharedPreferences.edit().putBoolean("is_guest",true).apply();
    }

    public boolean isGuest(){
        return sharedPreferences.getBoolean("is_guest",false);
    }



}
