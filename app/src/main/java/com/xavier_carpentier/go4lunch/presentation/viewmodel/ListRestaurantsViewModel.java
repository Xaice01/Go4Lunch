package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.xavier_carpentier.go4lunch.datasource.api.RetrofitService;
import com.xavier_carpentier.go4lunch.data.repository.LocationRepository;
import com.xavier_carpentier.go4lunch.data.repository.PlaceRepositoryRetrofit;
import com.xavier_carpentier.go4lunch.data.repository.UserRepositoryFirestore;
import com.xavier_carpentier.go4lunch.domain.usecase.GetListRestaurantsUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetLocationUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.LocationUi;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantItem;

import java.util.Collections;
import java.util.List;

public class ListRestaurantsViewModel extends ViewModel {
    //------------------------------------
    // DATA
    //------------------------------------
    private final PlaceRepositoryRetrofit placeRepositoryRetrofit = new PlaceRepositoryRetrofit(RetrofitService.getPlaceApi());
    private final UserRepositoryFirestore userRepositoryFirestore = UserRepositoryFirestore.getInstance();
    private LiveData<List<RestaurantItem>> listRestaurants;

    private String userLatitude;
    private String userLongitude;
    //----------------------------------------------------
    //UseCase
    //----------------------------------------------------
    private final GetListRestaurantsUseCase getListRestaurantsUseCase = new GetListRestaurantsUseCase(placeRepositoryRetrofit,userRepositoryFirestore);

    private final GetLocationUseCase getLocationUseCase;
    private final LiveData<LocationUi> locationLiveData;

    public ListRestaurantsViewModel(@NonNull Application application) {

        LocationRepository locationRepository = new LocationRepository(application);
        getLocationUseCase = new GetLocationUseCase(locationRepository);
        getLocationUseCase.startLocationUpdates();
        locationLiveData = getLocationUseCase.invoke();
    }

    private void initLiveDataRestaurants(){
        listRestaurants = Transformations.switchMap(
                locationLiveData, location -> {
                    if(location == null){
                        return new MutableLiveData<>(Collections.emptyList());
                    }else{
                        userLongitude=location.getLongitude();
                        userLatitude=location.getLatitude();
                        return getListRestaurantsUseCase.invoke(userLatitude,userLongitude);
                    }
                });
    }


    public LiveData<List<RestaurantItem>> getAllRestaurant(){
        initLiveDataRestaurants();
        return listRestaurants;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        getLocationUseCase.stopLocationUpdates();
    }

    public void stopLocationUpdates() {
        getLocationUseCase.stopLocationUpdates();
    }
}
