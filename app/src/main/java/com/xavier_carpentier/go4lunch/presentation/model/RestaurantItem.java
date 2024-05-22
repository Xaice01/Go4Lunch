package com.xavier_carpentier.go4lunch.presentation.model;

import android.net.Uri;

import javax.annotation.Nullable;

public class RestaurantItem {
    private final String uid;
    private final String name;
    private final String address;
    private final int distance;
    private final int note;
    private final Integer workmatesToEat;
    private final Boolean isOpen;
    private final Uri picture;

    public RestaurantItem(String uid, String name, String address, int distance, int note, Integer workmatesToEat, Boolean isOpen, Uri picture) {
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.distance = distance;
        this.note = note;
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

}
