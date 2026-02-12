package com.example.mealplanner.presentation.mealDetailScreen.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealplanner.R;
import com.example.mealplanner.data.favMeals.model.meal.Ingredient;

import java.util.List;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.CardHolder> {

    List<Ingredient> data;

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_card_view,parent,false);

        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {
        holder.bind(data.get(position));
    }

    public void setData(List<Ingredient> data) {
        this.data = data;
        notifyDataSetChanged();
        Log.d("asd -->", "setData:" + data.get(0).getName());
    }

    @Override
    public int getItemCount() {
        return data != null? data.size():0;
    }

    class CardHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView ingName;
        TextView ingMeasure;
        public CardHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ingredient_image);
            ingName = itemView.findViewById(R.id.ingredient_name);
            ingMeasure = itemView.findViewById(R.id.ingredient_measure);
        }

        public void bind(Ingredient ingredient){
            Log.d("asd -->", "url: " + ingredient.getUrl());
            Glide.with(itemView.getContext())
                    .load(ingredient.getUrl())
                    .centerCrop()
                    .placeholder(R.drawable.meal_icon)
                    .into(imageView);

            ingName.setText(ingredient.getName());
            ingMeasure.setText(ingredient.getMeasure());
        }
    }
}

/*
* https://www.themealdb.com/images/ingredients/lime-small.png
* https://www.themealdb.com/images/ingredients/Romano
* */