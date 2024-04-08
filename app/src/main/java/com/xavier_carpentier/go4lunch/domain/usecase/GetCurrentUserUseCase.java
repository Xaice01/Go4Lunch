package com.xavier_carpentier.go4lunch.domain.usecase;


import com.google.firebase.auth.FirebaseUser;
import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;

public class GetCurrentUserUseCase {

    private final AuthRepositoryFirebase authRepositoryFirebase;

    public GetCurrentUserUseCase(AuthRepositoryFirebase authRepositoryFirebase) {
        this.authRepositoryFirebase = authRepositoryFirebase;
    }

    public FirebaseUser getCurrentUser(){
        return authRepositoryFirebase.getCurrentUser();
    }
}
