package com.xavier_carpentier.go4lunch.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class AutocompletePredictionDomainTest {

    @Test
    public void testAutocompletePredictionDomain() {
        AutocompletePredictionDomain prediction = new AutocompletePredictionDomain("placeId1", "description1");

        assertEquals("placeId1", prediction.getRestaurantId());
        assertEquals("description1", prediction.getRestaurantName());
    }

    @Test
    public void testEquals() {
        AutocompletePredictionDomain prediction1 = new AutocompletePredictionDomain("placeId1", "description1");
        AutocompletePredictionDomain prediction2 = new AutocompletePredictionDomain("placeId1", "description1");
        AutocompletePredictionDomain prediction3 = new AutocompletePredictionDomain("placeId2", "description2");

        assertEquals(prediction1, prediction2);
        assertNotEquals(prediction1, prediction3);
    }

    @Test
    public void testHashCode() {
        AutocompletePredictionDomain prediction1 = new AutocompletePredictionDomain("placeId1", "description1");
        AutocompletePredictionDomain prediction2 = new AutocompletePredictionDomain("placeId1", "description1");

        assertEquals(prediction1.hashCode(), prediction2.hashCode());
    }

    @Test
    public void testToString() {
        AutocompletePredictionDomain prediction = new AutocompletePredictionDomain("placeId1", "description1");
        String expected = "AutocompletePredictionDomain{restaurantId='placeId1', restaurantName='description1'}";

        assertEquals(expected, prediction.toString());
    }
}
