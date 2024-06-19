package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PeriodTest {

    @Test
    public void testNoArgsConstructor() {
        Period period = new Period();
        assertNull(period.getClose());
        assertNull(period.getOpen());
    }

    @Test
    public void testSettersAndGetters() {
        Period period = new Period();

        Close close = new Close();
        period.setClose(close);
        assertEquals(close, period.getClose());

        Open open = new Open();
        period.setOpen(open);
        assertEquals(open, period.getOpen());
    }

    @Test
    public void testToString() {
        Period period = new Period();

        Close close = new Close();
        Open open = new Open();

        period.setClose(close);
        period.setOpen(open);

        String expected = Period.class.getName() + '@' + Integer.toHexString(System.identityHashCode(period)) + '[' +
                "close=" + ((close == null) ? "<null>" : close) +
                ",open=" + ((open == null) ? "<null>" : open) +
                ']';

        assertEquals(expected, period.toString());
    }
}
