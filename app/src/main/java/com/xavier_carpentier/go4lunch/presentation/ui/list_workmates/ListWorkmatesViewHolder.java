package com.xavier_carpentier.go4lunch.presentation.ui.list_workmates;

import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xavier_carpentier.go4lunch.R;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;

public class ListWorkmatesViewHolder extends RecyclerView.ViewHolder{

    // FOR DESIGN ---
    private final ImageView avatar;
    private final TextView usernameAndRestaurant;

    public ListWorkmatesViewHolder(@NonNull View itemView) {
        super(itemView);
        avatar = itemView.findViewById(R.id.item_list_user_avatar);
        usernameAndRestaurant = itemView.findViewById(R.id.item_list_workmates_username_and_restaurant);
    }

    public void bind(Workmate user, ListWorkmatesAdapter.OnItemClickListener mListener) {
        Glide.with(itemView.getContext())
                .load(user.getUrlPicture())
                .apply(RequestOptions.circleCropTransform())
                .into(avatar);

        String textToWrite;

        if(user.getRestaurantName()!=null){
            textToWrite =itemView.getContext().getString(R.string.eating,user.getUsername(),user.getRestaurantName());
            usernameAndRestaurant.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.black));
            usernameAndRestaurant.setTypeface(null, Typeface.BOLD);
        }else{
            textToWrite =user.getUsername() +" "+ itemView.getContext().getString(R.string.notDecided);
            usernameAndRestaurant.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.light_gray));
            usernameAndRestaurant.setTypeface(null, Typeface.ITALIC);
        }
        usernameAndRestaurant.setText(textToWrite);

        itemView.setOnClickListener(view -> {
            if (mListener != null) {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) { // to make sure the position is valid
                    mListener.onItemClick(getAdapterPosition(), user.getUidRestaurant());
                }
            }
        });
    }
}
