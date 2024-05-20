package com.xavier_carpentier.go4lunch.domain.repository;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;

public interface PermissionLocationRepository {

    public LiveData<Boolean> checkLocationPermission();

}
