package com.xavier_carpentier.go4lunch.domain.usecase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;

public class GetEmailUseCaseTest {

    private GetEmailUseCase getEmailUseCase;

    @Mock
    private AuthUserRepository authUserRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getEmailUseCase = new GetEmailUseCase(authUserRepository);
    }

    @Test
    public void testInvoke() {
        // Given
        String expectedEmail = "test@example.com";
        when(authUserRepository.getEmail()).thenReturn(expectedEmail);

        // When
        String actualEmail = getEmailUseCase.invoke();

        // Then
        verify(authUserRepository).getEmail();
        assertEquals(expectedEmail, actualEmail);
    }
}