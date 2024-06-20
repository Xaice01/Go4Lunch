package com.xavier_carpentier.go4lunch.presentation.model;

public class RestaurantDetail {
    private final String uid;
    private final String picture;
    private final String name;
    private final String address;
    private final Double note;
    private final String phone_number;
    private boolean isLike;
    private final String webSite;


    public RestaurantDetail(String uid, String picture, String name, String address, Double note, String phone_number, boolean isLike, String webSite) {
        this.uid = uid;
        this.picture = picture;
        this.name = name;
        this.address = address;
        this.note = note;
        this.phone_number = phone_number;
        this.isLike = isLike;
        this.webSite = webSite;
    }

    public String getUid() {
        return uid;
    }

    public String getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }


    public String getAddress() {
        return address;
    }

    public Double getNote() {
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

    public void setLike(Boolean isLike) {
        this.isLike=isLike;
    }

}
