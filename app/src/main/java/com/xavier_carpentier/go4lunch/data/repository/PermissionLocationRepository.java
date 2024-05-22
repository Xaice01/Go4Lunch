package com.xavier_carpentier.go4lunch.data.repository;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PermissionLocationRepository implements com.xavier_carpentier.go4lunch.domain.repository.PermissionLocationRepository {

    private MutableLiveData<Boolean> isPermissionLiveData = new MutableLiveData<>();
    private final Context context;

    public PermissionLocationRepository(Context context){
        this.context = context;
    }

    public LiveData<Boolean> checkLocationPermission(){
        setLocationPermission();
        return isPermissionLiveData;
    }

    public void setLocationPermission(){
        Boolean setLocationPermission = (ContextCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED);
        isPermissionLiveData.setValue(setLocationPermission);
    }
}