package com.xavier_carpentier.go4lunch.datasource;

import static org.junit.Assert.assertNotNull;

import com.xavier_carpentier.go4lunch.datasource.utils.AuthProviderType;
import com.xavier_carpentier.go4lunch.datasource.utils.AuthenticationProvider;

import org.junit.Test;

import java.util.List;

public class AuthenticationProviderTest {

    @Test
    public void getListAuthenticationProviderTest(){
        //When
        List<AuthProviderType> listProviders= AuthenticationProvider.getAuthProviderType();

        //Then
        assertNotNull(listProviders);
    }

}
