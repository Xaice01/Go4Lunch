package com.xavier_carpentier.go4lunch.domain.usecase;

import static org.junit.Assert.assertNotNull;

import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;
import com.xavier_carpentier.go4lunch.presentation.model.AuthProviderTypeUi;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GetBuilderListAuthenticationProvidersUseCaseTest {

    private final AuthRepositoryFirebase authRepositoryFirebase = AuthRepositoryFirebase.getInstance();

    private GetBuilderListAuthenticationProvidersUseCase getBuilderListAuthenticationProvidersUseCase;

    @Before
    public void setUp(){
        getBuilderListAuthenticationProvidersUseCase = new GetBuilderListAuthenticationProvidersUseCase(authRepositoryFirebase);

    }

    @Test
    public void getBuilderListAuthenticationProvidersUseCaseTest(){
        //When
        List<AuthProviderTypeUi> listProviders=getBuilderListAuthenticationProvidersUseCase.getBuilderListAuthenticationProviders();

        //Then
        assertNotNull(listProviders);
    }

}
