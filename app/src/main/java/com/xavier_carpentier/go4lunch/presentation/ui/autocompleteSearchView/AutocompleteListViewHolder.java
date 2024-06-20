package com.xavier_carpentier.go4lunch.presentation.ui.autocompleteSearchView;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xavier_carpentier.go4lunch.R;
import com.xavier_carpentier.go4lunch.presentation.model.AutocompletePrediction;

public class AutocompleteListViewHolder extends RecyclerView.ViewHolder {

    private final TextView nameRestaurant ;

    public AutocompleteListViewHolder(@NonNull View itemView) {
        super(itemView);
        nameRestaurant= itemView.findViewById(R.id.prediction_restaurant_name_item);
    }

    public void bind(AutocompletePrediction autocompletePrediction, AutocompleteListAdapter.OnItemClickListener listener){
        nameRestaurant.setText(autocompletePrediction.getRestaurantName());

        itemView.setOnClickListener(v -> {
            if (listener != null) {
                Log.d(TAG,"AUTOCOMPLETE avant recycler " + getAdapterPosition() +" "+ autocompletePrediction.getRestaurantName());
                if (getAdapterPosition() != RecyclerView.NO_POSITION) { // to make sure the position is valid
                    Log.d(TAG,"AUTOCOMPLETE " + getAdapterPosition() +" "+ autocompletePrediction.getRestaurantName());
                    listener.onItemClick(getAdapterPosition(), autocompletePrediction.getRestaurantId());
                }
            }
        });
    }
}