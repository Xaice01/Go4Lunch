package com.xavier_carpentier.go4lunch.domain.usecase;

import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;

public class LogoutUseCase {

    private final AuthRepositoryFirebase authRepositoryFirebase;

    public LogoutUseCase(AuthRepositoryFirebase authRepositoryFirebase) {
        this.authRepositoryFirebase = authRepositoryFirebase;
    }

    public void logout(){
        authRepositoryFirebase.logout();
    }
}