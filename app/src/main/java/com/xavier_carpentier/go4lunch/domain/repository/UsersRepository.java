package com.xavier_carpentier.go4lunch.domain.repository;

import androidx.lifecycle.LiveData;

import com.xavier_carpentier.go4lunch.domain.model.RestaurantChoiceDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

import java.util.List;

public interface UsersRepository {

    void createUserInDataBase(UserDomain user);

    LiveData<List<UserDomain>> getAllUsers();

    LiveData<List<RestaurantChoiceDomain>> getAllRestaurantChoiceToDay();

    void addRestaurantChoiceToDay(String idUser, String nameUser, String idRestaurant, String nameRestaurant);

    LiveData<Boolean> deleteRestaurantChoiceToDay(String idUser);

    LiveData<List<RestaurantChoiceDomain>> getListWorkmateToChoiceARestaurant(String idRestaurant);

}
