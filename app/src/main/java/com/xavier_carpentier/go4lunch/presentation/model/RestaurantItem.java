package com.xavier_carpentier.go4lunch.presentation.model;

import android.net.Uri;

import androidx.annotation.NonNull;

import javax.annotation.Nullable;

public class RestaurantItem {
    @NonNull
    private final String uid;
    private final String name;
    private final String address;
    private final int distance;
    private final int note;
    private final String latitude;
    private final String longitude;
    private int workmatesToEat;
    private final Boolean isOpen;
    private final Uri picture;

    public RestaurantItem(@NonNull String uid, String name, String address, int distance, int note, String latitude, String longitude, int workmatesToEat, Boolean isOpen, Uri picture) {
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.distance = distance;
        this.note = note;
        this.latitude = latitude;
        this.longitude = longitude;
        this.workmatesToEat = workmatesToEat;
        this.isOpen = isOpen;
        this.picture=picture;
    }

    // --- GETTERS ---
    public String getUid() {
        return uid;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public int getDistance() {
        return distance;
    }
    public int getNote() {
        return note;
    }
    public int getWorkmatesToEat() {
        return workmatesToEat;
    }
    public Boolean getIsOpen() {
        return isOpen;
    }
    public Uri getPicture(){
        return picture;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public void setWorkmatesToEat(int workmatesToEat){
        this.workmatesToEat = workmatesToEat;
    }
}
