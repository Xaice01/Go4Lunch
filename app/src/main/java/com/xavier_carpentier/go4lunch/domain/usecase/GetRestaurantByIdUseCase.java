package com.xavier_carpentier.go4lunch.domain.usecase;

import android.net.Uri;

import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantDetail;

public class GetRestaurantByIdUseCase {
    private final AuthUserRepository userRepository;

    public GetRestaurantByIdUseCase(AuthUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RestaurantDetail invoke(String restaurantUid){
        //TODO get restaurant by api place google

        Uri urifake = Uri.parse("android.resource://com.xavier_carpentier.go4lunch/drawable/detail_restaurant_picture.jpg");

        RestaurantDetail fakeRestaurant = new RestaurantDetail(restaurantUid,urifake,"fakeRestaurant1","French","18 rue du fake",2,"0645918962",true,"http://lama.com");

        return fakeRestaurant;
    }
}
