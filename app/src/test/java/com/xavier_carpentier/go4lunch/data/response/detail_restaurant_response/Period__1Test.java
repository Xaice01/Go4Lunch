package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Period__1Test {

    @Test
    public void testNoArgsConstructor() {
        Period__1 period = new Period__1();
        assertNull(period.getClose());
        assertNull(period.getOpen());
    }

    @Test
    public void testSettersAndGetters() {
        Period__1 period = new Period__1();

        Close__1 close = new Close__1();
        period.setClose(close);
        assertEquals(close, period.getClose());

        Open__1 open = new Open__1();
        period.setOpen(open);
        assertEquals(open, period.getOpen());
    }

    @Test
    public void testToString() {
        Period__1 period = new Period__1();

        Close__1 close = new Close__1();
        Open__1 open = new Open__1();

        period.setClose(close);
        period.setOpen(open);

        String expected = Period__1.class.getName() + '@' + Integer.toHexString(System.identityHashCode(period)) + '[' +
                "close=" + ((close == null) ? "<null>" : close) +
                ",open=" + ((open == null) ? "<null>" : open) +
                ']';

        assertEquals(expected, period.toString());
    }
}
