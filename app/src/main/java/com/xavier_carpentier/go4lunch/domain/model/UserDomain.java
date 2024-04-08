package com.xavier_carpentier.go4lunch.domain.model;

import android.net.Uri;

import androidx.annotation.Nullable;

public class UserDomain {
    private String uid;
    private String username;
    private String email;
    private Uri urlPicture;
    public UserDomain(String uid,String username, String email,Uri urlPicture){
        this.uid=uid;
        this.username=username;
        this.email=email;
        this.urlPicture=urlPicture;
    }

    // --- GETTERS ---
    public String getUid() { return uid; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    @Nullable
    public Uri getUrlPicture() { return urlPicture; }


    // --- SETTERS ---
    public void setUsername(String username) { this.username = username; }
    public void setUid(String uid) { this.uid = uid; }
    public void setEmail(String email) { this.email = email; }
    public void setUrlPicture(@Nullable Uri urlPicture) { this.urlPicture = urlPicture; }

}
