package com.xavier_carpentier.go4lunch.domain.usecase;

import com.xavier_carpentier.go4lunch.domain.repository.UserRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.AuthProviderTypeUi;

import java.util.List;

public class GetBuilderListAuthenticationProvidersUseCase {
    private final UserRepository userRepository;

    public GetBuilderListAuthenticationProvidersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AuthProviderTypeUi>getBuilderListAuthenticationProviders(){
        return MapperDomainUi.ListAuthProviderTypeDomainToListAuthProviderTypeUi(userRepository.getBuilderListAuthenticationProvider());
    }
}
