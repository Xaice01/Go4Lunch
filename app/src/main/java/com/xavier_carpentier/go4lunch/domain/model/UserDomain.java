package com.xavier_carpentier.go4lunch.domain.model;

import android.net.Uri;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserDomain {
    private String uid;
    private String username;
    private String urlPicture;
    private List<String> uidRestaurantFavoris;

    // No-argument constructor required for Firestore
    public UserDomain() {
        this.uidRestaurantFavoris = new ArrayList<>();
    }

    public UserDomain(String uid,String username,@Nullable String urlPicture){
        this.uid=uid;
        this.username=username;
        this.urlPicture=urlPicture;
        this.uidRestaurantFavoris = new ArrayList<>();
    }

    // --- GETTERS ---
    public String getUid() { return uid; }
    public String getUsername() { return username; }
    @Nullable
    public String getUrlPicture() { return urlPicture; }
    public List<String> getUidRestaurantFavoris(){return uidRestaurantFavoris;}


    // --- SETTERS ---
    public void setUsername(String username) { this.username = username; }
    public void setUid(String uid) { this.uid = uid; }
    public void setUrlPicture(@Nullable String urlPicture) { this.urlPicture = urlPicture; }
    public void setUidRestaurantFavoris(List<String> uidRestaurantFavoris) {
        this.uidRestaurantFavoris = uidRestaurantFavoris;
    }
}
