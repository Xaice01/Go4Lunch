package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.xavier_carpentier.go4lunch.data.RetrofitService;
import com.xavier_carpentier.go4lunch.data.repository.LocationRepository;
import com.xavier_carpentier.go4lunch.data.repository.PermissionLocationRepository;
import com.xavier_carpentier.go4lunch.data.repository.PlaceRepositoryRetrofit;
import com.xavier_carpentier.go4lunch.domain.usecase.CheckLocationPermissionUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetAutocompleteLiveDataUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetLocationUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.AutocompletePrediction;
import com.xavier_carpentier.go4lunch.presentation.model.LocationUi;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainViewModel extends ViewModel {
    //------------------------------------
    // DATA
    //------------------------------------
    private final PlaceRepositoryRetrofit placeRepositoryRetrofit = new PlaceRepositoryRetrofit(RetrofitService.getPlaceApi());

    private PermissionLocationRepository permissionLocationRepository ;

    private MutableLiveData<String> userQueryMutableLiveData;
    private LiveData<List<AutocompletePrediction>> predictionsLiveData;
    private LiveData<LocationUi> locationUiLiveData;
    private final MutableLiveData<Boolean> requestLocationPermission = new MutableLiveData<>();
    private LiveData<Boolean> isPermissionLocation;
    private String latitude;
    private String longitude;

    private String input;

    //----------------------------------------------------
    //UseCase
    //----------------------------------------------------

    private final GetAutocompleteLiveDataUseCase getAutocompleteLiveDataUseCase = new GetAutocompleteLiveDataUseCase(placeRepositoryRetrofit);
    private CheckLocationPermissionUseCase checkLocationPermissionUseCase;
    private GetLocationUseCase getLocationUseCase;


    public MainViewModel(Application application){
        userQueryMutableLiveData = new MutableLiveData<>();

        permissionLocationRepository = new PermissionLocationRepository(application.getApplicationContext());
        checkLocationPermissionUseCase = new CheckLocationPermissionUseCase(permissionLocationRepository);
        LocationRepository locationRepository = new LocationRepository(application);
        getLocationUseCase = new GetLocationUseCase(locationRepository);
        setLocation();

        locationUiLiveData = Transformations.switchMap(
                userQueryMutableLiveData, userQuery -> {
                    if (userQuery == null || userQuery.isEmpty() || userQuery.length() < 3) {
                        return new MutableLiveData<>();
                    } else {
                        return getLocationUseCase.invoke();
                    }
                });

        predictionsLiveData = Transformations.switchMap(
                locationUiLiveData, location -> {
                    if(location == null){
                        return new MutableLiveData<>(Collections.emptyList());
                    }else{
                        longitude=location.getLongitude();
                        latitude=location.getLatitude();
                        return getAutocompleteLiveDataUseCase.invoke(userQueryMutableLiveData.getValue(),latitude,longitude);

                    }
                });

    }

    public void setLocation(){
        isPermissionLocation = checkLocationPermissionUseCase.invoke();
        if(Boolean.TRUE.equals(isPermissionLocation.getValue())) {
            initLiveDataPosition();
        }else{
            //value by default
            longitude="3.057256";
            latitude="50.62925";
            onSomeActionThatRequiresPermission();
        }
    }

    public LiveData<Boolean> checkPermissionLocation(){
        return requestLocationPermission;
    }

    public void onSomeActionThatRequiresPermission() {
        // ask Permission location
        requestLocationPermission.setValue(true);
    }

    public void permissionHandled() {
        requestLocationPermission.setValue(false);
    }


    //todo to remove
    private void initLiveDataPosition(){
        getLocationUseCase.startLocationUpdates();
    }


    public LiveData<List<AutocompletePrediction>> getLiveDataPrediction(){
        return Transformations.switchMap(predictionsLiveData, predictions -> {
                    MutableLiveData<List<AutocompletePrediction>> predictionViewStateMutableLiveData = new MutableLiveData<>();
                    List<AutocompletePrediction> predictionViewStateList = new ArrayList<>();
                    if (predictions != null) {
                        for (AutocompletePrediction prediction : predictions) {
                            predictionViewStateList.add(
                                    new AutocompletePrediction(
                                            prediction.getRestaurantId(),
                                            prediction.getRestaurantName()
                                    )
                            );
                        }
                    } else {
                        predictionViewStateMutableLiveData.setValue(Collections.emptyList());
                    }
                    predictionViewStateMutableLiveData.setValue(predictionViewStateList);
                    return predictionViewStateMutableLiveData;
                }
        );
    }

    public void onQueryChanged(String query) {
        if (query == null || query.isEmpty()) {
            userQueryMutableLiveData.setValue(null);
            return;
        }
        userQueryMutableLiveData.setValue(query);
    }

    public void onPredictionPlaceIdReset() {
        userQueryMutableLiveData.setValue(null);
    }

    public LiveData<Boolean> checkLocationPermission(){
        return checkLocationPermissionUseCase.invoke();
    }

    public MainViewModel(@NonNull Closeable... closeables) {
        super(closeables);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        getLocationUseCase.stopLocationUpdates();
    }
}