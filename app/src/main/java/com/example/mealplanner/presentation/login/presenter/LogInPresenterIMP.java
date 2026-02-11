package com.example.mealplanner.presentation.login.presenter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.mealplanner.data.auth.dataSource.AuthRemotDataSource;
import com.example.mealplanner.data.calendarMeals.CalendarMealRepo;
import com.example.mealplanner.data.calendarMeals.dataSource.remote.CalendarMealRemoteDataSource;
import com.example.mealplanner.data.favMeals.MealRepository;
import com.example.mealplanner.presentation.login.view.LogInScreen;
import com.example.mealplanner.presentation.login.view.LogInView;
import com.google.firebase.auth.FirebaseUser;
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
                    Completable.fromAction(() -> {
                                setUserInfo(user);
                                mealRepository.clearAllTables();
                            })
                            .subscribeOn(Schedulers.io())
                            .andThen(mealRepository.fetchFavMeals())
                            .flatMapCompletable(favMealsList -> mealRepository.insertAll(favMealsList))
                            .andThen(calendarMealRepo.fetchAllCalendarMeals())
                            .flatMapCompletable(meals -> calendarMealRepo.insetAll(meals))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    () -> view.onSuccess(user)
                                    , this::handleError
                            );
                },
                this::handleError
        );

    }

    @Override
    public void logInUserWithGoogle(String idToken) {

        authRemotDataSource.loginWithGoogle(idToken)
                .subscribe(user -> {

                    // Clear DB first
                    Completable.fromAction(() -> {
                                setUserInfo(user);
                                mealRepository.clearAllTables();
                            })
                            .subscribeOn(Schedulers.io())
                            .andThen(mealRepository.fetchFavMeals())  // Single<List<Meal>>
                            .flatMapCompletable(favList ->
                                    mealRepository.insertAll(favList)
                            )
                            .andThen(calendarMealRepo.fetchAllCalendarMeals())
                            .flatMapCompletable(calList ->
                                    calendarMealRepo.insetAll(calList)
                            )
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    () -> view.onSuccess(user),
                                    this::handleError
                            );

                }, this::handleError);
    }


    public void setUserInfo(@NonNull FirebaseUser user) {
        Log.d("asd -->", "setUserInfo: username= " + user.getDisplayName());

        SharedPreferences sharedPreferences =
                activity.getSharedPreferences("app_info", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userId", user.getUid());
        editor.apply();
    }

    void handleError(Throwable error) {
        if (error instanceof InstantiationError) {
            Log.e("FavoriteMeals", "InstantiationError: " + error.getMessage(), error);
        } else if (error instanceof FirebaseFirestoreException) {
            Log.e("FavoriteMeals", "Firestore error: " + error.getMessage(), error);
        } else if (error instanceof IOException) {
            Log.e("FavoriteMeals", "Network error: " + error.getMessage(), error);
        } else {
            Log.e("FavoriteMeals", "Unknown error: " + error.getMessage(), error);
        }
    }

}
