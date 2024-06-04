package com.xavier_carpentier.go4lunch.domain.notification;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class NotificationDomain {
    private final String nameWorkmate;
    //all workmate name without workmate currently connecting
    private final List<String> workmateToEatInThisRestaurant;
    private final String idRestaurant;
    private final String restaurantName;
    private final String restaurantVicinity;

    public NotificationDomain(String nameWorkmate, List<String> workmateToEatInThisRestaurant, String idRestaurant, String restaurantName, String restaurantVicinity) {
        this.nameWorkmate = nameWorkmate;
        this.workmateToEatInThisRestaurant = workmateToEatInThisRestaurant;
        this.idRestaurant = idRestaurant;
        this.restaurantName = restaurantName;
        this.restaurantVicinity = restaurantVicinity;
    }

    public String getNameWorkmate() {
        return nameWorkmate;
    }

    public List<String> getWorkmateToEatInThisRestaurant() {
        return workmateToEatInThisRestaurant;
    }

    public String getIdRestaurant() {
        return idRestaurant;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantVicinity() {
        return restaurantVicinity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationDomain that = (NotificationDomain) o;
        return Objects.equals(nameWorkmate, that.nameWorkmate) && Objects.equals(workmateToEatInThisRestaurant, that.workmateToEatInThisRestaurant) && Objects.equals(idRestaurant, that.idRestaurant) && Objects.equals(restaurantName, that.restaurantName) && Objects.equals(restaurantVicinity, that.restaurantVicinity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameWorkmate, workmateToEatInThisRestaurant, idRestaurant, restaurantName, restaurantVicinity);
    }

    @NonNull
    @Override
    public String toString() {
        return "NotificationDomain{" +
                "nameWorkmate='" + nameWorkmate + '\'' +
                ", workmateToEatInThisRestaurant=" + workmateToEatInThisRestaurant +
                ", idRestaurant='" + idRestaurant + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", restaurantVicinity='" + restaurantVicinity + '\'' +
                '}';
    }
}
