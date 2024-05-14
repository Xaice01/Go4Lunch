package com.xavier_carpentier.go4lunch.data.repository;

import static com.xavier_carpentier.go4lunch.BuildConfig.GOOGLE_API_KEY;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.xavier_carpentier.go4lunch.data.GooglePlaceApi;
import com.xavier_carpentier.go4lunch.data.entity.detail_restaurant_response.RestaurantDetailResponse;
import com.xavier_carpentier.go4lunch.data.mappers.MapperDataToDomain;
import com.xavier_carpentier.go4lunch.domain.model.AutocompletePredictionDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantDomain;
import com.xavier_carpentier.go4lunch.domain.repository.PlaceRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class PlaceRepositoryRetrofit implements PlaceRepository {

    private final GooglePlaceApi googlePlaceApi;

    //TODO check with the mentor
    private final String apiKey = GOOGLE_API_KEY;

    public PlaceRepositoryRetrofit(GooglePlaceApi googlePlaceApi) {
        this.googlePlaceApi = googlePlaceApi;
    }
    // In this Map we will store the responses we get from the server (corresponding to the Uid restaurant),
    // so if needed we can "get back in time", this will act like a cache !
    private final Map<String, RestaurantDetailResponse> alreadyFetchedResponsesRestaurantDetail = new HashMap<>();


    public void getCurrentLocation(){


    }

    //public LiveData<List<AutocompletePredictionDomain>> getAutocomplete(String input, String location, String radius, String types){
//
    //}

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
            googlePlaceApi.getDetailsRestaurant(uidRestaurant, apiKey).enqueue(new Callback<RestaurantDetailResponse>() {
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
