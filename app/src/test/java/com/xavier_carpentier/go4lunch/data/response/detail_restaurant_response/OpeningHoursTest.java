package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OpeningHoursTest {

    @Test
    public void testNoArgsConstructor() {
        OpeningHours openingHours = new OpeningHours();
        assertNull(openingHours.getOpenNow());
        assertNull(openingHours.getPeriods());
        assertNull(openingHours.getWeekdayText());
    }

    @Test
    public void testSettersAndGetters() {
        OpeningHours openingHours = new OpeningHours();

        Boolean openNow = true;
        openingHours.setOpenNow(openNow);
        assertEquals(openNow, openingHours.getOpenNow());

        List<Period__1> periods = Collections.singletonList(new Period__1());
        openingHours.setPeriods(periods);
        assertEquals(periods, openingHours.getPeriods());

        List<String> weekdayText = Arrays.asList("Monday", "Tuesday");
        openingHours.setWeekdayText(weekdayText);
        assertEquals(weekdayText, openingHours.getWeekdayText());
    }

    @Test
    public void testToString() {
        OpeningHours openingHours = new OpeningHours();

        Boolean openNow = true;
        List<Period__1> periods = Collections.singletonList(new Period__1());
        List<String> weekdayText = Arrays.asList("Monday", "Tuesday");

        openingHours.setOpenNow(openNow);
        openingHours.setPeriods(periods);
        openingHours.setWeekdayText(weekdayText);

        String expected = OpeningHours.class.getName() + '@' + Integer.toHexString(System.identityHashCode(openingHours)) + '[' +
                "openNow=" + openNow +
                ",periods=" + periods +
                ",weekdayText=" + weekdayText +
                ']';

        assertEquals(expected, openingHours.toString());
    }
}
