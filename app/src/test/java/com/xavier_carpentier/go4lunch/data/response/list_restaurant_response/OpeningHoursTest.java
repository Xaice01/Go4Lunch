package com.xavier_carpentier.go4lunch.data.response.list_restaurant_response;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OpeningHoursTest {

    @Test
    public void testNoArgsConstructor() {
        OpeningHours openingHours = new OpeningHours();
        assertNull(openingHours.getOpenNow());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        Boolean openNow = true;
        OpeningHours openingHours = new OpeningHours(openNow);
        assertEquals(openNow, openingHours.getOpenNow());
    }

    @Test
    public void testSetters() {
        OpeningHours openingHours = new OpeningHours();
        Boolean openNow = true;
        openingHours.setOpenNow(openNow);
        assertEquals(openNow, openingHours.getOpenNow());
    }

    @Test
    public void testToString() {
        OpeningHours openingHours = new OpeningHours();
        Boolean openNow = true;
        openingHours.setOpenNow(openNow);

        String expected = OpeningHours.class.getName() + '@' + Integer.toHexString(System.identityHashCode(openingHours)) + '[' +
                "openNow=" + ((openingHours.getOpenNow() == null) ? "<null>" : openingHours.getOpenNow()) +
                ']';

        assertEquals(expected, openingHours.toString());
    }
}
