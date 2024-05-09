package com.xavier_carpentier.go4lunch.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.xavier_carpentier.go4lunch.R;
import com.xavier_carpentier.go4lunch.data.GooglePlaceApi;
import com.xavier_carpentier.go4lunch.data.entity.detail_restaurant_response.RestaurantDetailResponse;
import com.xavier_carpentier.go4lunch.data.mappers.MapperDataToDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantDomain;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceRepository {

    private final GooglePlaceApi googlePlaceApi;

    public PlaceRepository(GooglePlaceApi googlePlaceApi) {
        this.googlePlaceApi = googlePlaceApi;
    }
    // In this Map we will store the responses we get from the server (corresponding to the Uid restaurant),
    // so if needed we can "get back in time", this will act like a cache !
    private final Map<String, RestaurantDetailResponse> alreadyFetchedResponsesRestaurantDetail = new HashMap<>();


    public void getCurrentLocation(){


    }

    public void getAutocomplete(){

    }

    //TODO
    //public void getListRestaurant(positionExact){
//
    //}

    public LiveData<RestaurantDomain> getRestaurant(String uidRestaurant){

        MutableLiveData<RestaurantDomain> restaurantDetailResponseMutableLiveData = new MutableLiveData<>();

        // Check in our cache if we already queried and stored the response
        RestaurantDetailResponse response = alreadyFetchedResponsesRestaurantDetail.get(uidRestaurant);

        if(response!=null){
            restaurantDetailResponseMutableLiveData.setValue(MapperDataToDomain.restaurantDetailResponseToRestaurantDomain(response));
        }else{
            googlePlaceApi.getDetailsRestaurant(uidRestaurant, String.valueOf(R.string.api_Key)).enqueue(new Callback<RestaurantDetailResponse>() {
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
