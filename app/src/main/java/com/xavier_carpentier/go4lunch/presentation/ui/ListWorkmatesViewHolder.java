package com.xavier_carpentier.go4lunch.presentation.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xavier_carpentier.go4lunch.R;
import com.xavier_carpentier.go4lunch.presentation.model.UserWithRestaurant;

public class ListWorkmatesViewHolder extends RecyclerView.ViewHolder{

    // FOR DESIGN ---
    private final ImageView avatar;
    private final TextView usernameAndRestaurant;

    public ListWorkmatesViewHolder(@NonNull View itemView) {
        super(itemView);
        avatar = itemView.findViewById(R.id.item_list_user_avatar);
        usernameAndRestaurant = itemView.findViewById(R.id.item_list_workmates_username_and_restaurant);
    }

    public void bind(UserWithRestaurant user, ListWorkmatesAdapter.OnItemClickListener mListener) {
        Glide.with(itemView.getContext())
                .load(user.getUrlPicture())
                .apply(RequestOptions.circleCropTransform())
                .into(avatar);

        String textToWrite;
        if(user.getRestaurantName()!=null){
            textToWrite =user.getUsername()+R.string.eating+user.getTypeRestaurant()+" ("+user.getRestaurantName()+")";
        }else{
            textToWrite =user.getUsername()+R.string.notDecided;
        }
        usernameAndRestaurant.setText(textToWrite);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) { // to make sure the position is valid
                        mListener.onItemClick(getAdapterPosition(), user.getUidRestaurant());
                    }
                }
            }
        });
    }
}
