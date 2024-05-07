package com.xavier_carpentier.go4lunch.data.mappers;

import com.google.firebase.auth.FirebaseUser;
import com.xavier_carpentier.go4lunch.datasource.utils.AuthProviderType;
import com.xavier_carpentier.go4lunch.domain.model.AuthProviderTypeDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapperDataToDomain {

    public static UserDomain firebaseUserToUserDomain(FirebaseUser firebaseUser){
        if(firebaseUser.getPhotoUrl()==null)
        {
            return new UserDomain(firebaseUser.getUid(),firebaseUser.getDisplayName(),null);
        }
        return new UserDomain(firebaseUser.getUid(),firebaseUser.getDisplayName(), firebaseUser.getPhotoUrl().toString());
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
