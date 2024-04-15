package com.xavier_carpentier.go4lunch.data.mappers;

import com.google.firebase.auth.FirebaseUser;
import com.xavier_carpentier.go4lunch.datasource.utils.AuthProviderType;
import com.xavier_carpentier.go4lunch.domain.model.AuthProviderTypeDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

import java.util.ArrayList;
import java.util.List;

public class MapperDataToDomain {

    public static UserDomain firebaseUserToUserDomain(FirebaseUser firebaseUser){
        return new UserDomain(firebaseUser.getUid(),firebaseUser.getDisplayName(),firebaseUser.getEmail(),firebaseUser.getPhotoUrl());
    }

    public static List<AuthProviderTypeDomain> ListAuthProviderTypeToListAuthProviderTypeDomain(List<AuthProviderType> listAuthProviderType){

        List<AuthProviderTypeDomain> providers =new ArrayList<>();

        for(AuthProviderType provider:listAuthProviderType) {
            switch (provider) {
                case TWITTER:
                    providers.add(AuthProviderTypeDomain.TWITTER);
                    break;
                case EMAIL:
                    providers.add(AuthProviderTypeDomain.EMAIL);
                    break;

                case GOOGLE:
                    providers.add(AuthProviderTypeDomain.GOOGLE);
                    break;

                case FACEBOOK:
                    providers.add(AuthProviderTypeDomain.FACEBOOK);
                    break;
            }
        }

        return providers;
    }

}
