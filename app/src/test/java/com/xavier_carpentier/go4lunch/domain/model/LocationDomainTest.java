package com.xavier_carpentier.go4lunch.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class LocationDomainTest {

    @Test
    public void testLocationDomain() {
        LocationDomain location = new LocationDomain("10.123", "20.456");

        assertEquals("10.123", location.getLatitude());
        assertEquals("20.456", location.getLongitude());
    }

    @Test
    public void testEquals() {
        LocationDomain location1 = new LocationDomain("10.123", "20.456");
        LocationDomain location2 = new LocationDomain("10.123", "20.456");
        LocationDomain location3 = new LocationDomain("30.789", "40.012");

        assertEquals(location1, location2);
        assertNotEquals(location1, location3);
    }

    @Test
    public void testHashCode() {
        LocationDomain location1 = new LocationDomain("10.123", "20.456");
        LocationDomain location2 = new LocationDomain("10.123", "20.456");

        assertEquals(location1.hashCode(), location2.hashCode());
    }

}
