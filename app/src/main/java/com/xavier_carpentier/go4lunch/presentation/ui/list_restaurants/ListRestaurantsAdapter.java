package com.xavier_carpentier.go4lunch.presentation.ui.list_restaurants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.xavier_carpentier.go4lunch.R;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListRestaurantsAdapter extends RecyclerView.Adapter<ListRestaurantsViewHolder> {

    private List<RestaurantList> restaurants = new ArrayList<>();
    private ListRestaurantsAdapter.OnItemClickListener mListener;

    public void setOnItemClickListener(ListRestaurantsAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(int position, String restaurantUid);
    }
    @NonNull
    @Override
    public ListRestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_restaurant_item, parent, false);
        return new ListRestaurantsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListRestaurantsViewHolder holder, int position) {
        holder.bind(restaurants.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }


    public void updateList(List<RestaurantList> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ListRestaurantsAdapter.UserDiffCallback(newList, this.restaurants));
        this.restaurants = new ArrayList<>(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    private static class UserDiffCallback extends DiffUtil.Callback {

        private final List<RestaurantList> oldRestaurants;
        private final List<RestaurantList> newRestaurants;

        public UserDiffCallback(List<RestaurantList> newRestaurants, List<RestaurantList> oldRestaurants) {
            this.newRestaurants = newRestaurants;
            this.oldRestaurants = oldRestaurants;
        }

        @Override
        public int getOldListSize() {
            return oldRestaurants.size();
        }

        @Override
        public int getNewListSize() {
            return newRestaurants.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return Objects.equals(oldRestaurants.get(oldItemPosition).getUid(), newRestaurants.get(newItemPosition).getUid());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldRestaurants.get(oldItemPosition).equals(newRestaurants.get(newItemPosition));
        }
    }
}
