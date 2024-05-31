package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Transformations;

import com.xavier_carpentier.go4lunch.domain.repository.PlaceRepository;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetListRestaurantsUseCase {
    private final PlaceRepository placeRepository;
    private final UsersRepository userRepository;
    private static final String RADIUS = "1000";
    private static final String TYPES = "restaurant";

    public GetListRestaurantsUseCase( PlaceRepository placeRepository, UsersRepository userRepository) {
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
    }


    public LiveData<List<RestaurantItem>> invoke(String userLatitude, String userLongitude) {
        return Transformations.switchMap(placeRepository.getListRestaurant(userLatitude, userLongitude, RADIUS, TYPES), restaurantSearchDomains -> {
            List<RestaurantItem> restaurantItems = MapperDomainUi.listRestaurantSearchDomainTolistRestaurantItem(restaurantSearchDomains);

            MediatorLiveData<List<RestaurantItem>> mediatorLiveData = new MediatorLiveData<>();
            List<RestaurantItem> finalRestaurantItems = new ArrayList<>(Collections.nCopies(restaurantItems.size(), null));

            for (int i = 0; i < restaurantItems.size(); i++) {
                final int index = i;
                LiveData<RestaurantItem> updatedItemLiveData = getUpdatedRestaurantItem(restaurantItems.get(i));

                mediatorLiveData.addSource(updatedItemLiveData, updatedItem -> {
                    finalRestaurantItems.set(index, updatedItem);

                    if (!finalRestaurantItems.contains(null)) {
                        mediatorLiveData.setValue(new ArrayList<>(finalRestaurantItems));
                    }
                });
            }

            return mediatorLiveData;
        });
    }

    private LiveData<RestaurantItem> getUpdatedRestaurantItem(RestaurantItem restaurantItem) {
        return Transformations.map(userRepository.getListWorkmateToChoiceARestaurant(restaurantItem.getUid()), workmates -> {
            int workmatesCount = workmates != null ? workmates.size() : 0;
            restaurantItem.setWorkmatesToEat(workmatesCount); // Update the RestaurantItem
            return restaurantItem;
        });
    }
}
