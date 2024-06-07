package com.xavier_carpentier.go4lunch.domain.usecase;

import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.User;

public class GetEmailUseCase {
    private final AuthUserRepository userRepository;

    public GetEmailUseCase(AuthUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String invoke(){
        return userRepository.getEmail();
    }
}
