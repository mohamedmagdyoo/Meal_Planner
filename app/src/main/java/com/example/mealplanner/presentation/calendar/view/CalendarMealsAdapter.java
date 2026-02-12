package com.example.mealplanner.presentation.calendar.view;

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
import com.example.mealplanner.data.calendarMeals.model.CalendarMeal;

import java.util.List;

public class CalendarMealsAdapter extends RecyclerView.Adapter<CalendarMealsAdapter.CardHoleder> {

    private List<CalendarMeal> data;
    private CalendarView view;

    public CalendarMealsAdapter(CalendarFragment fragment){
        view = fragment;
    }

    @NonNull
    @Override
    public CardHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calender_meal_card_view, parent, false);
        return new CardHoleder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHoleder holder, int position) {
        holder.bind(data.get(position));
    }

    public void setData(List<CalendarMeal> data) {
        this.data = data;
        Log.d("asd -->", "setData: in calender ad = " + data.size());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data == null? 0 : data.size();
    }

    class CardHoleder extends RecyclerView.ViewHolder {
        ImageView mealImage;
        ImageView trashImage;
        TextView mealName;
        public CardHoleder(@NonNull View itemView) {
            super(itemView);

            mealImage = itemView.findViewById(R.id.calender_meal_card_image);
            mealName = itemView.findViewById(R.id.calender_meal_card_name);
            trashImage = itemView.findViewById(R.id.trash_calender_meal_btn);
        }

        public void bind(CalendarMeal calendarMeal){
            Glide.with(itemView.getContext())
                    .load(calendarMeal.getMealImage())
                    .centerCrop()
                    .placeholder(R.drawable.meal_icon)
                    .into(mealImage);

            mealName.setText(calendarMeal.getMealName());

            trashImage.setOnClickListener(v -> {
                view.onDeleteMealFromCalendar(calendarMeal);
            });

            itemView.setOnClickListener(v ->{
                view.onClickOnCalendarMeal(calendarMeal.getMealName());
            });

        }
    }
}
