package com.xavier_carpentier.go4lunch.domain.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.Timestamp;

import java.util.Objects;

public class RestaurantChoiceDomain {

    @NonNull
    private final Timestamp timestamp;
    @NonNull
    private final String idUser;
    @NonNull
    private final String userName;
    @Nullable
    private final String urlUserPicture;
    @NonNull
    private final String idRestaurant;
    @NonNull
    private final String restaurantName;
    @NonNull
    private final String vicinity;

    //constructor empty for firebase
    public RestaurantChoiceDomain() {
        // Needed for Firestore
        this.timestamp = null;
        this.idUser = "";
        this.userName = "";
        this.urlUserPicture = null;
        this.idRestaurant = "";
        this.restaurantName = "";
        this.vicinity="";
    }

    public RestaurantChoiceDomain(@NonNull Timestamp timestamp, @NonNull String idUser, @NonNull String userName, @Nullable String urlUserPicture, @NonNull String idRestaurant, @NonNull String restaurantName,@NonNull String vicinity) {
        this.timestamp=timestamp;
        this.idUser = idUser;
        this.userName = userName;
        this.urlUserPicture =urlUserPicture;
        this.idRestaurant = idRestaurant;
        this.restaurantName = restaurantName;
        this.vicinity=vicinity;
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
    @Nullable
    public String getUrlUserPicture() {
        return urlUserPicture;
    }

    @NonNull
    public String getIdRestaurant() {
        return idRestaurant;
    }

    @NonNull
    public String getRestaurantName() {
        return restaurantName;
    }

    @NonNull
    public String getVicinity() {
        return vicinity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantChoiceDomain that = (RestaurantChoiceDomain) o;
        return Objects.equals(timestamp, that.timestamp) && Objects.equals(idUser, that.idUser) && Objects.equals(userName, that.userName) && Objects.equals(urlUserPicture, that.urlUserPicture) && Objects.equals(idRestaurant, that.idRestaurant) && Objects.equals(restaurantName, that.restaurantName) && Objects.equals(vicinity, that.vicinity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, idUser, userName, urlUserPicture, idRestaurant, restaurantName, vicinity);
    }

    @NonNull
    @Override
    public String toString() {
        return "RestaurantChoiceDomain{" +
                "timestamp=" + timestamp +
                ", idUser='" + idUser + '\'' +
                ", userName='" + userName + '\'' +
                ", urlUserPicture='" + urlUserPicture + '\'' +
                ", idRestaurant='" + idRestaurant + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", vicinity='" + vicinity + '\'' +
                '}';
    }
}
