package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

public class CurrentOpeningHoursTest {

    @Test
    public void testNoArgsConstructor() {
        CurrentOpeningHours currentOpeningHours = new CurrentOpeningHours();
        assertNull(currentOpeningHours.getOpenNow());
        assertNull(currentOpeningHours.getPeriods());
        assertNull(currentOpeningHours.getWeekdayText());
    }

    @Test
    public void testSettersAndGetters() {
        CurrentOpeningHours currentOpeningHours = new CurrentOpeningHours();

        Boolean openNow = true;
        currentOpeningHours.setOpenNow(openNow);
        assertEquals(openNow, currentOpeningHours.getOpenNow());

        List<Period> periods = new ArrayList<>();
        currentOpeningHours.setPeriods(periods);
        assertEquals(periods, currentOpeningHours.getPeriods());

        List<String> weekdayText = new ArrayList<>();
        currentOpeningHours.setWeekdayText(weekdayText);
        assertEquals(weekdayText, currentOpeningHours.getWeekdayText());
    }

    @Test
    public void testToString() {
        CurrentOpeningHours currentOpeningHours = new CurrentOpeningHours();

        Boolean openNow = true;
        currentOpeningHours.setOpenNow(openNow);

        List<Period> periods = new ArrayList<>();
        currentOpeningHours.setPeriods(periods);

        List<String> weekdayText = new ArrayList<>();
        currentOpeningHours.setWeekdayText(weekdayText);

        String expected = CurrentOpeningHours.class.getName() + '@' + Integer.toHexString(System.identityHashCode(currentOpeningHours)) + '[' +
                "openNow=" + ((currentOpeningHours.getOpenNow() == null) ? "<null>" : currentOpeningHours.getOpenNow()) +
                ",periods=" + ((currentOpeningHours.getPeriods() == null) ? "<null>" : currentOpeningHours.getPeriods()) +
                ",weekdayText=" + ((currentOpeningHours.getWeekdayText() == null) ? "<null>" : currentOpeningHours.getWeekdayText()) +
                ']';

        assertEquals(expected, currentOpeningHours.toString());
    }
}
