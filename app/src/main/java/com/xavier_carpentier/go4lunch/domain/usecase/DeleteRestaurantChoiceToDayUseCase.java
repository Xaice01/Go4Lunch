package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.lifecycle.LiveData;

import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;

public class DeleteRestaurantChoiceToDayUseCase {
    private final UsersRepository usersRepository;
    private final AuthUserRepository authUserRepository;

    public DeleteRestaurantChoiceToDayUseCase(UsersRepository usersRepository, AuthUserRepository authUserRepository) {
        this.usersRepository = usersRepository;
        this.authUserRepository = authUserRepository;
    }

    public LiveData<Boolean> invoke() {
        String idUser =authUserRepository.getUser().getUid();
        return usersRepository.deleteRestaurantChoiceToDay(idUser);
    }
}