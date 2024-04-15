package com.xavier_carpentier.go4lunch.domain.usecase;

import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.domain.repository.UserRepository;
import com.xavier_carpentier.go4lunch.presentation.model.User;

public class GetCurrentUserUseCase{
    private final UserRepository userRepository;

    public GetCurrentUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser(){
        return MapperDomainUi.userDomainToUserUi(userRepository.getUser());
    }
}
