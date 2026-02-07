package com.example.mealplanner.presentation.profile.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.FragmentProfileBinding;
import com.example.mealplanner.presentation.profile.preseenter.ProfilePresenterIMP;


public class ProfileFragment extends Fragment implements ProfileView {

    private FragmentProfileBinding binding;
    private NavController controller;
    private NavDirections directions;
    private ProfilePresenterIMP presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = NavHostFragment.findNavController(this);
        presenter = new ProfilePresenterIMP(this);

        binding.logOutBtn.setOnClickListener(v -> {
            presenter.logOut();
        });
    }

    @Override
    public void logOutDone() {
        // todo dirictions , controller

        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.bottom_nav_graph, true)
                .build();

        controller.navigate(R.id.lunchScreen,null,navOptions);
    }
}