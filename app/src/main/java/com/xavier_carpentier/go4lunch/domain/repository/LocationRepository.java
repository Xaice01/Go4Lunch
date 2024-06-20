package com.xavier_carpentier.go4lunch.domain.repository;

import androidx.lifecycle.LiveData;

import com.xavier_carpentier.go4lunch.domain.model.LocationDomain;


public interface LocationRepository {

    void stopLocationUpdates();

    LiveData<LocationDomain> getLocationLiveData();
    void startLocationUpdates();
}
