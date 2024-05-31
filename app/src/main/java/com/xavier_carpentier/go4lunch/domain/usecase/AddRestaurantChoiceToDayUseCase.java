package com.xavier_carpentier.go4lunch.domain.usecase;

import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;

public class AddRestaurantChoiceToDayUseCase {
    private final UsersRepository usersRepository;
    private final AuthUserRepository authUserRepository;

    public AddRestaurantChoiceToDayUseCase(UsersRepository usersRepository, AuthUserRepository authUserRepository) {
        this.usersRepository = usersRepository;
        this.authUserRepository = authUserRepository;
    }

    public void invoke(String idRestaurant, String nameRestaurant) {
        String idUser =authUserRepository.getUser().getUid();
        String nameUser =authUserRepository.getUser().getUsername();
        String urlUserPicture =authUserRepository.getUser().getUrlPicture();
        usersRepository.addRestaurantChoiceToDay(idUser, nameUser, urlUserPicture, idRestaurant, nameRestaurant);
    }
}