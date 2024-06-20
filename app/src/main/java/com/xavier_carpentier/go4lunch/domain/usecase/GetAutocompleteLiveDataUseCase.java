package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.xavier_carpentier.go4lunch.domain.repository.PlaceRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.AutocompletePrediction;

import java.util.List;

public class GetAutocompleteLiveDataUseCase {
    private static final String RADIUS = "5000";
    private static final String TYPES = "restaurant";
    private final PlaceRepository placeRepository;


    public GetAutocompleteLiveDataUseCase(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }


    public LiveData<List<AutocompletePrediction>> invoke(String input,String latitude,String longitude){

        return Transformations.map(placeRepository.getAutocomplete(input,latitude,longitude,RADIUS,TYPES), MapperDomainUi::listAutocompletePredictionDomainToListAutocompletePrediction);
    }
}