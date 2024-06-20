package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NortheastTest {

    @Test
    public void testNoArgsConstructor() {
        Northeast northeast = new Northeast();
        assertNull(northeast.getLat());
        assertNull(northeast.getLng());
    }

    @Test
    public void testSettersAndGetters() {
        Northeast northeast = new Northeast();

        Double lat = 40.7128;
        northeast.setLat(lat);
        assertEquals(lat, northeast.getLat());

        Double lng = -74.0060;
        northeast.setLng(lng);
        assertEquals(lng, northeast.getLng());
    }

    @Test
    public void testToString() {
        Northeast northeast = new Northeast();

        Double lat = 40.7128;
        northeast.setLat(lat);

        Double lng = -74.0060;
        northeast.setLng(lng);

        String expected = Northeast.class.getName() + '@' + Integer.toHexString(System.identityHashCode(northeast)) + '[' +
                "lat=" + ((northeast.getLat() == null) ? "<null>" : northeast.getLat()) +
                ",lng=" + ((northeast.getLng() == null) ? "<null>" : northeast.getLng()) +
                ']';

        assertEquals(expected, northeast.toString());
    }
}
