package com.xavier_carpentier.go4lunch.domain.usecase;

import com.firebase.ui.auth.AuthUI;
import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;

import java.util.Arrays;
import java.util.List;

public class GetBuilderListAuthenticationProvidersUseCase {
    private final AuthRepositoryFirebase authRepositoryFirebase;

    public GetBuilderListAuthenticationProvidersUseCase(AuthRepositoryFirebase authRepositoryFirebase) {
        this.authRepositoryFirebase = authRepositoryFirebase;
    }

    //TODO enum (AuthUI a metre dans le viewmodel)

    public List<AuthUI.IdpConfig> getBuilderListAuthenticationProviders(){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());
        return providers;
    }
}
