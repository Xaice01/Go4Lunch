package com.xavier_carpentier.go4lunch.data.repository;

import static com.xavier_carpentier.go4lunch.BuildConfig.GOOGLE_API_KEY;

import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Task;
import com.xavier_carpentier.go4lunch.data.GooglePlaceApi;
import com.xavier_carpentier.go4lunch.data.entity.autocomplete_response.AutocompleteResponse;
import com.xavier_carpentier.go4lunch.data.entity.autocomplete_response.Prediction;
import com.xavier_carpentier.go4lunch.data.entity.detail_restaurant_response.RestaurantDetailResponse;
import com.xavier_carpentier.go4lunch.data.entity.list_restaurant_response.ListRestaurantResponse;
import com.xavier_carpentier.go4lunch.data.entity.list_restaurant_response.Result;
import com.xavier_carpentier.go4lunch.data.mappers.MapperDataToDomain;
import com.xavier_carpentier.go4lunch.domain.model.AutocompletePredictionDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantSearchDomain;
import com.xavier_carpentier.go4lunch.domain.repository.PlaceRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceRepositoryRetrofit implements PlaceRepository {

    private final GooglePlaceApi googlePlaceApi;

    public PlaceRepositoryRetrofit(GooglePlaceApi googlePlaceApi) {
        this.googlePlaceApi = googlePlaceApi;
    }
    // In this Map we will store the responses we get from the server (corresponding to the Uid restaurant),
    // so if needed we can "get back in time", this will act like a cache !
    private final Map<String, RestaurantDetailResponse> alreadyFetchedResponsesRestaurantDetail = new HashMap<>();

    private final Map<String, List<Prediction>> alreadyFetchedResponsesListPrediction = new HashMap<>();

    private final Map<String, List<Result>> alreadyFetchedResponsesListRestaurantResponse = new HashMap<>();

    //TODO
    public void getCurrentLocation(){
        //Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();

    }

    //todo check to remove or not longitude and latitude
    public LiveData<List<AutocompletePredictionDomain>> getAutocomplete(String input, String latitude,String longitude, String radius, String types){
        MutableLiveData<List<AutocompletePredictionDomain>> autocompletePredictionDomainMutableLiveData = new MutableLiveData<>();
        //todo change latitude and longitude
        String keyCache = input + latitude + longitude + radius + types;

        List<Prediction> responseList = alreadyFetchedResponsesListPrediction.get(keyCache);
        if(responseList!=null){
            autocompletePredictionDomainMutableLiveData.setValue(MapperDataToDomain.listPredictionToAutocompletePredictionDomain(responseList));
        }else {
            googlePlaceApi.getAutocomplete(input, (latitude +","+ longitude),radius,types,GOOGLE_API_KEY).enqueue(new Callback<AutocompleteResponse>() {
                @Override
                public void onResponse(Call<AutocompleteResponse> call, Response<AutocompleteResponse> response) {
                    if(response.isSuccessful() && response.body()!=null && response.body().getPredictions() != null){
                        List<Prediction> predictionList =response.body().getPredictions();
                        alreadyFetchedResponsesListPrediction.put(keyCache,predictionList);
                        autocompletePredictionDomainMutableLiveData.setValue(MapperDataToDomain.listPredictionToAutocompletePredictionDomain(predictionList));

                    }else{
                        autocompletePredictionDomainMutableLiveData.setValue(null);
                    }
                }

                @Override
                public void onFailure(Call<AutocompleteResponse> call, Throwable t) {
                    autocompletePredictionDomainMutableLiveData.setValue(null);
                }
            });
        }
        return autocompletePredictionDomainMutableLiveData;
    }

        //todo check to remove or not longitude and latitude
    public LiveData<List<RestaurantSearchDomain>> getListRestaurant(String latitude, String longitude, String radius, String types){
        MutableLiveData<List<RestaurantSearchDomain>> listRestaurantSearchDomainMutableLiveData = new MutableLiveData<>();
        //todo change latitude and longitude

        String keyCache = latitude + longitude + radius + types;

        List<Result> responseList = alreadyFetchedResponsesListRestaurantResponse.get(keyCache);
        if(responseList!=null){
            listRestaurantSearchDomainMutableLiveData.setValue(MapperDataToDomain.listResultRestaurantResponseToListRestaurantSearchDomain(responseList,latitude,longitude));
        }else {
            googlePlaceApi.getNearby((latitude + "," + longitude), types, radius, GOOGLE_API_KEY).enqueue(new Callback<ListRestaurantResponse>() {
                @Override
                public void onResponse(Call<ListRestaurantResponse> call, Response<ListRestaurantResponse> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().getResults() != null) {
                        List<Result> resultList = response.body().getResults();
                        alreadyFetchedResponsesListRestaurantResponse.put(keyCache, resultList);
                        listRestaurantSearchDomainMutableLiveData.setValue(MapperDataToDomain.listResultRestaurantResponseToListRestaurantSearchDomain(resultList, latitude, longitude));
                    } else {
                        listRestaurantSearchDomainMutableLiveData.setValue(null);
                    }
                }

                @Override
                public void onFailure(Call<ListRestaurantResponse> call, Throwable t) {
                    listRestaurantSearchDomainMutableLiveData.setValue(null);
                }
            });
        }
        return listRestaurantSearchDomainMutableLiveData;
    }

    public LiveData<RestaurantDomain> getRestaurant(String uidRestaurant){

        MutableLiveData<RestaurantDomain> restaurantDetailResponseMutableLiveData = new MutableLiveData<>();

        // Check in our cache if we already queried and stored the response
        RestaurantDetailResponse response = alreadyFetchedResponsesRestaurantDetail.get(uidRestaurant);

        if(response!=null){
            restaurantDetailResponseMutableLiveData.setValue(MapperDataToDomain.restaurantDetailResponseToRestaurantDomain(response));
        }else{
            googlePlaceApi.getDetailsRestaurant(uidRestaurant, GOOGLE_API_KEY).enqueue(new Callback<RestaurantDetailResponse>() {
                @Override
                public void onResponse(@NonNull Call<RestaurantDetailResponse> call, @NonNull Response<RestaurantDetailResponse> response) {
                    if (response.body() != null) {
                        // store it in Map for potential future use
                        alreadyFetchedResponsesRestaurantDetail.put(uidRestaurant, response.body());

                        // Publish the result to the LiveData, we can use 'setValue()' instead of 'postValue()'
                        // because Retrofit goes back to the Main Thread once the query is finished !
                        restaurantDetailResponseMutableLiveData.setValue(MapperDataToDomain.restaurantDetailResponseToRestaurantDomain(response.body()));
                    }
                }
                @Override
                public void onFailure(@NonNull Call<RestaurantDetailResponse> call, @NonNull Throwable t) {
                    restaurantDetailResponseMutableLiveData.setValue(null);
                }
            });
        }

        return restaurantDetailResponseMutableLiveData;
    }
}
