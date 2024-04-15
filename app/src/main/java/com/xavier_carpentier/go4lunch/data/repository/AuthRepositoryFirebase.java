package com.xavier_carpentier.go4lunch.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.xavier_carpentier.go4lunch.data.mappers.MapperDataToDomain;
import com.xavier_carpentier.go4lunch.datasource.utils.AuthenticationProvider;
import com.xavier_carpentier.go4lunch.domain.model.AuthProviderTypeDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.domain.repository.UserRepository;

import java.util.List;
import java.util.Objects;

public class AuthRepositoryFirebase implements UserRepository {

    private static volatile AuthRepositoryFirebase instance;

    //AuthRepositoryFirebase is a Singleton
    public static AuthRepositoryFirebase getInstance() {
        AuthRepositoryFirebase result = instance;
        if (result != null) {
            return result;
        }
        synchronized(AuthRepositoryFirebase.class) {
            if (instance == null) {
                instance = new AuthRepositoryFirebase();
            }
            return instance;
        }
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public List<AuthProviderTypeDomain> getBuilderListAuthenticationProvider() {
        return MapperDataToDomain.ListAuthProviderTypeToListAuthProviderTypeDomain(AuthenticationProvider.getAuthProviderType());
    }

    @Override
    public UserDomain getUser() {
        return MapperDataToDomain.firebaseUserToUserDomain(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()));
    }


}
