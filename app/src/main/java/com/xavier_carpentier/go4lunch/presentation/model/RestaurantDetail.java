package com.xavier_carpentier.go4lunch.presentation.model;

import android.net.Uri;

public class RestaurantDetail {
    private final String uid;
    private final Uri picture;
    private final String name;
    private final String typeRestaurant;
    private final String address;
    private final int note;
    private final String phone_number;
    private final boolean isLike;
    private final String webSite;


    public RestaurantDetail(String uid, Uri picture, String name, String typeRestaurant, String address, int note, String phone_number, boolean isLike, String webSite) {
        this.uid = uid;
        this.picture = picture;
        this.name = name;
        this.typeRestaurant = typeRestaurant;
        this.address = address;
        this.note = note;
        this.phone_number = phone_number;
        this.isLike = isLike;
        this.webSite = webSite;
    }

    public String getUid() {
        return uid;
    }

    public Uri getPicture() {
        return picture;
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

    public int getNote() {
        return note;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public boolean isLike() {
        return isLike;
    }

    public String getWebSite() {
        return webSite;
    }

}
