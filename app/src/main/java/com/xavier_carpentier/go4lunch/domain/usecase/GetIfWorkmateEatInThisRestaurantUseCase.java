package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.xavier_carpentier.go4lunch.domain.model.RestaurantChoiceDomain;
import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;

import java.util.List;

public class GetIfWorkmateEatInThisRestaurantUseCase {
    private final AuthUserRepository authUserRepository;
    private final UsersRepository userRepository;
    public GetIfWorkmateEatInThisRestaurantUseCase(UsersRepository userRepository,AuthUserRepository authUserRepository) {
        this.userRepository = userRepository;
        this.authUserRepository = authUserRepository;
    }

    public LiveData<Boolean> invoke(String idRestaurant){
        LiveData<List<RestaurantChoiceDomain>> choicesLiveData = userRepository.getListWorkmateToChoiceARestaurant(idRestaurant);
        String userId = authUserRepository.getUser().getUid();

        return Transformations.map(choicesLiveData, choices -> {
            for (RestaurantChoiceDomain choice : choices) {
                if (choice.getIdUser().equals(userId)) {
                    return true;
                }
            }
            return false;
        });
    }
}
