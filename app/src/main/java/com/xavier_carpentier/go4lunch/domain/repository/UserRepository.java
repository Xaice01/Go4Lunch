package com.xavier_carpentier.go4lunch.domain.repository;

import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

public interface UserRepository {
    UserDomain getUser();
    void logout();
}
