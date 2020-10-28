package com.mikeescom.doordashchallenge.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mikeescom.doordashchallenge.R;
import com.mikeescom.doordashchallenge.data.models.Restaurant;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListAdapterHolder> {
    private Restaurant[] results;
    private Context context;

    public ListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ListAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterHolder holder, int position) {
        Restaurant restaurant = results[position];

        Glide.with(context)
                .load(restaurant.getCover_img_url())
                .centerCrop()
                .placeholder(R.drawable.placeholder_item)
                .into(holder.foodImage);
        holder.deliveryFee.setText(String.valueOf(restaurant.getDelivery_fee()));
        holder.name.setText(restaurant.getName());
        holder.asapTime.setText(String.valueOf(restaurant.getAsap_time()));
        holder.rating.setText(String.valueOf(restaurant.getAverage_rating()));
        holder.ratingNumbers.setText(String.valueOf(restaurant.getNumber_of_ratings()));
    }

    @Override
    public int getItemCount() {
        if (results == null) {
            return 0;
        }
        return results.length;
    }

    public void setResults(Restaurant[] results) {
        this.results = results;
        notifyDataSetChanged();
    }

    class ListAdapterHolder extends RecyclerView.ViewHolder {
        private ImageView foodImage;
        private TextView deliveryFee;
        private TextView name;
        private TextView asapTime;
        private TextView rating;
        private TextView ratingNumbers;

        public ListAdapterHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            deliveryFee = itemView.findViewById(R.id.delivery_fee);
            name = itemView.findViewById(R.id.name);
            asapTime = itemView.findViewById(R.id.asap_time);
            rating = itemView.findViewById(R.id.rating);
            ratingNumbers = itemView.findViewById(R.id.ratings_number);
        }
    }
}