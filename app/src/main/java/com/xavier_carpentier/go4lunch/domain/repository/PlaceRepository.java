package com.xavier_carpentier.go4lunch.domain.repository;

import androidx.lifecycle.LiveData;

import com.xavier_carpentier.go4lunch.domain.model.RestaurantDomain;
import com.xavier_carpentier.go4lunch.domain.model.AutocompletePredictionDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantSearchDomain;

import java.util.List;

public interface PlaceRepository {
    void getCurrentLocation();


    LiveData<List<AutocompletePredictionDomain>> getAutocomplete(String input, String latitude,String longitude, String radius, String types);

    LiveData<List<RestaurantSearchDomain>> getListRestaurant(String latitude, String longitude, String radius, String types);

    LiveData<RestaurantDomain> getRestaurant(String uidRestaurant);
}
