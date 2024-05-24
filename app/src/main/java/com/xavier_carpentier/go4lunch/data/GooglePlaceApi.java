package com.xavier_carpentier.go4lunch.data;

import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.AutocompleteResponse;
import com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response.RestaurantDetailResponse;
import com.xavier_carpentier.go4lunch.data.response.list_restaurant_response.ListRestaurantResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlaceApi {
    @GET("details/json")
    Call<RestaurantDetailResponse> getDetailsRestaurant(@Query("place_id") String placeId, @Query("key") String key);


    @GET("autocomplete/json")
    Call<AutocompleteResponse> getAutocomplete(@Query("input") String input, @Query ("location") String location, @Query("radius") String radius, @Query("types") String types, @Query("key") String key);

    @GET("nearbysearch/json")
    Call<ListRestaurantResponse> getNearby(@Query("location") String location, @Query("types") String types, @Query("radius") String radius, @Query("key") String apiKey);

    @GET("nearbysearch/json")
    Call<ListRestaurantResponse> getNearby(@Query("pagetoken") String pagetoken, @Query("key") String apiKey);
}