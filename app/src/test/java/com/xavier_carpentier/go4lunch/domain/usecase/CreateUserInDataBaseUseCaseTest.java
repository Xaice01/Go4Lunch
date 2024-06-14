package com.xavier_carpentier.go4lunch.domain.usecase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.User;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

public class CreateUserInDataBaseUseCaseTest {

    private CreateUserInDataBaseUseCase createUserInDataBaseUseCase;

    @Mock
    private UsersRepository usersRepository;

    @Captor
    private ArgumentCaptor<UserDomain> userDomainCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        createUserInDataBaseUseCase = new CreateUserInDataBaseUseCase(usersRepository);
    }

    @Test
    public void testInvoke() {
        // Given
        User user = new User("id1", "John Doe", "url1");
        UserDomain expectedUserDomain = MapperDomainUi.userUiToUserDomain(user);

        // When
        createUserInDataBaseUseCase.invoke(user);

        // Then
        verify(usersRepository, times(1)).createUserInDataBase(userDomainCaptor.capture());
        UserDomain actualUserDomain = userDomainCaptor.getValue();

        assertEquals(expectedUserDomain.getUid(), actualUserDomain.getUid());
        assertEquals(expectedUserDomain.getUsername(), actualUserDomain.getUsername());
        assertEquals(expectedUserDomain.getUrlPicture(), actualUserDomain.getUrlPicture());
    }
}
