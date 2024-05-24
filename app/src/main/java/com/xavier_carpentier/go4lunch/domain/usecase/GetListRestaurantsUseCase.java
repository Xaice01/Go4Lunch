package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.domain.repository.PlaceRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantItem;

import java.util.List;

public class GetListRestaurantsUseCase {
    private final AuthUserRepository userRepository;
    private final PlaceRepository placeRepository;
    private static final String RADIUS = "1500";
    private static final String TYPES = "restaurant";

    public GetListRestaurantsUseCase(AuthUserRepository userRepository,PlaceRepository placeRepository) {
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
    }

    public LiveData<List<RestaurantItem>> invoke(String userLatitude, String userLongitude){
        //TODO get number of workmates to eat

        return Transformations.map(placeRepository.getListRestaurant(userLatitude,userLongitude,RADIUS,TYPES), MapperDomainUi::listRestaurantSearchDomainTolistRestaurantItem);

    }

}
