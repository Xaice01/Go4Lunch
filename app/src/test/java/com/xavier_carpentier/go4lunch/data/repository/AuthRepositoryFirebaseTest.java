package com.xavier_carpentier.go4lunch.data.repository;

import static org.junit.Assert.assertEquals;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.xavier_carpentier.go4lunch.data.mappers.MapperDataToDomain;
import com.xavier_carpentier.go4lunch.datasource.utils.AuthProviderType;
import com.xavier_carpentier.go4lunch.datasource.utils.AuthenticationProvider;
import com.xavier_carpentier.go4lunch.domain.model.AuthProviderTypeDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;


public class AuthRepositoryFirebaseTest {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private AuthRepositoryFirebase authRepository;

    @Before
    public void setUp() {
        firebaseAuth = Mockito.mock(FirebaseAuth.class);
        firebaseUser = Mockito.mock(FirebaseUser.class);

        // Mocking the static methods of FirebaseAuth
        try (MockedStatic<FirebaseAuth> mockedFirebaseAuth = Mockito.mockStatic(FirebaseAuth.class)) {
            mockedFirebaseAuth.when(FirebaseAuth::getInstance).thenReturn(firebaseAuth);
            Mockito.when(firebaseAuth.getCurrentUser()).thenReturn(firebaseUser);

            authRepository = AuthRepositoryFirebase.getInstance();
        }
    }

    @Test
    public void testLogout() {
        try (MockedStatic<FirebaseAuth> mockedFirebaseAuth = Mockito.mockStatic(FirebaseAuth.class)) {
            mockedFirebaseAuth.when(FirebaseAuth::getInstance).thenReturn(firebaseAuth);

            authRepository.logout();
            Mockito.verify(firebaseAuth).signOut();
        }
    }

    @Test
    public void testGetBuilderListAuthenticationProvider() {
        List<AuthProviderType> authProviderTypes = Arrays.asList(AuthProviderType.GOOGLE, AuthProviderType.FACEBOOK);

        try (MockedStatic<AuthenticationProvider> mockedAuthProvider = Mockito.mockStatic(AuthenticationProvider.class)) {
            mockedAuthProvider.when(AuthenticationProvider::getAuthProviderType).thenReturn(authProviderTypes);

            List<AuthProviderTypeDomain> authProviderTypeDomains = authRepository.getBuilderListAuthenticationProvider();
            assertEquals(authProviderTypes.size(), authProviderTypeDomains.size());
        }
    }

    @Test
    public void testGetUser() {
        UserDomain userDomain = new UserDomain("uid", "name", "photoUrl");

        try (MockedStatic<MapperDataToDomain> mockedMapper = Mockito.mockStatic(MapperDataToDomain.class);
             MockedStatic<FirebaseAuth> mockedFirebaseAuth = Mockito.mockStatic(FirebaseAuth.class)) {

            mockedFirebaseAuth.when(FirebaseAuth::getInstance).thenReturn(firebaseAuth);
            mockedMapper.when(() -> MapperDataToDomain.firebaseUserToUserDomain(firebaseUser)).thenReturn(userDomain);

            UserDomain result = authRepository.getUser();
            assertEquals(userDomain, result);
        }
    }

    @Test
    public void testGetEmail() {
        String email = "test@example.com";
        Mockito.when(firebaseUser.getEmail()).thenReturn(email);

        try (MockedStatic<FirebaseAuth> mockedFirebaseAuth = Mockito.mockStatic(FirebaseAuth.class)) {
            mockedFirebaseAuth.when(FirebaseAuth::getInstance).thenReturn(firebaseAuth);

            String result = authRepository.getEmail();
            assertEquals(email, result);
        }
    }
}