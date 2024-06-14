package com.xavier_carpentier.go4lunch.presentation.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AuthProviderTypeUiTest {

    @Test
    public void testAuthProviderTypeUiValues() {
        AuthProviderTypeUi[] values = AuthProviderTypeUi.values();
        assertNotNull(values);
        assertEquals(4, values.length);
        assertEquals(AuthProviderTypeUi.TWITTER, values[0]);
        assertEquals(AuthProviderTypeUi.FACEBOOK, values[1]);
        assertEquals(AuthProviderTypeUi.EMAIL, values[2]);
        assertEquals(AuthProviderTypeUi.GOOGLE, values[3]);
    }

    @Test
    public void testValueOf() {
        assertEquals(AuthProviderTypeUi.TWITTER, AuthProviderTypeUi.valueOf("TWITTER"));
        assertEquals(AuthProviderTypeUi.FACEBOOK, AuthProviderTypeUi.valueOf("FACEBOOK"));
        assertEquals(AuthProviderTypeUi.EMAIL, AuthProviderTypeUi.valueOf("EMAIL"));
        assertEquals(AuthProviderTypeUi.GOOGLE, AuthProviderTypeUi.valueOf("GOOGLE"));
    }
}