package com.example.mealplanner.presentation.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mealplanner.databinding.FragmentSearchBinding;
import com.example.mealplanner.presentation.search.presenter.SearchPresenterIMP;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchFragment extends Fragment implements SearchView, OnItemClick {

    private FragmentSearchBinding binding;
    private SearchPresenterIMP presenterIMP;
    private SearchAdapter adapter;
    private Chip selectedChip;
    private NavDirections directions;
    private NavController controller;
    private String flag;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenterIMP = new SearchPresenterIMP(this, requireContext());
        binding.searchScreenTextInpuFirst.setHint("Select Type To Start Search");
        controller = NavHostFragment.findNavController(this);

        putListenerOnSelectedChip();
        handelSearching();

        binding.searchScreenContainer
                .setLayoutManager(new LinearLayoutManager(requireContext(),
                        LinearLayoutManager.VERTICAL, false));
        adapter = new SearchAdapter(this);
        binding.searchScreenContainer.setAdapter(adapter);
        presenterIMP.getAllCategories();
    }

    void handelSearching() {

        Observable<String> stringObservable = Observable.create(emitter -> {
            binding.searchScreenTextInpuFirst.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String query = s.toString();
                    emitter.onNext(query);
                }
            });
        });

        stringObservable
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        query -> {
                            presenterIMP.filterer(query);
                        }
                );
    }


    @Override
    public void setData(List<SearchItem> data, String flag) {
        adapter.setData(data, flag);
    }

    @Override
    public void onError(String message) {
        binding.noConnectionLottieCont.setVisibility(LottieAnimationView.VISIBLE);
    }


    private void putListenerOnSelectedChip() {
        binding.chipGroup.setOnCheckedStateChangeListener((chipGroup, list) -> {
            int checkedId = list.get(0);
            selectedChip = chipGroup.findViewById(checkedId);

            if ("Category".equals(selectedChip.getText().toString())) {
                binding.searchScreenTextInpuFirst.setText("");
                binding.searchScreenTextInpuFirst.setHint("Search By Category");
                presenterIMP.getAllCategories();
            } else if ("Country".equals(selectedChip.getText().toString())) {
                binding.searchScreenTextInpuFirst.setText("");
                binding.searchScreenTextInpuFirst.setHint("Search By Country");
                presenterIMP.getAllCountries();

            } else if ("Ingredient".equals(selectedChip.getText().toString())) {
                binding.searchScreenTextInpuFirst.setText("");
                binding.searchScreenTextInpuFirst.setHint("Search By Ingredient");
                presenterIMP.getAllIngredients();

            }
        });
    }


    @Override
    public void onItemClick(String nameToFiltrate, String flag) {
        directions = SearchFragmentDirections.actionSearchFragmentToSearchMeal(nameToFiltrate, flag);
        controller.navigate(directions);
    }
}