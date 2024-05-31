package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;

import java.util.List;

public class GetListWorkmateToEatByIdRestaurantUseCase {
    private final UsersRepository userRepository;

    public GetListWorkmateToEatByIdRestaurantUseCase(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<List<Workmate>> invoke(String idRestaurant){

        return Transformations.map(userRepository.getListWorkmateToChoiceARestaurant(idRestaurant), MapperDomainUi::listRestaurantChoiceDomainToListWorkmate);
    }
}
