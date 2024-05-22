package com.xavier_carpentier.go4lunch.domain.usecase;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.domain.repository.PlaceRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantItem;

import java.util.ArrayList;
import java.util.List;

public class GetListRestaurantsUseCase {
    private final AuthUserRepository userRepository;
    private final PlaceRepository placeRepository;
    private static final String RADIUS = "5000";
    private static final String TYPES = "restaurant";

    public GetListRestaurantsUseCase(AuthUserRepository userRepository,PlaceRepository placeRepository) {
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
    }

    public LiveData<List<RestaurantItem>> invoke(String userLatitude, String userLongitude){
        //TODO get number of workmates to eat


        return Transformations.map(placeRepository.getListRestaurant(userLatitude,userLongitude,RADIUS,TYPES), MapperDomainUi::listRestaurantSearchDomainTolistRestaurantItem);

        //Uri urifake = Uri.parse("android.resource://com.xavier_carpentier.go4lunch/drawable/detail_restaurant_picture.jpg");
//
        //RestaurantItem fakeRestaurant1 = new RestaurantItem("1","fakeRestaurant1","18 rue du fake",300,3,6,true,null);
        //RestaurantItem fakeRestaurant2 = new RestaurantItem("2","fakeRestaurant2","31 rue du fake2",180,1,0,true,null);
        //RestaurantItem fakeRestaurant3 = new RestaurantItem("3","fakeRestaurant3","5 rue du fake3",20,0,2,false, urifake);
        //MutableLiveData<List<RestaurantItem>> restaurantLivedata = new MutableLiveData<>();
//
        //List<RestaurantItem> fakeData =new ArrayList<>();
        //fakeData.add(fakeRestaurant1);
        //fakeData.add(fakeRestaurant2);
        //fakeData.add(fakeRestaurant3);
        //restaurantLivedata.setValue(fakeData);
//
        //return restaurantLivedata;

       // return Transformations.map(placeRepository.getAutocomplete(input,latitude,longitude,RADIUS,TYPES), MapperDomainUi::ListAutocompletePredictionDomainToListAutocompletePrediction);

    }

}
