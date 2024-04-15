package com.xavier_carpentier.go4lunch.datasource.utils;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationProvider {
    public static List<AuthProviderType> getAuthProviderType(){
        ArrayList<AuthProviderType> authProviderAvailableList = new ArrayList<>();
        authProviderAvailableList.add(AuthProviderType.EMAIL);
        authProviderAvailableList.add(AuthProviderType.GOOGLE);
        authProviderAvailableList.add(AuthProviderType.FACEBOOK);
        authProviderAvailableList.add(AuthProviderType.TWITTER);

        return authProviderAvailableList;
    }

}
