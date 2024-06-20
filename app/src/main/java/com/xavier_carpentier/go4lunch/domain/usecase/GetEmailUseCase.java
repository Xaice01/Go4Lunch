package com.xavier_carpentier.go4lunch.domain.usecase;

import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;

public class GetEmailUseCase {
    private final AuthUserRepository userRepository;

    public GetEmailUseCase(AuthUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String invoke(){
        return userRepository.getEmail();
    }
}
