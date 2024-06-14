package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import static org.mockito.Mockito.verify;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.xavier_carpentier.go4lunch.domain.usecase.CreateUserInDataBaseUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetBuilderListAuthenticationProvidersUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetCurrentUserUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetEmailUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.LogoutUseCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AuthViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private LogoutUseCase logoutUseCase;
    @Mock
    private GetBuilderListAuthenticationProvidersUseCase getBuilderListAuthenticationProvidersUseCase;
    @Mock
    private GetCurrentUserUseCase getCurrentUserUseCase;
    @Mock
    private GetEmailUseCase getEmailUseCase;
    @Mock
    private CreateUserInDataBaseUseCase createUserInDataBaseUseCase;

    private AuthViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        viewModel = new AuthViewModel(logoutUseCase, getBuilderListAuthenticationProvidersUseCase,
                getCurrentUserUseCase, getEmailUseCase, createUserInDataBaseUseCase);
    }

    @Test
    public void testLogout() {
        viewModel.Logout();
        verify(logoutUseCase).invoke();
    }

}
