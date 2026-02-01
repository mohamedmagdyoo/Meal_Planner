package com.example.mealplanner;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.mealplanner.databinding.ActivityMainBinding;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    SplashScreen splashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.fragmentContainerView);

        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(
                binding.bottomNavigationView,
                navController
        );

        handleBottomNavBar(navController);

        binding.lottieSplash.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                binding.lottieSplash.setVisibility(View.GONE);
            }
        });
    }

    private void handleBottomNavBar(NavController navController) {
        Set<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.homeScreen);
        topLevelDestinations.add(R.id.searchFragment);
        topLevelDestinations.add(R.id.calendarFragment);
        topLevelDestinations.add(R.id.favoriteFragment);
        topLevelDestinations.add(R.id.profileFragment);

        navController.addOnDestinationChangedListener((controller, destination, args) -> {
            binding.bottomNavigationView.setVisibility(
                    topLevelDestinations.contains(destination.getId())
                            ? View.VISIBLE
                            : View.GONE
            );
        });
    }
}


//    @NonNull
//    private NavController setupNavigation() {
//
//        NavController navController =
//                Navigation.findNavController(this, R.id.fragmentContainerView);
//
//        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
//            if (navController.getCurrentDestination() != null &&
//                    item.getItemId() == navController.getCurrentDestination().getId()) {
//                return false;
//            }
//            NavOptions options = new NavOptions.Builder()
//                    .setLaunchSingleTop(true)
//                    .setRestoreState(true)
//                    .setPopUpTo(R.id.homeScreen, false, true)
//                    .build();
//
//            try {
//                navController.navigate(item.getItemId(), null, options);
//                return true;
//            } catch (Exception e) {
//                return NavigationUI.onNavDestinationSelected(item, navController);
//            }
//        });
//
//        return navController;
//    }