package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Transformations;

import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.domain.repository.PlaceRepository;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantItem;

import java.util.ArrayList;
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

            List<LiveData<RestaurantItem>> updatedRestaurantItems = new ArrayList<>();
            for (RestaurantItem item : restaurantItems) {
                updatedRestaurantItems.add(getUpdatedRestaurantItem(item));
            }

            MediatorLiveData<List<RestaurantItem>> mediatorLiveData = new MediatorLiveData<>();
            List<RestaurantItem> finalRestaurantItems = new ArrayList<>(restaurantItems.size());
            for (int i = 0; i < restaurantItems.size(); i++) {
                finalRestaurantItems.add(null);
            }

            for (int i = 0; i < updatedRestaurantItems.size(); i++) {
                final int index = i;
                mediatorLiveData.addSource(updatedRestaurantItems.get(i), updatedItem -> {
                    finalRestaurantItems.set(index, updatedItem);
                    mediatorLiveData.setValue(new ArrayList<>(finalRestaurantItems));
                });
            }

            return mediatorLiveData;
        });
    }

    private LiveData<RestaurantItem> getUpdatedRestaurantItem(RestaurantItem item) {
        return Transformations.map(userRepository.getListWorkmateToChoiceARestaurant(item.getUid()), workmateList -> {
            int workmatesToEat = workmateList.size();
            return new RestaurantItem(
                    item.getUid(),
                    item.getName(),
                    item.getAddress(),
                    item.getDistance(),
                    item.getNote(),
                    item.getLatitude(),
                    item.getLongitude(),
                    workmatesToEat,
                    item.getIsOpen(),
                    item.getPicture()
            );
        });
    }
}
