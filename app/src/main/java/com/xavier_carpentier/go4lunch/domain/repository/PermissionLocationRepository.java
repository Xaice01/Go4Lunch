package com.xavier_carpentier.go4lunch.domain.repository;

import androidx.lifecycle.LiveData;

public interface PermissionLocationRepository {

    LiveData<Boolean> checkLocationPermission();

}
