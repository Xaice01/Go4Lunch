package com.xavier_carpentier.go4lunch.domain.usecase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.net.Uri;

import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.presentation.model.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


public class GetCurrentUserUseCaseTest {
    @Mock
    private AuthRepositoryFirebase mockauthRepositoryFirebase;
    @Mock
    private Uri uriTest;
    private UserDomain userDomainReturnByRepository;
    private GetCurrentUserUseCase getCurrentUserUseCase;

    @Before
    public void setUp(){
        userDomainReturnByRepository = new UserDomain("123456789","nameTest","emailTest",uriTest);
        mockauthRepositoryFirebase = mock(AuthRepositoryFirebase.class);
        when(mockauthRepositoryFirebase.getUser()).thenReturn(userDomainReturnByRepository);
        getCurrentUserUseCase = new GetCurrentUserUseCase(mockauthRepositoryFirebase);

    }

    @Test
    public void getCurrentUserUseCaseTest(){
        //When
        User userToTest = getCurrentUserUseCase.invoke();

        //Then
        verify(mockauthRepositoryFirebase,times(1)).getUser();
        assertEquals(userToTest.getUid(), userDomainReturnByRepository.getUid());
        assertEquals(userToTest.getUsername(), userDomainReturnByRepository.getUsername());
        assertEquals(userToTest.getEmail(), userDomainReturnByRepository.getEmail());
        assertEquals(userToTest.getUrlPicture(), userDomainReturnByRepository.getUrlPicture());

    }



}
