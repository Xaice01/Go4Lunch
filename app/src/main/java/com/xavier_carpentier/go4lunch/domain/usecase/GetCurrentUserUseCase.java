package com.xavier_carpentier.go4lunch.domain.usecase;

import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.presentation.model.User;

public class GetCurrentUserUseCase{
    private final AuthUserRepository userRepository;

    public GetCurrentUserUseCase(AuthUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User invoke(){
        return MapperDomainUi.userDomainToUserUi(userRepository.getUser());
    }
}
