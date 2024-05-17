package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.xavier_carpentier.go4lunch.data.RetrofitService;
import com.xavier_carpentier.go4lunch.data.repository.PlaceRepositoryRetrofit;
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

    private final MutableLiveData<String> userQueryMutableLiveData;
    private final LiveData<List<AutocompletePrediction>> predictionsLiveData;
    private LiveData<List<AutocompletePrediction>> listPrediction;
    private String latitude;
    private String longitude;

    private String input;

    //----------------------------------------------------
    //UseCase
    //----------------------------------------------------

    private final GetAutocompleteLiveDataUseCase getAutocompleteLiveDataUseCase = new GetAutocompleteLiveDataUseCase(placeRepositoryRetrofit);


    public MainViewModel(){
        initLiveDataPosition();
        userQueryMutableLiveData = new MutableLiveData<>();

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
    private void initLiveDataPosition(){
        longitude="3.057256";
        latitude="50.62925";
    }
    //private void initLiveDataAutocompletePrediction(String input){
    //    initLiveDataPosition();
    //   // if (input == null || input.isEmpty() || input.length() < 3) {
    //        listPrediction = new MutableLiveData<>(Collections.emptyList());
    //   // } else {
    //   //     Log.d(TAG, "invoke the getautocompletelivedatausecase");
    //    List<AutocompletePrediction> predictionList = getAutocompleteLiveDataUseCase.invoke(input, latitude, longitude).getValue();
    //        listPrediction.setValue(predictionList);
    //   // }
    //}

    //public LiveData<List<AutocompletePrediction>> getLiveDataPrediction(){
    //    initLiveDataAutocompletePrediction(input);
    //    return getAutocompleteLiveDataUseCase.invoke(input, latitude, longitude);
    //}

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

    //public void onQueryChanged(String newText) {
    //    input=newText;
    //    initLiveDataAutocompletePrediction(input);
    //}

    public void onQueryChanged(String query) {
        if (query == null || query.isEmpty()) {
            //resetPredictionPlaceIdUseCase.invoke();
            userQueryMutableLiveData.setValue(null);
            return;
        }
        userQueryMutableLiveData.setValue(query);
    }

    public void onPredictionClicked(String placeId) {
        //savePredictionPlaceIdUseCase.invoke(placeId);
        userQueryMutableLiveData.setValue(null);
    }

    public void onPredictionPlaceIdReset() {
        userQueryMutableLiveData.setValue(null);
        //resetPredictionPlaceIdUseCase.invoke();
    }
}
