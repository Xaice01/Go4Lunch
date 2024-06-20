package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SouthwestTest {

    @Test
    public void testNoArgsConstructor() {
        Southwest southwest = new Southwest();
        assertNull(southwest.getLat());
        assertNull(southwest.getLng());
    }

    @Test
    public void testSettersAndGetters() {
        Southwest southwest = new Southwest();

        southwest.setLat(12.34);
        assertEquals(Double.valueOf(12.34), southwest.getLat());

        southwest.setLng(56.78);
        assertEquals(Double.valueOf(56.78), southwest.getLng());
    }

    @Test
    public void testToString() {
        Southwest southwest = new Southwest();
        southwest.setLat(12.34);
        southwest.setLng(56.78);

        String expected = Southwest.class.getName() + '@' + Integer.toHexString(System.identityHashCode(southwest)) + '[' +
                "lat=" + ((southwest.getLat() == null) ? "<null>" : southwest.getLat()) +
                ",lng=" + ((southwest.getLng() == null) ? "<null>" : southwest.getLng()) +
                ']';

        assertEquals(expected, southwest.toString());
    }
}