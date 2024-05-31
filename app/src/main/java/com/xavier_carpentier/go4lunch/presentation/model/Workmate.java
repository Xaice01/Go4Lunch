package com.xavier_carpentier.go4lunch.presentation.model;

import android.net.Uri;

import androidx.annotation.Nullable;

public class Workmate {
    private final String uid;
    private final String username;
    private final Uri urlPicture;
    private String uidRestaurant;
    private String restaurantName;
    public Workmate(String uid, String username, Uri urlPicture, String uidRestaurant, String restaurantName){
        this.uid=uid;
        this.username=username;
        this.urlPicture=urlPicture;
        this.uidRestaurant=uidRestaurant;
        this.restaurantName=restaurantName;
    }

    // --- GETTERS ---
    public String getUid() { return uid; }
    public String getUsername() { return username; }
    @Nullable
    public Uri getUrlPicture() { return urlPicture; }
    @Nullable
    public String getUidRestaurant(){return uidRestaurant;}
    @Nullable
    public String getRestaurantName(){return restaurantName;}
    public void setUidRestaurant(String uidRestaurant){
        this.uidRestaurant=uidRestaurant;
    }
    public void setRestaurantName(String restaurantName){
        this.restaurantName=restaurantName;
    }
}
