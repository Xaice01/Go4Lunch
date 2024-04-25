package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.xavier_carpentier.go4lunch.domain.repository.UserRepository;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantList;

import java.util.ArrayList;
import java.util.List;

public class GetListRestaurantsUseCase {
    private final UserRepository userRepository;

    public GetListRestaurantsUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<List<RestaurantList>> invoke(){
        //TODO create a list with restaurant based on text auto complete and localisation

        RestaurantList fakeRestaurant1 = new RestaurantList("1","fakeRestaurant1","French","18 rue du fake",300,3,6,"open until 2 pm",null);
        RestaurantList fakeRestaurant2 = new RestaurantList("2","fakeRestaurant2","Spainish","31 rue du fake2",180,1,0,"open until 7 pm",null);
        RestaurantList fakeRestaurant3 = new RestaurantList("3","fakeRestaurant3","French","5 rue du fake3",20,0,2,"closing soon",null);
        MutableLiveData<List<RestaurantList>> restaurantLivedata = new MutableLiveData<>();

        List<RestaurantList> fakeData =new ArrayList<>();
        fakeData.add(fakeRestaurant1);
        fakeData.add(fakeRestaurant2);
        fakeData.add(fakeRestaurant3);
        restaurantLivedata.setValue(fakeData);

        return restaurantLivedata;
    }

}
