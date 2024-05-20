package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.xavier_carpentier.go4lunch.data.RetrofitService;
import com.xavier_carpentier.go4lunch.data.repository.PermissionLocationRepository;
import com.xavier_carpentier.go4lunch.data.repository.PlaceRepositoryRetrofit;
import com.xavier_carpentier.go4lunch.domain.usecase.CheckLocationPermissionUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetAutocompleteLiveDataUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.AutocompletePrediction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainViewModel extends ViewModel {
    //------------------------------------
    // DATA
    //------------------------------------
    private final PlaceRepositoryRetrofit placeRepositoryRetrofit = new PlaceRepositoryRetrofit(RetrofitService.getPlaceApi());

    private PermissionLocationRepository permissionLocationRepository = null;

    private final MutableLiveData<String> userQueryMutableLiveData;
    private final LiveData<List<AutocompletePrediction>> predictionsLiveData;
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


    public MainViewModel(Context context){
        userQueryMutableLiveData = new MutableLiveData<>();

        permissionLocationRepository = new PermissionLocationRepository(context);
        checkLocationPermissionUseCase = new CheckLocationPermissionUseCase(permissionLocationRepository);

        setLocation();

        predictionsLiveData = Transformations.switchMap(
                userQueryMutableLiveData, userQuery -> {
                    if (userQuery == null || userQuery.isEmpty() || userQuery.length() < 3) {
                        return new MutableLiveData<>(Collections.emptyList());
                    } else {
                        return getAutocompleteLiveDataUseCase.invoke(userQuery,latitude,longitude);
                    }
                }
        );
    }


    public void setLocation(){
        isPermissionLocation = checkLocationPermissionUseCase.invoke();
        if(Boolean.TRUE.equals(isPermissionLocation.getValue())) {
            initLiveDataPosition();
        }else{
            onSomeActionThatRequiresPermission();
        }
        //permi

    }
    //public getlocation

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
        longitude="3.057256";
        latitude="50.62925";
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
}
