package com.xavier_carpentier.go4lunch.domain.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface FavorisRestaurantRepository {
    LiveData<List<String>> getRestaurantFavorisByIdUser(String idUser);

    void addRestaurantFavoris(String idUser, String idRestaurant);

    void deleteRestaurantFavoris(String IdUser, String idRestaurant);

}
