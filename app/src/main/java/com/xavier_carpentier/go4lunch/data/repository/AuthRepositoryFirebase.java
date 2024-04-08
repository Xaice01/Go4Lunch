package com.xavier_carpentier.go4lunch.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthRepositoryFirebase {
    //TODO implement interface
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

    public FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }


}
