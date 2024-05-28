package com.xavier_carpentier.go4lunch.domain.model;

import androidx.annotation.NonNull;

import com.google.firebase.Timestamp;

import java.util.Objects;

public class RestaurantChoiceDomain {

    @NonNull
    private final Timestamp timestamp;
    @NonNull
    private final String idUser;
    @NonNull
    private final String userName;
    @NonNull
    private final String idRestaurant;
    @NonNull
    private final String restaurantName;


    public RestaurantChoiceDomain(@NonNull Timestamp timestamp,@NonNull String idUser,@NonNull String userName,@NonNull String idRestaurant,@NonNull String restaurantName) {
        this.timestamp=timestamp;
        this.idUser = idUser;
        this.userName = userName;
        this.idRestaurant = idRestaurant;
        this.restaurantName = restaurantName;
    }

    @NonNull
    public Timestamp getTimestamp() {
        return timestamp;
    }
    @NonNull
    public String getIdUser() {
        return idUser;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    @NonNull
    public String getIdRestaurant() {
        return idRestaurant;
    }

    @NonNull
    public String getRestaurantName() {
        return restaurantName;
    }



    @Override
    public String toString() {
        return "RestaurantChoiceDomain{" +
                "timestamp=" + timestamp +
                ", idUser='" + idUser + '\'' +
                ", userName='" + userName + '\'' +
                ", idRestaurant='" + idRestaurant + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantChoiceDomain that = (RestaurantChoiceDomain) o;
        return Objects.equals(timestamp, that.timestamp) && Objects.equals(idUser, that.idUser) && Objects.equals(userName, that.userName) && Objects.equals(idRestaurant, that.idRestaurant) && Objects.equals(restaurantName, that.restaurantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, idUser, userName, idRestaurant, restaurantName);
    }
}
