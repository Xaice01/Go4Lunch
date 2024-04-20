package com.xavier_carpentier.go4lunch.presentation.model;

import android.net.Uri;

import androidx.annotation.Nullable;

public class UserWithRestaurant {
    private String uid;
    private String username;
    private String email;
    private Uri urlPicture;
    private String uidRestaurant;
    private String restaurantName;
    private String typeRestaurant;
    public UserWithRestaurant(String uid,String username, String email,Uri urlPicture,String uidRestaurant,String restaurantName, String typeRestaurant){
        this.uid=uid;
        this.username=username;
        this.email=email;
        this.urlPicture=urlPicture;
        this.uidRestaurant=uidRestaurant;
        this.restaurantName=restaurantName;
        this.typeRestaurant=typeRestaurant;
    }

    // --- GETTERS ---
    public String getUid() { return uid; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    @Nullable
    public Uri getUrlPicture() { return urlPicture; }
    @Nullable
    public String getUidRestaurant(){return uidRestaurant;}
    @Nullable
    public String getRestaurantName(){return restaurantName;}
    @Nullable
    public String getTypeRestaurant(){return typeRestaurant;}


    // --- SETTERS ---
    public void setUsername(String username) { this.username = username; }
    public void setUid(String uid) { this.uid = uid; }
    public void setEmail(String email) { this.email = email; }
    public void setUrlPicture(@Nullable Uri urlPicture) { this.urlPicture = urlPicture; }
    public void setUidRestaurant(String uidRestaurant) {this.uidRestaurant = uidRestaurant;}
    public void setRestaurantName(String restaurantName) {this.restaurantName = restaurantName;}
    public void setTypeRestaurant(String typeRestaurant) {this.typeRestaurant = typeRestaurant;}
}
