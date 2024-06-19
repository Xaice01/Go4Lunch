package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.xavier_carpentier.go4lunch.domain.usecase.GetListRestaurantsUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetLocationUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.LocationUi;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantItem;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class MapViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private Application application;

    @Mock
    private GetLocationUseCase getLocationUseCase;

    @Mock
    private GetListRestaurantsUseCase getListRestaurantsUseCase;

    private MapViewModel mapViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mapViewModel = new MapViewModel(application, getLocationUseCase, getListRestaurantsUseCase);
    }

    @Test
    public void testGetLocationLiveData() {
        // Given
        MutableLiveData<LocationUi> locationLiveData = new MutableLiveData<>();
        LocationUi location = new LocationUi("10.0", "20.0");
        locationLiveData.setValue(location);
        when(getLocationUseCase.invoke()).thenReturn(locationLiveData);

        // When
        LiveData<LocationUi> result = mapViewModel.getLocationLiveData();

        // Then
        verify(getLocationUseCase).invoke();
        assertEquals(location, result.getValue());
    }

    @Test
    public void testGetRestaurantsLiveData() {
        // Given
        String latitude = "10.0";
        String longitude = "20.0";
        MutableLiveData<List<RestaurantItem>> restaurantsLiveData = new MutableLiveData<>();
        RestaurantItem restaurantItem = new RestaurantItem("uid", "name", "address", 100, 4.0, "10.0", "20.0", 5, true, null);
        List<RestaurantItem> restaurantList = Arrays.asList(restaurantItem);
        restaurantsLiveData.setValue(restaurantList);
        when(getListRestaurantsUseCase.invoke(latitude, longitude)).thenReturn(restaurantsLiveData);

        // When
        LiveData<List<RestaurantItem>> result = mapViewModel.getRestaurantsLiveData(latitude, longitude);

        // Then
        verify(getListRestaurantsUseCase).invoke(latitude, longitude);
        assertEquals(restaurantList, result.getValue());
    }

    @Test
    public void testStartLocationUpdates() {
        // When
        mapViewModel.startLocationUpdates();

        // Then
        verify(getLocationUseCase).startLocationUpdates();
    }

    @Test
    public void testStopLocationUpdates() {
        // When
        mapViewModel.stopLocationUpdates();

        // Then
        verify(getLocationUseCase).stopLocationUpdates();
    }
}
