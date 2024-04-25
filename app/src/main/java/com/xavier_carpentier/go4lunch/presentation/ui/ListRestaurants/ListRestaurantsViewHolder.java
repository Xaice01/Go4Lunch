package com.xavier_carpentier.go4lunch.presentation.ui.ListRestaurants;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xavier_carpentier.go4lunch.R;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantList;

public class ListRestaurantsViewHolder extends RecyclerView.ViewHolder {
    // FOR DESIGN ---
    private final ImageView pictureRestaurant;
    private final TextView name;
    private final TextView typeAndAddress;
    private final TextView schedule;
    private final TextView distance;
    private final TextView workmatesToEat;
    private final TextView rating;
    public ListRestaurantsViewHolder(@NonNull View itemView) {
        super(itemView);
        pictureRestaurant = itemView.findViewById(R.id.item_picture_restaurant);
        name = itemView.findViewById(R.id.item_name_restaurant);
        typeAndAddress = itemView.findViewById(R.id.item_type_restaurant_and_address_restaurant);
        schedule = itemView.findViewById(R.id.item_text_open_and_hour);
        distance = itemView.findViewById(R.id.item_distance_restaurant);
        workmatesToEat = itemView.findViewById(R.id.item_how_many_people_want_to_eat);
        rating = itemView.findViewById(R.id.item_text_rating);
    }

    public void bind(RestaurantList restaurant, ListRestaurantsAdapter.OnItemClickListener mListener) {
        pictureRestaurant.setImageURI(restaurant.getPicture());
        name.setText(restaurant.getName());
        typeAndAddress.setText(String.format("%s - %s", restaurant.getTypeRestaurant(), restaurant.getAddress()));
        schedule.setText(restaurant.getSchedule());
        distance.setText(String.format("%sm",restaurant.getDistance()));
        workmatesToEat.setText(String.format("(%s)",restaurant.getWorkmatesToEat()));

        //TODo if dans le viewmodel
        StringBuilder noteToWrite= new StringBuilder();
        for(int i=0;i<restaurant.getNote()&&i<3;i++){
            noteToWrite.append("⭐");

        }

        rating.setText(noteToWrite);



        itemView.setOnClickListener(view -> {
            if (mListener != null) {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) { // to make sure the position is valid
                    mListener.onItemClick(getAdapterPosition(), restaurant.getUid());
                }
            }
        });
    }
}
