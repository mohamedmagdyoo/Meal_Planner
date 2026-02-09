package com.example.mealplanner.presentation.favscreen.view;

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
import com.example.mealplanner.data.favMeals.model.meal.Meal;

import java.util.List;

public class FavMealAdapter extends RecyclerView.Adapter<FavMealAdapter.CardHolder> {

    private OnFavMealsAdapterCallBack callBack;
    private List<Meal> data;

    public FavMealAdapter(FavoriteFragment favoriteFragment) {
        callBack = favoriteFragment;
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_meal_card, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {
        holder.bind(data.get(position));
        Log.d("asd -->", "onBindViewHolder: " + data.get(position).getMealName());
    }

    public void setData(List<Meal> data) {

        this.data = data;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {

        return data == null ? 0 : data.size();
    }

    public class CardHolder extends RecyclerView.ViewHolder {

        ImageView favImage;
        TextView favMealName;
        ImageView trashBtn;

        public CardHolder(@NonNull View itemView) {
            super(itemView);
            favImage = itemView.findViewById(R.id.search_card_image);
            favMealName = itemView.findViewById(R.id.search_card_name);
            trashBtn = itemView.findViewById(R.id.trash_fav_meal_btn);
        }

        public void bind(Meal mealDto) {
            Glide.with(itemView
                    .getContext()).load(mealDto.getMealImage()).centerCrop().placeholder(R.drawable.meal_icon).into(favImage);

            Log.d("asd -->", "bind: meal name" + mealDto.getMealName());
            favMealName.setText(mealDto.getMealName());

            trashBtn.setOnClickListener(view -> {
                callBack.onDeleteFromFavMeals(mealDto);
            });

            itemView.setOnClickListener(v -> {
                callBack.onItemViewClick(mealDto.getMealName());
            });
        }
    }
}
