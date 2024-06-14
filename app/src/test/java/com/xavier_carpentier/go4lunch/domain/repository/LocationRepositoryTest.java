package com.xavier_carpentier.go4lunch.domain.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.junit.runner.RunWith;

import com.xavier_carpentier.go4lunch.domain.model.LocationDomain;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class LocationRepositoryTest {

    private LocationRepository locationRepository;

    @Mock
    private LocationRepository mockLocationRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        locationRepository = mockLocationRepository;
    }

    @Test
    public void testStopLocationUpdates() {
        // When
        locationRepository.stopLocationUpdates();

        // Then
        verify(mockLocationRepository, times(1)).stopLocationUpdates();
    }

    @Test
    public void testStartLocationUpdates() {
        // When
        locationRepository.startLocationUpdates();

        // Then
        verify(mockLocationRepository, times(1)).startLocationUpdates();
    }

    @Test
    public void testGetLocationLiveData() {
        // Given
        MutableLiveData<LocationDomain> expectedLiveData = new MutableLiveData<>();
        LocationDomain expectedLocation = new LocationDomain("48.8566", "2.3522");
        expectedLiveData.setValue(expectedLocation);

        when(locationRepository.getLocationLiveData()).thenReturn(expectedLiveData);

        // When
        LiveData<LocationDomain> actualLiveData = locationRepository.getLocationLiveData();

        // Then
        verify(mockLocationRepository, times(1)).getLocationLiveData();
        assertEquals(expectedLiveData.getValue(), actualLiveData.getValue());
    }
}