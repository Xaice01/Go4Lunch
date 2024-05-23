package com.xavier_carpentier.go4lunch.domain.usecase;

import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.User;

public class CreateUserInDataBaseUseCase {

    private final UsersRepository userRepository;

    public CreateUserInDataBaseUseCase(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void invoke(User user){
        userRepository.createUserInDataBase(MapperDomainUi.userUiToUserDomain(user));
    }
}
