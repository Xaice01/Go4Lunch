package com.xavier_carpentier.go4lunch.domain.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class AutocompletePredictionDomain {

    private final String restaurantId;
    private final String restaurantName;

    public AutocompletePredictionDomain(String restaurantId, String restaurantName) {
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
        AutocompletePredictionDomain that = (AutocompletePredictionDomain) o;
        return Objects.equals(restaurantId, that.restaurantId) && Objects.equals(restaurantName, that.restaurantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, restaurantName);
    }

    @NonNull
    @Override
    public String toString() {
        return "AutocompletePredictionDomain{" +
                "restaurantId='" + restaurantId + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                '}';
    }
}
