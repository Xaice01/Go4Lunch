package com.xavier_carpentier.go4lunch.data.mappers;

import com.google.firebase.auth.FirebaseUser;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

public class MapperDataToDomain {

    public static UserDomain firebaseUserToUserDomain(FirebaseUser firebaseUser){
        return new UserDomain(firebaseUser.getUid(),firebaseUser.getDisplayName(),firebaseUser.getEmail(),firebaseUser.getPhotoUrl());
    }

}
