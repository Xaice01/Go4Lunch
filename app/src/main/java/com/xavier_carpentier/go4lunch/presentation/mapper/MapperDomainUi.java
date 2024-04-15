package com.xavier_carpentier.go4lunch.presentation.mapper;

import com.xavier_carpentier.go4lunch.domain.model.AuthProviderTypeDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.presentation.model.AuthProviderTypeUi;
import com.xavier_carpentier.go4lunch.presentation.model.User;

import java.util.ArrayList;
import java.util.List;

public class MapperDomainUi {
    public static User userDomainToUserUi(UserDomain userDomain){
        return new User(userDomain.getUid(),userDomain.getUsername(),userDomain.getEmail(),userDomain.getUrlPicture());
    }

    public static List<AuthProviderTypeUi> ListAuthProviderTypeDomainToListAuthProviderTypeUi(List<AuthProviderTypeDomain> authProviderTypeDomains){

        List<AuthProviderTypeUi> providers =new ArrayList<>();

        for(AuthProviderTypeDomain provider:authProviderTypeDomains) {
            switch (provider) {
                case TWITTER:
                    providers.add(AuthProviderTypeUi.TWITTER);
                    break;
                case EMAIL:
                    providers.add(AuthProviderTypeUi.EMAIL);
                    break;

                case GOOGLE:
                    providers.add(AuthProviderTypeUi.GOOGLE);
                    break;

                case FACEBOOK:
                    providers.add(AuthProviderTypeUi.FACEBOOK);
                    break;
            }
        }

        return providers;
    }
}
