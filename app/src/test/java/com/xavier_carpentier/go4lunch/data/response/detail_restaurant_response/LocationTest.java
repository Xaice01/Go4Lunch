package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LocationTest {

    @Test
    public void testNoArgsConstructor() {
        Location location = new Location();
        assertNull(location.getLat());
        assertNull(location.getLng());
    }

    @Test
    public void testSettersAndGetters() {
        Location location = new Location();

        Double lat = 40.7128;
        location.setLat(lat);
        assertEquals(lat, location.getLat());

        Double lng = -74.0060;
        location.setLng(lng);
        assertEquals(lng, location.getLng());
    }

    @Test
    public void testToString() {
        Location location = new Location();

        Double lat = 40.7128;
        location.setLat(lat);

        Double lng = -74.0060;
        location.setLng(lng);

        String expected = Location.class.getName() + '@' + Integer.toHexString(System.identityHashCode(location)) + '[' +
                "lat=" + ((location.getLat() == null) ? "<null>" : location.getLat()) +
                ",lng=" + ((location.getLng() == null) ? "<null>" : location.getLng()) +
                ']';

        assertEquals(expected, location.toString());
    }
}
