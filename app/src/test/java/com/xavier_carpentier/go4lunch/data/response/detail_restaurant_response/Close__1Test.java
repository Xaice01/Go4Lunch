package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Close__1Test {

    @Test
    public void testNoArgsConstructor() {
        Close__1 close = new Close__1();
        assertNull(close.getDay());
        assertNull(close.getTime());
    }

    @Test
    public void testSettersAndGetters() {
        Close__1 close = new Close__1();

        Integer day = 1;
        close.setDay(day);
        assertEquals(day, close.getDay());

        String time = "1700";
        close.setTime(time);
        assertEquals(time, close.getTime());
    }

}
