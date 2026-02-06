package com.example.mealplanner.presentation.homescreen.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealplanner.R;
import com.example.mealplanner.data.meal.model.meal.MealDto;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {

    private List<MealDto> data;
    private OnClickOnMealItem event;

    public MealAdapter(HomeScreen view){
        event = view;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_card_view,parent,false);

        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        holder.bind(data.get(position));

        holder.itemView.setOnClickListener(v -> {
            event.clickedOnMealItem(data.get(position));
        });
    }

    public void setData(List<MealDto> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data == null? 0 : data.size();
    }


    class MealViewHolder extends RecyclerView.ViewHolder{
        ImageView mealImage;
        TextView mealName;
        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.card_meal_image);
            mealName = itemView.findViewById(R.id.card_meal_name);
        }

        public void bind(MealDto mealDto){
            mealName.setText(mealDto.getMealName());

            Glide.with(itemView.getContext())
                    .load(mealDto.getMealImage())
                    .centerCrop()
                    .placeholder(R.drawable.meal_icon)
                    .into(mealImage);
        }
    }


}
