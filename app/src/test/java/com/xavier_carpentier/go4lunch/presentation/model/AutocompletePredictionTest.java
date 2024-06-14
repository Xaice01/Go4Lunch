package com.xavier_carpentier.go4lunch.presentation.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class AutocompletePredictionTest {

    private AutocompletePrediction autocompletePrediction1;
    private AutocompletePrediction autocompletePrediction2;
    private AutocompletePrediction autocompletePrediction3;

    @Before
    public void setUp() {
        autocompletePrediction1 = new AutocompletePrediction("1", "Restaurant A");
        autocompletePrediction2 = new AutocompletePrediction("1", "Restaurant A");
        autocompletePrediction3 = new AutocompletePrediction("2", "Restaurant B");
    }

    @Test
    public void testGetRestaurantId() {
        assertEquals("1", autocompletePrediction1.getRestaurantId());
    }

    @Test
    public void testGetRestaurantName() {
        assertEquals("Restaurant A", autocompletePrediction1.getRestaurantName());
    }

    @Test
    public void testEquals() {
        assertTrue(autocompletePrediction1.equals(autocompletePrediction2));
        assertFalse(autocompletePrediction1.equals(autocompletePrediction3));
    }

    @Test
    public void testHashCode() {
        assertEquals(autocompletePrediction1.hashCode(), autocompletePrediction2.hashCode());
        assertNotEquals(autocompletePrediction1.hashCode(), autocompletePrediction3.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "AutocompletePrediction{restaurantId='1', restaurantName='Restaurant A'}";
        assertEquals(expectedString, autocompletePrediction1.toString());
    }
}