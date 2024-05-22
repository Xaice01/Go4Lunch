package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import static android.content.ContentValues.TAG;

import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.xavier_carpentier.go4lunch.data.RetrofitService;
import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;
import com.xavier_carpentier.go4lunch.data.repository.PlaceRepositoryRetrofit;
import com.xavier_carpentier.go4lunch.domain.usecase.CheckLocationPermissionUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetListRestaurantsUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantItem;

import java.util.List;
import java.util.concurrent.Executor;

public class ListRestaurantsViewModel extends ViewModel {
    //------------------------------------
    // DATA
    //------------------------------------
    private final AuthRepositoryFirebase authRepositoryFirebase = AuthRepositoryFirebase.getInstance();
    private final PlaceRepositoryRetrofit placeRepositoryRetrofit = new PlaceRepositoryRetrofit(RetrofitService.getPlaceApi());
    private LiveData<List<RestaurantItem>> listRestaurants;

    private String userLatitude;
    private String userLongitude;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    //----------------------------------------------------
    //UseCase
    //----------------------------------------------------
    private final GetListRestaurantsUseCase getListRestaurantsUseCase = new GetListRestaurantsUseCase(authRepositoryFirebase,placeRepositoryRetrofit);


    private void initLiveDataRestaurants(){
        setLocalisation();
        listRestaurants=getListRestaurantsUseCase.invoke(userLatitude,userLongitude);
    }

    //todo
    private void setLocalisation(){
        //localisation by default if user don't give permission location
        userLatitude="50.62925";
        userLongitude="3.057256";
    }

    public LiveData<List<RestaurantItem>> getAllRestaurant(){
        initLiveDataRestaurants();
        return listRestaurants;
    }
}
