package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.xavier_carpentier.go4lunch.domain.repository.PlaceRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantDetail;

public class GetRestaurantByIdUseCase {
    private final PlaceRepository placeRepository;

    public GetRestaurantByIdUseCase(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public LiveData<RestaurantDetail> invoke(String restaurantUid){


        return Transformations.map(placeRepository.getRestaurant(restaurantUid), MapperDomainUi::restaurantDomainToRestaurantDetail);
    }
}
