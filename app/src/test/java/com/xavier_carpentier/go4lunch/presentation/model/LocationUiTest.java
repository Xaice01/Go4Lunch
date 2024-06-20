package com.xavier_carpentier.go4lunch.presentation.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class LocationUiTest {

    private LocationUi locationUi1;
    private LocationUi locationUi2;
    private LocationUi locationUi3;

    @Before
    public void setUp() {
        locationUi1 = new LocationUi("48.8566", "2.3522");
        locationUi2 = new LocationUi("48.8566", "2.3522");
        locationUi3 = new LocationUi("40.7128", "-74.0060");
    }

    @Test
    public void testGetLatitude() {
        assertEquals("48.8566", locationUi1.getLatitude());
    }

    @Test
    public void testGetLongitude() {
        assertEquals("2.3522", locationUi1.getLongitude());
    }

    @Test
    public void testEquals() {
        assertTrue(locationUi1.equals(locationUi2));
        assertFalse(locationUi1.equals(locationUi3));
    }

    @Test
    public void testHashCode() {
        assertEquals(locationUi1.hashCode(), locationUi2.hashCode());
        assertNotEquals(locationUi1.hashCode(), locationUi3.hashCode());
    }
}
