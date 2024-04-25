package com.xavier_carpentier.go4lunch.domain.usecase;

import com.xavier_carpentier.go4lunch.domain.repository.UserRepository;

public class LogoutUseCase {

    private final UserRepository userRepository;

    public LogoutUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void invoke(){
        userRepository.logout();
    }
}
