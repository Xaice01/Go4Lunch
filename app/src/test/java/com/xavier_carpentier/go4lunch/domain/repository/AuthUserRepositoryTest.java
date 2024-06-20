package com.xavier_carpentier.go4lunch.domain.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.junit.runner.RunWith;

import com.xavier_carpentier.go4lunch.domain.model.AuthProviderTypeDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

import java.util.Arrays;
import java.util.List;


public class AuthUserRepositoryTest {

    private AuthUserRepository authUserRepository;

    @Mock
    private AuthUserRepository mockAuthUserRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authUserRepository = mockAuthUserRepository;
    }

    @Test
    public void testGetBuilderListAuthenticationProvider() {
        // Given
        List<AuthProviderTypeDomain> expectedProviders = Arrays.asList(
                AuthProviderTypeDomain.EMAIL,
                AuthProviderTypeDomain.GOOGLE
        );

        when(authUserRepository.getBuilderListAuthenticationProvider()).thenReturn(expectedProviders);

        // When
        List<AuthProviderTypeDomain> actualProviders = authUserRepository.getBuilderListAuthenticationProvider();

        // Then
        verify(mockAuthUserRepository).getBuilderListAuthenticationProvider();
        assertEquals(expectedProviders, actualProviders);
    }

    @Test
    public void testGetUser() {
        // Given
        UserDomain expectedUser = new UserDomain("id1", "John Doe", "url1");

        when(authUserRepository.getUser()).thenReturn(expectedUser);

        // When
        UserDomain actualUser = authUserRepository.getUser();

        // Then
        verify(mockAuthUserRepository).getUser();
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testLogout() {
        // When
        authUserRepository.logout();

        // Then
        verify(mockAuthUserRepository).logout();
    }

    @Test
    public void testGetEmail() {
        // Given
        String expectedEmail = "john.doe@example.com";

        when(authUserRepository.getEmail()).thenReturn(expectedEmail);

        // When
        String actualEmail = authUserRepository.getEmail();

        // Then
        verify(mockAuthUserRepository).getEmail();
        assertEquals(expectedEmail, actualEmail);
    }
}