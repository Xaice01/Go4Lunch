package com.xavier_carpentier.go4lunch.data.response.list_restaurant_response;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GeometryTest {

    @Test
    public void testNoArgsConstructor() {
        Geometry geometry = new Geometry();
        assertNull(geometry.getLocation());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        Location location = new Location();
        Geometry geometry = new Geometry(location);

        assertEquals(location, geometry.getLocation());
    }

    @Test
    public void testSetters() {
        Geometry geometry = new Geometry();

        Location location = new Location();
        geometry.setLocation(location);
        assertEquals(location, geometry.getLocation());
    }

    @Test
    public void testToString() {
        Geometry geometry = new Geometry();

        Location location = new Location();
        geometry.setLocation(location);

        String expected = Geometry.class.getName() + '@' + Integer.toHexString(System.identityHashCode(geometry)) + '[' +
                "location=" + ((geometry.getLocation() == null) ? "<null>" : geometry.getLocation()) +
                ']';

        assertEquals(expected, geometry.toString());
    }
}
