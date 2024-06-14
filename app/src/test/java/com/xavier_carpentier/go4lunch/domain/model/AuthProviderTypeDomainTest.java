package com.xavier_carpentier.go4lunch.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class AuthProviderTypeDomainTest {

    @Test
    public void testAuthProviderTypeDomain() {
        AuthProviderTypeDomain type = AuthProviderTypeDomain.GOOGLE;

        assertEquals(AuthProviderTypeDomain.GOOGLE, type);
    }

    @Test
    public void testToString() {
        AuthProviderTypeDomain type = AuthProviderTypeDomain.GOOGLE;
        String expected = "GOOGLE";

        assertEquals(expected, type.toString());
    }
}
