package com.xavier_carpentier.go4lunch.domain.usecase;

import android.content.Context;

import com.xavier_carpentier.go4lunch.domain.repository.UserRepository;

import java.util.List;

public class GetBuilderListAuthenticationProvidersUseCase {
    private final UserRepository userRepository;

    public GetBuilderListAuthenticationProvidersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String>getBuilderListAuthenticationProviders(Context context){
        return userRepository.getBuilderListAuthenticationProvider(context);
    }
}
