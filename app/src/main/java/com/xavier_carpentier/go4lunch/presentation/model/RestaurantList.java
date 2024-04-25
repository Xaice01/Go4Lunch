package com.xavier_carpentier.go4lunch.presentation.model;

import android.net.Uri;

public class RestaurantList {
    private final String uid;
    private final String name;
    private final String typeRestaurant;
    private final String address;
    private final int distance;
    private final int note;
    private final int workmatesToEat;
    //horaire
    private final String schedule;
    private final Uri picture;

    public RestaurantList(String uid, String name, String typeRestaurant, String address, int distance, int note, int workmatesToEat, String schedule,Uri picture) {
        this.uid = uid;
        this.name = name;
        this.typeRestaurant = typeRestaurant;
        this.address = address;
        this.distance = distance;
        this.note = note;
        this.workmatesToEat = workmatesToEat;
        this.schedule = schedule;
        this.picture=picture;
    }

    // --- GETTERS ---
    public String getUid() {
        return uid;
    }
    public String getName() {
        return name;
    }
    public String getTypeRestaurant() {
        return typeRestaurant;
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
    public String getSchedule() {
        return schedule;
    }
    public Uri getPicture(){
        return picture;
    }

}
