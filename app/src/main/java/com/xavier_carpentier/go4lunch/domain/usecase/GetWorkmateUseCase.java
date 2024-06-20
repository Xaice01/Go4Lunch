package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;

public class GetWorkmateUseCase {
    private final UsersRepository userRepository;

    public GetWorkmateUseCase(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }
    public LiveData<Workmate> invoke(String idUser){
        return Transformations.map(userRepository.getRestaurantChoiceToDay(idUser), MapperDomainUi::RestaurantChoiceDomainToWorkmate);
    }
}