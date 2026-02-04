package com.example.mealplanner.presentation.favscreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealplanner.data.meal.model.Meal;
import com.example.mealplanner.data.meal.model.MealDto;
import com.example.mealplanner.databinding.FragmentFavoriteBinding;
import com.example.mealplanner.presentation.favscreen.presenter.FavMealPresenterIMP;

import java.util.List;


public class FavoriteFragment extends Fragment implements OnDeleteFromFav, FavoMealView {

    private FragmentFavoriteBinding binding;
    private FavMealAdapter adapter;
    private FavMealPresenterIMP presenterIMP;

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
    }

    @Override
    public void deleteFromFavMeals(Meal meal) {
        presenterIMP.deleteFromFavMeals(meal);
    }

    @Override
    public void setData(List<Meal> data) {
        if (data == null || data.isEmpty()) {
            Toast.makeText(requireContext(), "DataBase Is Empty", Toast.LENGTH_SHORT).show();
        }
        adapter.setData(data);
    }

}