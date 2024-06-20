package com.xavier_carpentier.go4lunch.data.response.AutocompleteResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.AutocompleteResponse;
import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.MatchedSubstring;
import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.Prediction;
import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.StructuredFormatting;
import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.Term;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class AutocompleteResponseTest {

    @Test
    public void testAutocompleteResponse() {
        MatchedSubstring matchedSubstring = new MatchedSubstring(0, 10);
        Term term = new Term(0, "value");

        List<MatchedSubstring> matchedSubstrings = Collections.singletonList(matchedSubstring);
        List<Term> terms = Collections.singletonList(term);

        StructuredFormatting structuredFormatting = new StructuredFormatting();
        structuredFormatting.setMainText("mainText");
        structuredFormatting.setSecondaryText("secondaryText");

        Prediction prediction = new Prediction();
        prediction.setDescription("testDescription");
        prediction.setPlaceId("testId");
        prediction.setMatchedSubstrings(matchedSubstrings);
        prediction.setStructuredFormatting(structuredFormatting);
        prediction.setTerms(terms);
        prediction.setTypes(Collections.singletonList("type"));

        AutocompleteResponse response = new AutocompleteResponse(Collections.singletonList(prediction), "OK");

        assertNotNull(response.getPredictions());
        assertEquals("OK", response.getStatus());
    }

    @Test
    public void testEmptyResponse() {
        AutocompleteResponse response = new AutocompleteResponse(Collections.emptyList(), "ZERO_RESULTS");

        assertTrue(response.getPredictions().isEmpty());
        assertEquals("ZERO_RESULTS", response.getStatus());
    }
    @Test
    public void testSetStatus() {
        AutocompleteResponse response = new AutocompleteResponse();
        response.setStatus("OVER_QUERY_LIMIT");

        assertEquals("OVER_QUERY_LIMIT", response.getStatus());
    }

    @Test
    public void testSetPredictions() {
        Prediction prediction = new Prediction();
        prediction.setDescription("testDescription");

        AutocompleteResponse response = new AutocompleteResponse();
        response.setPredictions(Collections.singletonList(prediction));

        assertNotNull(response.getPredictions());
        assertEquals(1, response.getPredictions().size());
        assertEquals("testDescription", response.getPredictions().get(0).getDescription());
    }

    @Test
    public void testToString() {
        Prediction prediction = new Prediction();
        prediction.setDescription("testDescription");

        AutocompleteResponse response = new AutocompleteResponse();
        response.setStatus("OK");
        response.setPredictions(Collections.singletonList(prediction));

        StringBuilder expectedString = new StringBuilder();
        expectedString.append(AutocompleteResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(response))).append('[');
        expectedString.append("predictions");
        expectedString.append('=');
        expectedString.append('[');
        expectedString.append((prediction));
        expectedString.append(']');
        expectedString.append(',');
        expectedString.append("status");
        expectedString.append('=');
        expectedString.append(response.getStatus());
        expectedString.append(']');

        assertEquals(expectedString.toString(), response.toString());
    }

    @Test
    public void testToStringWithEmptyPredictions() {
        AutocompleteResponse response = new AutocompleteResponse(Collections.emptyList(), "ZERO_RESULTS");

        StringBuilder expectedString = new StringBuilder();
        expectedString.append(AutocompleteResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(response))).append('[');
        expectedString.append("predictions");
        expectedString.append('=');
        expectedString.append('[');
        expectedString.append(']');
        expectedString.append(',');
        expectedString.append("status");
        expectedString.append('=');
        expectedString.append(response.getStatus());
        expectedString.append(']');

        assertEquals(expectedString.toString(), response.toString());
    }

    @Test
    public void testDefaultConstructor() {
        AutocompleteResponse response = new AutocompleteResponse();

        assertNotNull(response);
        assertNull(response.getStatus());
        assertNull(response.getPredictions());
    }
}
