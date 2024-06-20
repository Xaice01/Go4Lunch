package com.xavier_carpentier.go4lunch.data.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.location.Location;
import android.os.Looper;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.xavier_carpentier.go4lunch.data.mappers.MapperDataToDomain;
import com.xavier_carpentier.go4lunch.domain.model.LocationDomain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Collections;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class LocationRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private FusedLocationProviderClient fusedLocationClient;

    @Mock
    private Observer<LocationDomain> observer;

    @Mock
    private LocationResult locationResult;

    @Captor
    private ArgumentCaptor<LocationCallback> locationCallbackCaptor;

    private LocationRepository locationRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        locationRepository = new LocationRepository(fusedLocationClient);
    }

    @Test
    public void testGetLocationLiveData() {
        locationRepository.getLocationLiveData().observeForever(observer);
        Location location = new Location("provider");
        location.setLatitude(40.748817);
        location.setLongitude(-73.985428);
        locationRepository.locationLiveData.setValue(location);

        LocationDomain expected = MapperDataToDomain.locationToLocationDomain(location);

        verify(observer).onChanged(expected);
    }

    @Test
    public void testStartLocationUpdates() {
        locationRepository.startLocationUpdates();
        verify(fusedLocationClient).requestLocationUpdates(
                (LocationRequest) org.mockito.ArgumentMatchers.any(),
                locationCallbackCaptor.capture(),
                (Looper) org.mockito.ArgumentMatchers.any()
        );

        Location location = new Location("provider");
        location.setLatitude(40.748817);
        location.setLongitude(-73.985428);

        when(locationResult.getLastLocation()).thenReturn(location);
        LocationCallback locationCallback = locationCallbackCaptor.getValue();
        locationCallback.onLocationResult(locationResult);

        assertEquals(location, locationRepository.locationLiveData.getValue());
    }

    @Test
    public void testStopLocationUpdates() {
        locationRepository.startLocationUpdates();
        verify(fusedLocationClient).requestLocationUpdates(
                (LocationRequest) org.mockito.ArgumentMatchers.any(),
                locationCallbackCaptor.capture(),
                (Looper) org.mockito.ArgumentMatchers.any()
        );

        LocationCallback locationCallback = locationCallbackCaptor.getValue();
        locationRepository.stopLocationUpdates();

        verify(fusedLocationClient).removeLocationUpdates(locationCallback);
    }

    @Test
    public void testUpdateLocation() {
        locationRepository.startLocationUpdates();
        Location initialLocation = new Location("provider");
        initialLocation.setLatitude(40.748817);
        initialLocation.setLongitude(-73.985428);

        Location newLocation = new Location("provider");
        newLocation.setLatitude(40.748817);
        newLocation.setLongitude(-73.985429);

        locationRepository.locationLiveData.setValue(initialLocation);
        locationRepository.getLocationLiveData().observeForever(observer);

        LocationDomain expected = MapperDataToDomain.locationToLocationDomain(newLocation);

        locationRepository.locationCallback.onLocationResult(LocationResult.create(Collections.singletonList(newLocation)));

        verify(observer).onChanged(expected);
    }
}