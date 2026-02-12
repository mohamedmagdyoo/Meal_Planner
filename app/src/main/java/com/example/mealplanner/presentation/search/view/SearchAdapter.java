package com.example.mealplanner.presentation.search.view;

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

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.CardHolder> {
    private List<SearchItem> data;
    private String flag;
    private OnItemClick event;

    public SearchAdapter(SearchFragment fragment) {
        event = fragment;

    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card, parent,false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {
        SearchItem item = data.get(position);
        holder.bind(item);
    }


    public void setData(List<SearchItem> data, String character) {
        this.data = data;
        flag = character;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return data == null? 0 : data.size();
    }

    public class CardHolder extends RecyclerView.ViewHolder {
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
                event.onItemClick(item.getItemName(),flag);
            });
        }
    }
}
