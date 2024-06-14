package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkManager;

import com.xavier_carpentier.go4lunch.data.repository.UserRepositoryFirestore;
import com.xavier_carpentier.go4lunch.datasource.api.RetrofitService;
import com.xavier_carpentier.go4lunch.data.repository.LocationRepository;
import com.xavier_carpentier.go4lunch.data.repository.PermissionLocationRepository;
import com.xavier_carpentier.go4lunch.data.repository.PlaceRepositoryRetrofit;
import com.xavier_carpentier.go4lunch.domain.usecase.CheckLocationPermissionUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetAutocompleteLiveDataUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetLocationUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetWorkmateUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.AutocompletePrediction;
import com.xavier_carpentier.go4lunch.presentation.model.LocationUi;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;
import com.xavier_carpentier.go4lunch.presentation.ui.utils.Event;
import com.xavier_carpentier.go4lunch.utils.NotificationScheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainViewModel extends ViewModel {
    //------------------------------------
    // DATA
    //------------------------------------
    private PlaceRepositoryRetrofit placeRepositoryRetrofit = new PlaceRepositoryRetrofit(RetrofitService.getPlaceApi());
    private UserRepositoryFirestore userRepositoryFirestore = UserRepositoryFirestore.getInstance();

    public MutableLiveData<String> userQueryMutableLiveData;
    private final LiveData<List<AutocompletePrediction>> predictionsLiveData;
    private final MutableLiveData<Boolean> requestLocationPermission = new MutableLiveData<>();
    private final MutableLiveData<String> userUidLiveData = new MutableLiveData<>();
    private final MutableLiveData<Event<String>> navigateToDetailEvent = new MutableLiveData<>();
    private final MutableLiveData<Event<String>> showToastEvent = new MutableLiveData<>();
    public String latitude;
    public String longitude;

    //----------------------------------------------------
    //UseCase
    //----------------------------------------------------

    private GetAutocompleteLiveDataUseCase getAutocompleteLiveDataUseCase = new GetAutocompleteLiveDataUseCase(placeRepositoryRetrofit);
    private GetWorkmateUseCase getWorkmateUseCase = new GetWorkmateUseCase(userRepositoryFirestore);
    private final CheckLocationPermissionUseCase checkLocationPermissionUseCase;
    private final GetLocationUseCase getLocationUseCase;
    private final NotificationScheduler notificationScheduler;


    public MainViewModel(Application application){

        //for notification
        WorkManager workManager = WorkManager.getInstance(application);
        //need to instantiation
        notificationScheduler = new NotificationScheduler(workManager);
        userQueryMutableLiveData = new MutableLiveData<>();

        PermissionLocationRepository permissionLocationRepository = new PermissionLocationRepository(application.getApplicationContext());
        checkLocationPermissionUseCase = new CheckLocationPermissionUseCase(permissionLocationRepository);
        LocationRepository locationRepository = new LocationRepository(application);
        getLocationUseCase = new GetLocationUseCase(locationRepository);
        setLocation();

        //for Autocomplete
        LiveData<LocationUi> locationUiLiveData = Transformations.switchMap(
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

    // Constructor for testing
    public MainViewModel(
            Application application,
            PlaceRepositoryRetrofit placeRepositoryRetrofit,
            UserRepositoryFirestore userRepositoryFirestore,
            GetAutocompleteLiveDataUseCase getAutocompleteLiveDataUseCase,
            GetWorkmateUseCase getWorkmateUseCase,
            CheckLocationPermissionUseCase checkLocationPermissionUseCase,
            GetLocationUseCase getLocationUseCase,
            NotificationScheduler notificationScheduler
    ) {
        this.placeRepositoryRetrofit = placeRepositoryRetrofit;
        this.userRepositoryFirestore = userRepositoryFirestore;
        this.getAutocompleteLiveDataUseCase = getAutocompleteLiveDataUseCase;
        this.getWorkmateUseCase = getWorkmateUseCase;
        this.checkLocationPermissionUseCase = checkLocationPermissionUseCase;
        this.getLocationUseCase = getLocationUseCase;
        this.notificationScheduler = notificationScheduler;

        userQueryMutableLiveData = new MutableLiveData<>();

        // for Autocomplete
        LiveData<LocationUi> locationUiLiveData = Transformations.switchMap(
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
                        longitude = location.getLongitude();
                        latitude = location.getLatitude();
                        return getAutocompleteLiveDataUseCase.invoke(userQueryMutableLiveData.getValue(), latitude, longitude);
                    }
                });
    }

    public LiveData<Event<String>> getNavigateToDetailEvent() {
        return navigateToDetailEvent;
    }

    public LiveData<Event<String>> getShowToastEvent() {
        return showToastEvent;
    }

    public void checkWorkmateRestaurant() {
        getWorkmate().observeForever(workmate -> {
            if (workmate != null && workmate.getUidRestaurant() != null) {
                navigateToDetailEvent.setValue(new Event<>(workmate.getUidRestaurant()));
            } else {
                showToastEvent.setValue(new Event<>(null));
            }
        });
    }

    private LiveData<Workmate> getWorkmate(){
        return getWorkmateUseCase.invoke(userUidLiveData.getValue());
    }

    public void setLocation(){
        LiveData<Boolean> isPermissionLocation = checkLocationPermissionUseCase.invoke();
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


    @Override
    protected void onCleared() {
        super.onCleared();
        getLocationUseCase.stopLocationUpdates();
    }
    public void setUserUid(String uid) {
        userUidLiveData.setValue(uid);
    }
    /** @noinspection AccessStaticViaInstance*/
    //need to instance
    public void scheduleDailyNotification() {
        notificationScheduler.scheduleDailyNotification();
    }

}