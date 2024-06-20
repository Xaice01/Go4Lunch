package com.xavier_carpentier.go4lunch.domain.usecase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xavier_carpentier.go4lunch.domain.repository.LocationRepository;
import com.xavier_carpentier.go4lunch.presentation.model.LocationUi;
import com.xavier_carpentier.go4lunch.domain.model.LocationDomain;

public class GetLocationUseCaseTest {

    private GetLocationUseCase getLocationUseCase;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private Observer<LocationUi> observer;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getLocationUseCase = new GetLocationUseCase(locationRepository);
    }

    @Test
    public void testInvoke() {
        // Given
        MutableLiveData<LocationDomain> locationDomainLiveData = new MutableLiveData<>();
        LocationDomain locationDomain = new LocationDomain("10.0", "20.0");
        locationDomainLiveData.setValue(locationDomain);

        LocationUi expectedLocationUi = new LocationUi("10.0", "20.0");

        when(locationRepository.getLocationLiveData()).thenReturn(locationDomainLiveData);

        // When
        LiveData<LocationUi> result = getLocationUseCase.invoke();
        result.observeForever(observer);

        // Then
        verify(observer).onChanged(expectedLocationUi);
        assertEquals(expectedLocationUi, result.getValue());
    }

    @Test
    public void testStartLocationUpdates() {
        // When
        getLocationUseCase.startLocationUpdates();

        // Then
        verify(locationRepository).startLocationUpdates();
    }

    @Test
    public void testStopLocationUpdates() {
        // When
        getLocationUseCase.stopLocationUpdates();

        // Then
        verify(locationRepository).stopLocationUpdates();
    }
}