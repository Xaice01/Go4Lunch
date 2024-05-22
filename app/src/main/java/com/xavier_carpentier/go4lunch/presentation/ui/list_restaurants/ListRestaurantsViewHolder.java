package com.xavier_carpentier.go4lunch.presentation.ui.list_restaurants;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xavier_carpentier.go4lunch.R;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantItem;

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

    public void bind(RestaurantItem restaurant, ListRestaurantsAdapter.OnItemClickListener mListener) {
        //InputStream stream = getContentResolver().openInputStream(restaurant.getPicture());

        Glide.with(itemView.getRootView())
                .load(restaurant.getPicture())
                .centerCrop()
                .error(R.drawable.detail_restaurant_picture)
                .fallback(R.drawable.detail_restaurant_picture)
                .into(pictureRestaurant);

        name.setText(restaurant.getName());
        typeAndAddress.setText(restaurant.getAddress());

        //todo to change
        if(restaurant.getIsOpen()!=null) {
            schedule.setVisibility(View.VISIBLE);
            if (restaurant.getIsOpen()) {
                schedule.setText("Open");
                //schedule.setTextColor(green);
            } else {
                schedule.setText("close");
                //schedule.setTextColor(R.color.white); red
            }
        }else{
            schedule.setVisibility(View.INVISIBLE);
        }

        distance.setText(String.format("%sm",restaurant.getDistance()));

        //todo if a mettre dans le viewmodel
        if(restaurant.getWorkmatesToEat()==0){
            workmatesToEat.setVisibility(View.INVISIBLE);
        }else {
            workmatesToEat.setText(String.format("(%s)", restaurant.getWorkmatesToEat()));
        }

        //TODo for dans le viewmodel
        StringBuilder noteToWrite= new StringBuilder();
        for(int i=0;i<restaurant.getNote()&&i<4;i++){
            if(i!=1 && i!=3) {
                noteToWrite.append("⭐");
            }
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
