package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CloseTest {

    @Test
    public void testNoArgsConstructor() {
        Close close = new Close();
        assertNull(close.getDate());
        assertNull(close.getDay());
        assertNull(close.getTime());
    }

    @Test
    public void testSettersAndGetters() {
        Close close = new Close();

        String date = "2024-06-18";
        close.setDate(date);
        assertEquals(date, close.getDate());

        Integer day = 1;
        close.setDay(day);
        assertEquals(day, close.getDay());

        String time = "1700";
        close.setTime(time);
        assertEquals(time, close.getTime());
    }

    @Test
    public void testToString() {
        Close close = new Close();

        String date = "2024-06-18";
        close.setDate(date);

        Integer day = 1;
        close.setDay(day);

        String time = "1700";
        close.setTime(time);

        String expected = Close.class.getName() + '@' + Integer.toHexString(System.identityHashCode(close)) + '[' +
                "date=" + ((close.getDate() == null) ? "<null>" : close.getDate()) +
                ",day=" + ((close.getDay() == null) ? "<null>" : close.getDay()) +
                ",time=" + ((close.getTime() == null) ? "<null>" : close.getTime()) +
                ']';

        assertEquals(expected, close.toString());
    }
}
