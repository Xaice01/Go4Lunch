package com.xavier_carpentier.go4lunch.presentation.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class AutocompletePrediction {

    private final String restaurantId;
    private final String restaurantName;

    public AutocompletePrediction(String restaurantId, String restaurantName) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;

    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.xavier_carpentier.go4lunch.presentation.model.AutocompletePrediction that = (com.xavier_carpentier.go4lunch.presentation.model.AutocompletePrediction) o;
        return Objects.equals(restaurantId, that.restaurantId) && Objects.equals(restaurantName, that.restaurantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, restaurantName);
    }

    @NonNull
    @Override
    public String toString() {
        return "AutocompletePrediction{" +
                "restaurantId='" + restaurantId + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                '}';
    }
}
