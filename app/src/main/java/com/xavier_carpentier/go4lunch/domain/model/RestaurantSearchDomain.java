package com.xavier_carpentier.go4lunch.domain.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

public class RestaurantSearchDomain {
    private final String placeId;

    private final String restaurantName;

    private final String vicinity;

    private final String photoReferenceUrl;

    private final Double rating;

    private final String latitude;

    private final String longitude;

    private final Integer distance;

    private final Boolean isOpen;


    public RestaurantSearchDomain(String placeId, String restaurantName, String vicinity, String photoReferenceUrl, Double rating, String latitude, String longitude, Integer distance, @Nullable Boolean isOpen) {
        this.placeId = placeId;
        this.restaurantName = restaurantName;
        this.vicinity = vicinity;
        this.photoReferenceUrl = photoReferenceUrl;
        this.rating = rating;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.isOpen = isOpen;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getVicinity() {
        return vicinity;
    }

    public String getPhotoReferenceUrl() {
        return photoReferenceUrl;
    }

    public Double getRating() {
        return rating;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public Integer getDistance() {
        return distance;
    }

    public Boolean getOpen() {
        return isOpen;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantSearchDomain that = (RestaurantSearchDomain) o;
        return Objects.equals(placeId, that.placeId) && Objects.equals(restaurantName, that.restaurantName) && Objects.equals(vicinity, that.vicinity) && Objects.equals(photoReferenceUrl, that.photoReferenceUrl) && Objects.equals(rating, that.rating) && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude) && Objects.equals(distance, that.distance) && Objects.equals(isOpen, that.isOpen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeId, restaurantName, vicinity, photoReferenceUrl, rating, latitude, longitude, distance, isOpen);
    }

    @NonNull
    @Override
    public String toString() {
        return "RestaurantSearchDomain{" +
                "placeId='" + placeId + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", vicinity='" + vicinity + '\'' +
                ", photoReferenceUrl='" + photoReferenceUrl + '\'' +
                ", rating=" + rating +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", distance=" + distance +
                ", isOpen=" + isOpen +
                '}';
    }
}
