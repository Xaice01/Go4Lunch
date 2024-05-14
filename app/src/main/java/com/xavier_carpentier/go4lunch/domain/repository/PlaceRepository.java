package com.xavier_carpentier.go4lunch.domain.repository;

import androidx.lifecycle.LiveData;

import com.xavier_carpentier.go4lunch.domain.model.RestaurantDomain;
import com.xavier_carpentier.go4lunch.domain.model.AutocompletePredictionDomain;
import androidx.lifecycle.LiveData;

import java.util.List;

public interface PlaceRepository {
    void getCurrentLocation();


    //LiveData<List<AutocompletePredictionDomain>> getAutocomplete(String input, String location, String radius, String types);


    //TODO
    //void getListRestaurant(positionExact);

    LiveData<RestaurantDomain> getRestaurant(String uidRestaurant);
}
