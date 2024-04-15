package com.xavier_carpentier.go4lunch.domain.usecase;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LogoutUseCaseTest {

    @Mock
    private AuthRepositoryFirebase mockauthRepositoryFirebase;
    private LogoutUseCase logoutUseCase;


    @Before
    public void setUp(){
        logoutUseCase = new LogoutUseCase(mockauthRepositoryFirebase);

    }

    @Test
    public void logoutUseCaseTest(){
        //When
        logoutUseCase.logout();

        //Then
        verify(mockauthRepositoryFirebase,times(1)).logout();

    }
}
