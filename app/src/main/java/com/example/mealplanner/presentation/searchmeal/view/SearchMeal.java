package com.example.mealplanner.presentation.searchmeal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.media3.exoplayer.source.ads.ServerSideAdInsertionMediaSource;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealplanner.R;
import com.example.mealplanner.data.meal.model.meal.MealDto;
import com.example.mealplanner.databinding.FragmentSearchMealBinding;
import com.example.mealplanner.presentation.search.view.OnItemClick;
import com.example.mealplanner.presentation.search.view.SearchFragmentDirections;
import com.example.mealplanner.presentation.search.view.SearchItem;
import com.example.mealplanner.presentation.searchmeal.presenter.SearchMealPresenter;
import com.example.mealplanner.presentation.searchmeal.presenter.SearchMealPresenterIMP;

import java.util.List;

public class SearchMeal extends Fragment implements SearchMealView, OnItemClick {
    private String key;
    private String flag;
    private SearchMealPresenterIMP presenter;
    private SearchMealAdapter adapter;
    private FragmentSearchMealBinding binding;
    private NavDirections directions;
    private NavController controller;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchMealBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new SearchMealPresenterIMP(this, requireContext());
        controller = NavHostFragment.findNavController(this);


        adapter = new SearchMealAdapter(this);
        binding.searchMealScreenContainer.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.searchMealScreenContainer.setAdapter(adapter);


        if (getArguments() != null) {
            key = SearchMealArgs.fromBundle(getArguments()).getKey();
            flag = SearchMealArgs.fromBundle(getArguments()).getFlag();
            Log.d("asd -->", "SearchMEal: Key and flag:   " + key + flag);
            presenter.getAllMeals(key, flag);
        }

    }

    @Override
    public void setData(List<SearchItem> data) {
        if (data.isEmpty()){
            directions = SearchMealDirections.actionSearchMealToNoDataScreen();
            controller.navigate(directions);
            return;
        }
        adapter.setData(data);
    }

    @Override
    public void setMealInfo(List<MealDto> mealDtoList) {
        if (mealDtoList != null) {
            MealDto meal = mealDtoList.get(0);

            if (meal != null) {
                directions = SearchMealDirections.actionSearchMealToMealDetailsScreen(meal);
                controller.navigate(directions);
            }
        }
    }

    @Override
    public void onError() {
        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show(); // koko: remove it
    }

    @Override
    public void onItemClick(String mealId, String flag) {
        //todo nav to meal with id
        presenter.getMealInfo(mealId);

    }
}