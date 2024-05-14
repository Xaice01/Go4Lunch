package com.xavier_carpentier.go4lunch.data;

import com.xavier_carpentier.go4lunch.data.entity.autocomplete_response.AutocompleteResponse;
import com.xavier_carpentier.go4lunch.data.entity.detail_restaurant_response.RestaurantDetailResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlaceApi {
    @GET("details/json")
    Call<RestaurantDetailResponse> getDetailsRestaurant(@Query("place_id") String placeId, @Query("key") String key);


    @GET("autocomplete/json")
    Call<AutocompleteResponse> getAutocomplete(@Query("input") String input, @Query ("location") String location, @Query("radius") String radius, @Query("types") String types, @Query("key") String key);

    //TODO faire le get list restaurant
    //@GET


}
