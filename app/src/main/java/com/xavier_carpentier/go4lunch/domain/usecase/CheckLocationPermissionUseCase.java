package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.lifecycle.LiveData;

import com.xavier_carpentier.go4lunch.domain.repository.PermissionLocationRepository;


public class CheckLocationPermissionUseCase {

    private final PermissionLocationRepository repository;

    public CheckLocationPermissionUseCase(PermissionLocationRepository repository) {
        this.repository = repository;
    }

    public LiveData<Boolean> invoke(){
        return repository.checkLocationPermission();
    }
}
