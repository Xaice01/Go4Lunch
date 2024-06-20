package com.xavier_carpentier.go4lunch.presentation.ui.autocompleteSearchView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.xavier_carpentier.go4lunch.R;
import com.xavier_carpentier.go4lunch.presentation.model.AutocompletePrediction;

public class AutocompleteListAdapter extends ListAdapter<AutocompletePrediction, AutocompleteListViewHolder> {

    private AutocompleteListAdapter.OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public AutocompleteListAdapter() {
        super(new ListAutocompletePredictionItemCallback());

    }

    @NonNull
    @Override
    public AutocompleteListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.autocomplete_prediction_item, parent, false);
        return new AutocompleteListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AutocompleteListViewHolder holder, int position) {
        holder.bind(getItem(position), mListener);
    }


    public interface OnItemClickListener {
        void onItemClick(int position, String restaurantId);
    }

    private static class ListAutocompletePredictionItemCallback extends DiffUtil.ItemCallback<AutocompletePrediction> {

        @Override
        public boolean areItemsTheSame(@NonNull AutocompletePrediction oldItem, @NonNull AutocompletePrediction newItem) {
            return oldItem.getRestaurantId().equals(newItem.getRestaurantId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull AutocompletePrediction oldItem, @NonNull AutocompletePrediction newItem) {
            return oldItem.equals(newItem);
        }
    }
}
