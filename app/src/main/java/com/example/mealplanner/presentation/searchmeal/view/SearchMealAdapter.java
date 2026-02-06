package com.example.mealplanner.presentation.searchmeal.view;

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
import com.example.mealplanner.presentation.search.view.OnItemClick;
import com.example.mealplanner.presentation.search.view.SearchAdapter;
import com.example.mealplanner.presentation.search.view.SearchFragment;
import com.example.mealplanner.presentation.search.view.SearchItem;

import java.util.List;

public class SearchMealAdapter extends RecyclerView.Adapter<SearchMealAdapter.CardHolder> {
    private List<SearchItem> data;
    private OnItemClick event;

    public SearchMealAdapter(SearchMeal fragment) {
        event = fragment;

    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card, parent,false);
        return new SearchMealAdapter.CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {
        SearchItem item = data.get(position);
        holder.bind(item);
    }

    public void setData(List<SearchItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data == null? 0 : data.size();
    }

    class CardHolder extends RecyclerView.ViewHolder {
        TextView cardName;
        ImageView cardImage;

        public CardHolder(@NonNull View itemView) {
            super(itemView);

            cardImage = itemView.findViewById(R.id.search_card_image);
            cardName = itemView.findViewById(R.id.search_card_name);
        }

        void bind(SearchItem item){
            Glide.with(itemView)
                    .load(item.getItemImage())
                    .centerCrop()
                    .placeholder(R.drawable.meal_icon)
                    .into(cardImage);

            cardName.setText(item.getItemName());

            itemView.setOnClickListener((view)->{
                event.onItemClick(item.getItemName() ,null);
            });

        }
    }
}
