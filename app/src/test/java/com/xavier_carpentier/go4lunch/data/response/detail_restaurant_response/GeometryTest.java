package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GeometryTest {

    @Test
    public void testNoArgsConstructor() {
        Geometry geometry = new Geometry();
        assertNull(geometry.getLocation());
        assertNull(geometry.getViewport());
    }

    @Test
    public void testSettersAndGetters() {
        Geometry geometry = new Geometry();

        Location location = new Location();
        geometry.setLocation(location);
        assertEquals(location, geometry.getLocation());

        Viewport viewport = new Viewport();
        geometry.setViewport(viewport);
        assertEquals(viewport, geometry.getViewport());
    }

    @Test
    public void testToString() {
        Geometry geometry = new Geometry();

        Location location = new Location();
        geometry.setLocation(location);

        Viewport viewport = new Viewport();
        geometry.setViewport(viewport);

        String expected = Geometry.class.getName() + '@' + Integer.toHexString(System.identityHashCode(geometry)) + '[' +
                "location=" + ((geometry.getLocation() == null) ? "<null>" : geometry.getLocation()) +
                ",viewport=" + ((geometry.getViewport() == null) ? "<null>" : geometry.getViewport()) +
                ']';

        assertEquals(expected, geometry.toString());
    }
}
