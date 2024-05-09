package com.xavier_carpentier.go4lunch.data;

import com.xavier_carpentier.go4lunch.data.entity.detail_restaurant_response.RestaurantDetailResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlaceApi {
    @GET("details/json")
    Call<RestaurantDetailResponse> getDetailsRestaurant(@Query("restaurant_id") String restaurantId, @Query("key") String key);

    //TODO faire le get autocomplete
    //@GET

    //TODO faire le get list restaurant
    //@GET


}
