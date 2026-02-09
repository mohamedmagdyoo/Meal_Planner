package com.example.mealplanner.presentation.favscreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mealplanner.data.favMeals.model.meal.Meal;
import com.example.mealplanner.data.favMeals.model.meal.MealDto;
import com.example.mealplanner.databinding.FragmentFavoriteBinding;
import com.example.mealplanner.presentation.favscreen.presenter.FavMealPresenterIMP;

import java.util.List;


public class FavoriteFragment extends Fragment implements OnFavMealsAdapterCallBack, FavoMealView {

    private FragmentFavoriteBinding binding;
    private FavMealAdapter adapter;
    private FavMealPresenterIMP presenterIMP;
    private NavDirections directions;
    private NavController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new FavMealAdapter(this);
        presenterIMP = new FavMealPresenterIMP(this, getActivity().getApplicationContext());

        presenterIMP.getFavMeals();
        binding.favMealsContainer.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.favMealsContainer.setAdapter(adapter);

        //Controller
        controller = NavHostFragment.findNavController(this);


    }

    @Override
    public void onDeleteFromFavMeals(Meal meal) {
        presenterIMP.deleteFromFavMeals(meal);
    }

    @Override
    public void onItemViewClick(String mealName) {
        presenterIMP.getMealByName(mealName);
    }

    @Override
    public void setData(List<Meal> data) {
        adapter.setData(data);
    }

    @Override
    public void noDataInDB() {
        binding.noDataLottieFavScreen.setVisibility(LottieAnimationView.VISIBLE);
    }

    @Override
    public void noInternetError() {
        binding.noConnectionLottieCont.setVisibility(LottieAnimationView.VISIBLE);
    }

    @Override
    public void setMeal(MealDto mealDTO) {
        directions = FavoriteFragmentDirections.actionFavoriteFragmentToMealDetailsScreen(mealDTO);
        controller.navigate(directions);
    }

}