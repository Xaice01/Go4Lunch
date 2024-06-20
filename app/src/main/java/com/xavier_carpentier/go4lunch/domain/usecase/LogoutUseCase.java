package com.xavier_carpentier.go4lunch.domain.usecase;

import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;

public class LogoutUseCase {

    private final AuthUserRepository userRepository;

    public LogoutUseCase(AuthUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void invoke(){
        userRepository.logout();
    }
}
