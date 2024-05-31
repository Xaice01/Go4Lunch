package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.lifecycle.LiveData;

import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.domain.repository.FavorisRestaurantRepository;

import java.util.List;

public class LikeRestaurantUseCase {
    private final FavorisRestaurantRepository favorisRestaurantRepository;
    private final AuthUserRepository userRepository;

    public LikeRestaurantUseCase(FavorisRestaurantRepository favorisRestaurantRepository, AuthUserRepository userRepository) {
        this.favorisRestaurantRepository = favorisRestaurantRepository;
        this.userRepository = userRepository;
    }

    public void add(String idRestaurant){
        String idUser=userRepository.getUser().getUid();
        favorisRestaurantRepository.addRestaurantFavoris(idUser, idRestaurant);
    }
    public void remove(String idRestaurant){
        String idUser=userRepository.getUser().getUid();
        favorisRestaurantRepository.deleteRestaurantFavoris(idUser, idRestaurant);
    }

    public LiveData<List<String>> getListRestaurant(){
        String idUser=userRepository.getUser().getUid();
        return favorisRestaurantRepository.getRestaurantFavorisByIdUser(idUser);
    }
}
