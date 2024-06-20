package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ViewportTest {

    @Test
    public void testNoArgsConstructor() {
        Viewport viewport = new Viewport();
        assertNull(viewport.getNortheast());
        assertNull(viewport.getSouthwest());
    }

    @Test
    public void testSettersAndGetters() {
        Viewport viewport = new Viewport();

        Northeast northeast = new Northeast();
        viewport.setNortheast(northeast);
        assertEquals(northeast, viewport.getNortheast());

        Southwest southwest = new Southwest();
        viewport.setSouthwest(southwest);
        assertEquals(southwest, viewport.getSouthwest());
    }

    @Test
    public void testToString() {
        Viewport viewport = new Viewport();

        Northeast northeast = new Northeast();
        viewport.setNortheast(northeast);

        Southwest southwest = new Southwest();
        viewport.setSouthwest(southwest);

        String expected = Viewport.class.getName() + '@' + Integer.toHexString(System.identityHashCode(viewport)) + '[' +
                "northeast=" + ((viewport.getNortheast() == null) ? "<null>" : viewport.getNortheast()) +
                ",southwest=" + ((viewport.getSouthwest() == null) ? "<null>" : viewport.getSouthwest()) +
                ']';

        assertEquals(expected, viewport.toString());
    }
}
