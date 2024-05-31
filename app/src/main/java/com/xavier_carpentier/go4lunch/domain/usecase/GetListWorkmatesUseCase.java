package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.xavier_carpentier.go4lunch.domain.model.RestaurantChoiceDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;

import java.util.ArrayList;
import java.util.List;

public class GetListWorkmatesUseCase {
    private final UsersRepository userRepository;

    public GetListWorkmatesUseCase(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<List<Workmate>> invoke() {
        MediatorLiveData<List<Workmate>> result = new MediatorLiveData<>();

        LiveData<List<Workmate>> usersLiveData = Transformations.map(userRepository.getAllUsers(), MapperDomainUi::listUserDomainToListWorkmate);
        LiveData<List<Workmate>> restaurantChoicesLiveData = Transformations.map(userRepository.getAllRestaurantChoiceToDay(), MapperDomainUi::listRestaurantChoiceDomainToListWorkmate);

        result.addSource(usersLiveData, users -> {
            combine(users, restaurantChoicesLiveData.getValue(), result);
        });

        result.addSource(restaurantChoicesLiveData, restaurantChoices -> {
            combine(usersLiveData.getValue(), restaurantChoices, result);
        });

        return result;
    }

    private void combine(List<Workmate> users, List<Workmate> restaurantChoices, MediatorLiveData<List<Workmate>> result) {
        if (users == null || restaurantChoices == null) {
            return;
        }

        List<Workmate> combinedList = new ArrayList<>(users);

        for (Workmate user : combinedList) {
            for (Workmate choice : restaurantChoices) {
                if (choice.getUid().equals(user.getUid())) {
                    user.setUidRestaurant(choice.getUidRestaurant());
                    user.setRestaurantName(choice.getRestaurantName());
                    break;
                }
            }
        }

        result.setValue(combinedList);
    }
}
