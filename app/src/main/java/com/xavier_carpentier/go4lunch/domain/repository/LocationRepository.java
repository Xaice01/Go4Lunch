package com.xavier_carpentier.go4lunch.domain.repository;

import android.location.Location;

import androidx.lifecycle.LiveData;

import com.xavier_carpentier.go4lunch.domain.model.LocationDomain;


public interface LocationRepository {

    public void stopLocationUpdates();

    public LiveData<LocationDomain> getLocationLiveData();
    public void startLocationUpdates();
}
