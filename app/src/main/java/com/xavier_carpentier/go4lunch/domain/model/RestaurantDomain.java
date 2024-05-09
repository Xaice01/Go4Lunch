package com.xavier_carpentier.go4lunch.domain.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class RestaurantDomain {
    private final String uidRestaurant;

    private final String restaurantName;

    private final String vicinity;

    private final String photoReferenceUrl;

    private final Double rating;

    private final String phoneNumber;

    private final String websiteUrl;


    public RestaurantDomain(String uidRestaurant, String restaurantName, String vicinity, String photoReferenceUrl, Double rating, String phoneNumber, String websiteUrl) {
        this.uidRestaurant = uidRestaurant;
        this.restaurantName = restaurantName;
        this.vicinity = vicinity;
        this.photoReferenceUrl = photoReferenceUrl;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.websiteUrl = websiteUrl;
    }


    public String getUidRestaurant() {
        return uidRestaurant;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantDomain that = (RestaurantDomain) o;
        return uidRestaurant.equals(that.uidRestaurant) && restaurantName.equals(that.restaurantName) && vicinity.equals(that.vicinity) && Objects.equals(photoReferenceUrl, that.photoReferenceUrl) && Objects.equals(rating, that.rating) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(websiteUrl, that.websiteUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uidRestaurant, restaurantName, vicinity, photoReferenceUrl, rating, phoneNumber, websiteUrl);
    }


    @NonNull
    @Override
    public String toString() {
        return "DetailsRestaurantEntity{" +
                "placeId='" + uidRestaurant + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", vicinity='" + vicinity + '\'' +
                ", photoReferenceUrl='" + photoReferenceUrl + '\'' +
                ", rating=" + rating +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                '}';
    }
}