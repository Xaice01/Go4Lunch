package com.xavier_carpentier.go4lunch.presentation.viewmodel;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import android.content.Intent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.firebase.ui.auth.AuthUI;
import com.xavier_carpentier.go4lunch.domain.usecase.CreateUserInDataBaseUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetBuilderListAuthenticationProvidersUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetCurrentUserUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetEmailUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.LogoutUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.AuthProviderTypeUi;
import com.xavier_carpentier.go4lunch.presentation.model.User;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private LogoutUseCase mockLogoutUseCase;
    @Mock
    private GetBuilderListAuthenticationProvidersUseCase mockGetBuilderListAuthenticationProvidersUseCase;
    @Mock
    private GetCurrentUserUseCase mockGetCurrentUserUseCase;
    @Mock
    private GetEmailUseCase mockGetEmailUseCase;
    @Mock
    private CreateUserInDataBaseUseCase mockCreateUserInDataBaseUseCase;
    @Mock
    private ActivityResultLauncher<Intent> mockSignInLauncher;

    private AuthViewModel authViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        authViewModel = new AuthViewModel(mockLogoutUseCase, mockGetBuilderListAuthenticationProvidersUseCase,
                mockGetCurrentUserUseCase, mockGetEmailUseCase, mockCreateUserInDataBaseUseCase);
    }

    @Test
    public void testIsLogging() {
        Observer<Boolean> observer = mock(Observer.class);
        authViewModel.isLogging().observeForever(observer);

        authViewModel.isLogging.setValue(true);

        verify(observer).onChanged(true);
    }

    @Test
    public void testGetEmail() {
        Observer<String> observer = mock(Observer.class);
        authViewModel.getEmail().observeForever(observer);

        String email = "test@example.com";
        authViewModel.liveDataEmail.setValue(email);

        verify(observer).onChanged(email);
    }

    @Test
    public void testGetCurrentUser() {
        Observer<User> observer = mock(Observer.class);
        authViewModel.getCurrentUser().observeForever(observer);

        User user = new User("uid", "username", "photoUrl");
        authViewModel.liveDataUser.setValue(user);

        verify(observer).onChanged(user);
    }

    @Test
    public void testLogout() {
        authViewModel.Logout();

        verify(mockLogoutUseCase).invoke();
    }

    @Test
    public void testGetBuilderListAuthenticationProviders_EmptyList() {
        // Arrange
        List<AuthProviderTypeUi> authProviderTypeUiList = new ArrayList<>();
        when(mockGetBuilderListAuthenticationProvidersUseCase.invoke()).thenReturn(authProviderTypeUiList);

        // Act
        List<AuthUI.IdpConfig> providers = authViewModel.getBuilderListAuthenticationProviders();

        // Assert
        assertEquals(0, providers.size());

        // Verify the method is invoked
        verify(mockGetBuilderListAuthenticationProvidersUseCase).invoke();
    }

}
