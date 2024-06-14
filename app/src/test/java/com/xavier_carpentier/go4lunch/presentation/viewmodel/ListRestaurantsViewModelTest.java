package com.xavier_carpentier.go4lunch.presentation.viewmodel;

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

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ListRestaurantsViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private GetLocationUseCase getLocationUseCase;
    @Mock
    private GetListRestaurantsUseCase getListRestaurantsUseCase;

    private ListRestaurantsViewModel viewModel;
    private MutableLiveData<LocationUi> locationLiveData;
    private MutableLiveData<List<RestaurantItem>> restaurantsLiveData;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        locationLiveData = new MutableLiveData<>();
        restaurantsLiveData = new MutableLiveData<>();

        when(getLocationUseCase.invoke()).thenReturn(locationLiveData);
        when(getListRestaurantsUseCase.invoke(anyString(), anyString())).thenReturn(restaurantsLiveData);

        viewModel = new ListRestaurantsViewModel(getLocationUseCase, getListRestaurantsUseCase, locationLiveData);
    }

    @Test
    public void testGetAllRestaurant_withValidLocation() {
        // Given
        LocationUi locationUi = new LocationUi("123", "456");
        locationLiveData.setValue(locationUi);

        RestaurantItem mockRestaurant = new RestaurantItem(
                "1", "Restaurant", "Address", 100, 5,
                "123", "456", 3, true, null);
        List<RestaurantItem> mockRestaurantList = Collections.singletonList(mockRestaurant);
        restaurantsLiveData.setValue(mockRestaurantList);

        // When
        LiveData<List<RestaurantItem>> result = viewModel.getAllRestaurant();

        // Then
        assertNotNull(result);
        assertEquals(mockRestaurantList, result.getValue());
    }

    @Test
    public void testGetAllRestaurant_withNullLocation() {
        // Given
        locationLiveData.setValue(null);

        // When
        LiveData<List<RestaurantItem>> result = viewModel.getAllRestaurant();

        // Then
        assertNotNull(result);
        assertTrue(result.getValue().isEmpty());
    }

    @Test
    public void testStopLocationUpdates() {
        // When
        viewModel.stopLocationUpdates();

        // Then
        getLocationUseCase.stopLocationUpdates();
    }
}