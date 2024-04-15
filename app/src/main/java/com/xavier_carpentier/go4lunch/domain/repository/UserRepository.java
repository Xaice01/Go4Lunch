package com.xavier_carpentier.go4lunch.domain.repository;

import com.xavier_carpentier.go4lunch.domain.model.AuthProviderTypeDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

import java.util.List;

public interface UserRepository {
    List<AuthProviderTypeDomain> getBuilderListAuthenticationProvider();
    UserDomain getUser();
    void logout();
}
