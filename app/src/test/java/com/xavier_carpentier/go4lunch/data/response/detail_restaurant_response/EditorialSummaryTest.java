package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EditorialSummaryTest {

    @Test
    public void testNoArgsConstructor() {
        EditorialSummary editorialSummary = new EditorialSummary();
        assertNull(editorialSummary.getLanguage());
        assertNull(editorialSummary.getOverview());
    }

    @Test
    public void testSettersAndGetters() {
        EditorialSummary editorialSummary = new EditorialSummary();

        String language = "English";
        editorialSummary.setLanguage(language);
        assertEquals(language, editorialSummary.getLanguage());

        String overview = "This is an overview.";
        editorialSummary.setOverview(overview);
        assertEquals(overview, editorialSummary.getOverview());
    }

    @Test
    public void testToString() {
        EditorialSummary editorialSummary = new EditorialSummary();

        String language = "English";
        editorialSummary.setLanguage(language);

        String overview = "This is an overview.";
        editorialSummary.setOverview(overview);

        String expected = EditorialSummary.class.getName() + '@' + Integer.toHexString(System.identityHashCode(editorialSummary)) + '[' +
                "language=" + ((editorialSummary.getLanguage() == null) ? "<null>" : editorialSummary.getLanguage()) +
                ",overview=" + ((editorialSummary.getOverview() == null) ? "<null>" : editorialSummary.getOverview()) +
                ']';

        assertEquals(expected, editorialSummary.toString());
    }
}
