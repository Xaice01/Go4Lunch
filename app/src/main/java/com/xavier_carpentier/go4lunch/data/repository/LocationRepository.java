package com.xavier_carpentier.go4lunch.data.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.xavier_carpentier.go4lunch.data.mappers.MapperDataToDomain;
import com.xavier_carpentier.go4lunch.domain.model.LocationDomain;

import java.util.Objects;

public class LocationRepository implements com.xavier_carpentier.go4lunch.domain.repository.LocationRepository {

    private static final long UPDATE_INTERVAL = 30000;  // 30 seconds
    private static final long FASTEST_INTERVAL = 10000; // 10 seconds

    public final MutableLiveData<Location> locationLiveData = new MutableLiveData<>();
    public final FusedLocationProviderClient fusedLocationClient;
    public LocationCallback locationCallback;

    public LocationRepository(Context context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        startLocationUpdates();
    }

    public LiveData<LocationDomain> getLocationLiveData() {
        return Transformations.map(locationLiveData, MapperDataToDomain::locationToLocationDomain);
    }

    @SuppressLint("MissingPermission")
    public void startLocationUpdates() {
        LocationRequest locationRequest = new LocationRequest.Builder(UPDATE_INTERVAL)
                .setMinUpdateIntervalMillis(FASTEST_INTERVAL)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .build();

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                if(locationLiveData.getValue()==null){
                    locationLiveData.postValue(locationResult.getLastLocation());
                }else {
                    if (Objects.requireNonNull(locationResult.getLastLocation()).getLatitude() != locationLiveData.getValue().getLatitude() || locationResult.getLastLocation().getLongitude() != locationLiveData.getValue().getLongitude()) {
                        locationLiveData.postValue(locationResult.getLastLocation());
                    }
                }
            }
        };

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    public void stopLocationUpdates() {
        if (locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }
}
