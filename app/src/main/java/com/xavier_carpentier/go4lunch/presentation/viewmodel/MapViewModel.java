package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.xavier_carpentier.go4lunch.datasource.api.RetrofitService;
import com.xavier_carpentier.go4lunch.data.repository.LocationRepository;
import com.xavier_carpentier.go4lunch.data.repository.PlaceRepositoryRetrofit;
import com.xavier_carpentier.go4lunch.data.repository.UserRepositoryFirestore;
import com.xavier_carpentier.go4lunch.domain.usecase.GetListRestaurantsUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetLocationUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.LocationUi;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantItem;

import java.util.List;

public class MapViewModel extends AndroidViewModel {
    private PlaceRepositoryRetrofit placeRepositoryRetrofit = new PlaceRepositoryRetrofit(RetrofitService.getPlaceApi());
    private UserRepositoryFirestore userRepositoryFirestore = UserRepositoryFirestore.getInstance();


    private final GetLocationUseCase getLocationUseCase;
    private GetListRestaurantsUseCase getListRestaurantsUseCase = new GetListRestaurantsUseCase(placeRepositoryRetrofit,userRepositoryFirestore);

    public MapViewModel(@NonNull Application application) {
        super(application);
        // Initialize the use cases (you might need to pass your repositories here)
        // Replace the repository initializations with actual instances
        LocationRepository locationRepository = new LocationRepository(application);
        getLocationUseCase = new GetLocationUseCase(locationRepository);
    }
    // Constructor for testing
    public MapViewModel(@NonNull Application application, GetLocationUseCase getLocationUseCase, GetListRestaurantsUseCase getListRestaurantsUseCase) {
        super(application);
        this.placeRepositoryRetrofit = null;
        this.userRepositoryFirestore = null;
        this.getLocationUseCase = getLocationUseCase;
        this.getListRestaurantsUseCase = getListRestaurantsUseCase;
    }


    public LiveData<LocationUi> getLocationLiveData() {
        return getLocationUseCase.invoke();
    }

    public LiveData<List<RestaurantItem>> getRestaurantsLiveData(String latitude, String longitude) {
        return getListRestaurantsUseCase.invoke(latitude, longitude);
    }

    public void startLocationUpdates() {
        getLocationUseCase.startLocationUpdates();
    }

    public void stopLocationUpdates() {
        getLocationUseCase.stopLocationUpdates();
    }

}