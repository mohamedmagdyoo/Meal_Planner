package com.example.mealplanner.presentation.login.presenter;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.example.mealplanner.data.auth.dataSource.AuthRemotDataSource;
import com.example.mealplanner.data.calendarMeals.CalendarMealRepo;
import com.example.mealplanner.data.favMeals.MealRepository;
import com.example.mealplanner.presentation.login.view.LogInScreen;
import com.example.mealplanner.presentation.login.view.LogInView;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.io.IOException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LogInPresenterIMP implements LogInPresenter {

    private AuthRemotDataSource authRemotDataSource;
    private LogInView view;
    private FragmentActivity activity;
    private MealRepository mealRepository;
    private CalendarMealRepo calendarMealRepo;

    public LogInPresenterIMP(LogInScreen logInScreen, Context context, FragmentActivity activity) {
        authRemotDataSource = new AuthRemotDataSource(context);
        view = logInScreen;
        this.activity = activity;
        mealRepository = new MealRepository(context);
        calendarMealRepo = new CalendarMealRepo(context);
    }

    @Override
    public void logInUser(String email, String pass) {
        authRemotDataSource.login(email, pass).subscribe(
                user -> {
                    handleDB();
                    view.onSuccess(user);
                }, this::handleError);

    }

    @Override
    public void logInUserWithGoogle(String idToken) {

        authRemotDataSource.loginWithGoogle(idToken)
                .subscribe(user -> {
                    handleDB();
                    view.onSuccess(user);
                }, this::handleError);
    }

    public void handleDB() {
        Completable.fromAction(() -> mealRepository.fetchFavMeals())
                .andThen(mealRepository.fetchFavMeals())
                .doOnError(e -> Log.d("asd -->", "handleDB: error with fetch fav meals"))
                .flatMapCompletable(favList -> mealRepository.insertAll(favList))
                .doOnError(e -> Log.d("asd -->", "handleDB: error with insert fav meals"))
                .andThen(calendarMealRepo.fetchAllCalendarMeals())
                .doOnError(e -> Log.d("asd -->", "handleDB: error with fetch calander meals"))
                .flatMapCompletable(calList -> calendarMealRepo.insetAll(calList))
                .doOnError(e -> Log.d("asd -->", "handleDB: error with insert calander meals"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.d("asd -->", "handleDB: done"),
                        this::handleError);

    }


    void handleError(Throwable error) {
        view.onFailed(error.getMessage());
    }
}
/*
//    }
//        if (error instanceof InstantiationError) {
//            Log.e("FavoriteMeals", "InstantiationError: " + error.getMessage(), error);
//        }else if(error instanceof FirebaseNetworkException){
//            Log.e("FavoriteMeals", "Firestore error: " + error.getMessage(), error);
//        }
//        else if (error instanceof FirebaseFirestoreException) {
//            Log.e("FavoriteMeals", "Firestore error: " + error.getMessage(), error);
//        } else if (error instanceof IOException) {
//            Log.e("FavoriteMeals", "IOException: " + error.getMessage(), error);
//        } else if (error instanceof NetworkErrorException){
//            Log.e("FavoriteMeals", "NetworkError error: " + error.getMessage(), error);
//
//        }
//        else {
//            Log.e("FavoriteMeals", "Unknown error: " + error.getMessage(), error);
//        }
//    }
* */