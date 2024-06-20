package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Open__1Test {

    @Test
    public void testNoArgsConstructor() {
        Open__1 open = new Open__1();
        assertNull(open.getDay());
        assertNull(open.getTime());
    }

    @Test
    public void testSettersAndGetters() {
        Open__1 open = new Open__1();

        Integer day = 1;
        open.setDay(day);
        assertEquals(day, open.getDay());

        String time = "0800";
        open.setTime(time);
        assertEquals(time, open.getTime());
    }

    @Test
    public void testToString() {
        Open__1 open = new Open__1();

        Integer day = 1;
        open.setDay(day);

        String time = "0800";
        open.setTime(time);

        String expected = Open__1.class.getName() + '@' + Integer.toHexString(System.identityHashCode(open)) + '[' +
                "day=" + ((open.getDay() == null) ? "<null>" : open.getDay()) +
                ",time=" + ((open.getTime() == null) ? "<null>" : open.getTime()) +
                ']';

        assertEquals(expected, open.toString());
    }
}
