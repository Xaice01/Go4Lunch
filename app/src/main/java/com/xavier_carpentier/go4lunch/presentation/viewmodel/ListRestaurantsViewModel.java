package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetListRestaurantsUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantList;

import java.util.List;

public class ListRestaurantsViewModel extends ViewModel {
    //------------------------------------
    // DATA
    //------------------------------------
    private final AuthRepositoryFirebase authRepositoryFirebase = AuthRepositoryFirebase.getInstance();
    private LiveData<List<RestaurantList>> listRestaurants;
    //----------------------------------------------------
    //UseCase
    //----------------------------------------------------
    private final GetListRestaurantsUseCase getListRestaurantsUseCase = new GetListRestaurantsUseCase(authRepositoryFirebase);

    private LiveData<List<RestaurantList>> getListRestaurants(){
        listRestaurants=getListRestaurantsUseCase.invoke();
        return listRestaurants;
    }

    public LiveData<List<RestaurantList>> getAllRestaurant(){

        return getListRestaurants();
    }
}
