package com.xavier_carpentier.go4lunch.domain.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface FavorisRestaurantRepository {
    LiveData<List<String>> getRestaurantFavorisByIdUser(String idUser);

    LiveData<Boolean> addRestaurantFavoris(String idUser, String idRestaurant);

    LiveData<Boolean> deleteRestaurantFavoris(String IdUser, String idRestaurant);

}
