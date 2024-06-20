package com.xavier_carpentier.go4lunch.domain.usecase;

import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.AuthProviderTypeUi;

import java.util.List;

public class GetBuilderListAuthenticationProvidersUseCase {
    private final AuthUserRepository userRepository;

    public GetBuilderListAuthenticationProvidersUseCase(AuthUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AuthProviderTypeUi> invoke(){
        return MapperDomainUi.listAuthProviderTypeDomainToListAuthProviderTypeUi(userRepository.getBuilderListAuthenticationProvider());
    }
}
