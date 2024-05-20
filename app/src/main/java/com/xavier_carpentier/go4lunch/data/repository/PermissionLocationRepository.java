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
        //todo to remove
    //private void getLocationPermission() {
    //    /*
    //     * Request location permission, so that we can get the location of the
    //     * device. The result of the permission request is handled by a callback,
    //     * onRequestPermissionsResult.
    //     */
    //    mLocationPermissionGranted = false;
    //    if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
    //            android.Manifest.permission.ACCESS_FINE_LOCATION)
    //            == PackageManager.PERMISSION_GRANTED) {
    //        mLocationPermissionGranted = true;
    //    } else {
    //        ActivityCompat.requestPermissions(this,
    //                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
    //                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
    //    }
    //}