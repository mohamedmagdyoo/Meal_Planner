package com.example.mealplanner;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    SplashScreen splashScreen;
    LottieAnimationView lottieView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        splashScreen  = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        lottieView= findViewById(R.id.lottie_splash);
        lottieView.setMaxProgress(0.4f);


        lottieView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                lottieView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationStart(@NonNull Animator animation) {

            }
        });
    }
}