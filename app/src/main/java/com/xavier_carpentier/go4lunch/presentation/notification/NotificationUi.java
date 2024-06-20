package com.xavier_carpentier.go4lunch.presentation.notification;

import java.util.List;
public class NotificationUi {
    private final String nameWorkmate;
    //all workmate name without workmate currently connecting
    private final List<String> workmateToEatInThisRestaurant;
    private final String idRestaurant;
    private final String restaurantName;
    private final String restaurantVicinity;

    public NotificationUi(String nameWorkmate, List<String> workmateToEatInThisRestaurant, String idRestaurant, String restaurantName, String restaurantVicinity) {
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

}