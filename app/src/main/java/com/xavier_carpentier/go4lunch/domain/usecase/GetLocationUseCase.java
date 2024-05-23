package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.xavier_carpentier.go4lunch.domain.repository.LocationRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.LocationUi;

public class GetLocationUseCase {
    private final LocationRepository locationRepository;

    public GetLocationUseCase(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LiveData<LocationUi> invoke() {
        return Transformations.map(locationRepository.getLocationLiveData(), MapperDomainUi::locationDomainToLocationUi);
    }
    public void startLocationUpdates(){locationRepository.startLocationUpdates();}

    public void stopLocationUpdates() {
        locationRepository.stopLocationUpdates();
    }
}
