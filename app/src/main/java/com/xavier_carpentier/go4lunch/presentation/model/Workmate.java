package com.xavier_carpentier.go4lunch.presentation.model;

import android.net.Uri;

import androidx.annotation.Nullable;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workmate workmate = (Workmate) o;
        return Objects.equals(uid, workmate.uid) && Objects.equals(username, workmate.username) && Objects.equals(urlPicture, workmate.urlPicture) && Objects.equals(uidRestaurant, workmate.uidRestaurant) && Objects.equals(restaurantName, workmate.restaurantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, username, urlPicture, uidRestaurant, restaurantName);
    }
}
