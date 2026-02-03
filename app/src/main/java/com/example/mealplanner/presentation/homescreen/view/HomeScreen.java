package com.example.mealplanner.presentation.homescreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mealplanner.R;
import com.example.mealplanner.data.meal.MealRepository;
import com.example.mealplanner.data.meal.datasourc.remote.MealRemoteDataSource;
import com.example.mealplanner.data.meal.datasourc.remote.RetrofitCallBack;
import com.example.mealplanner.data.meal.model.MealDto;
import com.example.mealplanner.presentation.homescreen.prsenter.HomePresenter;
import com.example.mealplanner.presentation.homescreen.prsenter.HomePresenterIMP;

import java.util.List;

public class HomeScreen extends Fragment implements MealView {

    private RecyclerView recyclerView;
    private MealAdapter adapter;
    private MealDto randomMeal;
    private ImageView suggestedMealImage;
    private TextView suggestedMealName;
    private HomePresenterIMP presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.meals_recycler_view);
        suggestedMealImage = view.findViewById(R.id.suggested_meal_image);
        suggestedMealName = view.findViewById(R.id.suggested_meal_name);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new MealAdapter();
        recyclerView.setAdapter(adapter);

        presenter = new HomePresenterIMP(this);
        presenter.getAllMeals();
        presenter.getRandomMeal();
    }

    @Override
    public void setAllMeals(List<MealDto> data) {
        adapter.setData(data);
    }

    @Override
    public void setRandomMeal(MealDto randomMeal) {
        this.randomMeal = randomMeal;

        Glide.with(requireContext())
                .load(randomMeal.getMealImage())
                .centerCrop()
                .placeholder(R.drawable.meal_icon)
                .into(suggestedMealImage);

        suggestedMealName.setText(randomMeal.getMealName());

    }
}
