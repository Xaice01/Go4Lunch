package com.xavier_carpentier.go4lunch.presentation.model;


import androidx.annotation.Nullable;

public class User{
    private final String uid;
    private final String username;
    private final String urlPicture;
    public User(String uid,String username,@Nullable String urlPicture){
        this.uid=uid;
        this.username=username;
        this.urlPicture=urlPicture;
    }

    // --- GETTERS ---
    public String getUid() { return uid; }
    public String getUsername() { return username; }
    @Nullable
    public String getUrlPicture() { return urlPicture; }

}
