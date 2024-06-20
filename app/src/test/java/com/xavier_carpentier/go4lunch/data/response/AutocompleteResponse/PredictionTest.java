package com.xavier_carpentier.go4lunch.data.response.AutocompleteResponse;
import static org.junit.Assert.*;

import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.MatchedSubstring;
import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.Prediction;
import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.StructuredFormatting;
import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.Term;

import org.junit.Test;
import java.util.Collections;
import java.util.List;

public class PredictionTest {

    @Test
    public void testPrediction() {
        MatchedSubstring matchedSubstring = new MatchedSubstring(0, 10);
        Term term = new Term(0, "value");

        StructuredFormatting structuredFormatting = new StructuredFormatting();
        structuredFormatting.setMainText("mainText");
        structuredFormatting.setSecondaryText("secondaryText");

        List<MatchedSubstring> matchedSubstrings = Collections.singletonList(matchedSubstring);
        List<Term> terms = Collections.singletonList(term);
        List<String> types = Collections.singletonList("type");

        Prediction prediction = new Prediction("testDescription", matchedSubstrings, "testId", "testReference", structuredFormatting, terms, types);

        assertEquals("testDescription", prediction.getDescription());
        assertEquals("testId", prediction.getPlaceId());
        assertEquals("testReference", prediction.getReference());
        assertEquals(1, prediction.getMatchedSubstrings().size());
        assertEquals("mainText", prediction.getStructuredFormatting().getMainText());
        assertEquals(1, prediction.getTerms().size());
        assertEquals(1, prediction.getTypes().size());
    }

    @Test
    public void testSetDescription() {
        Prediction prediction = new Prediction();
        prediction.setDescription("newDescription");

        assertEquals("newDescription", prediction.getDescription());
    }

    @Test
    public void testSetPlaceId() {
        Prediction prediction = new Prediction();
        prediction.setPlaceId("newPlaceId");

        assertEquals("newPlaceId", prediction.getPlaceId());
    }

    @Test
    public void testSetMatchedSubstrings() {
        MatchedSubstring matchedSubstring = new MatchedSubstring(0, 5);
        Prediction prediction = new Prediction();
        prediction.setMatchedSubstrings(Collections.singletonList(matchedSubstring));

        assertNotNull(prediction.getMatchedSubstrings());
        assertEquals(1, prediction.getMatchedSubstrings().size());
        assertEquals(5, (long)prediction.getMatchedSubstrings().get(0).getOffset());
    }

    @Test
    public void testSetStructuredFormatting() {
        StructuredFormatting structuredFormatting = new StructuredFormatting();
        structuredFormatting.setMainText("mainText");
        Prediction prediction = new Prediction();
        prediction.setStructuredFormatting(structuredFormatting);

        assertNotNull(prediction.getStructuredFormatting());
        assertEquals("mainText", prediction.getStructuredFormatting().getMainText());
    }

    @Test
    public void testSetTerms() {
        Term term = new Term(0, "term");
        Prediction prediction = new Prediction();
        prediction.setTerms(Collections.singletonList(term));

        assertNotNull(prediction.getTerms());
        assertEquals(1, prediction.getTerms().size());
        assertEquals("term", prediction.getTerms().get(0).getValue());
    }

    @Test
    public void testSetTypes() {
        Prediction prediction = new Prediction();
        prediction.setTypes(Collections.singletonList("type"));

        assertNotNull(prediction.getTypes());
        assertEquals(1, prediction.getTypes().size());
        assertEquals("type", prediction.getTypes().get(0));
    }

    @Test
    public void testGetAndSetReference() {
        Prediction prediction = new Prediction();
        prediction.setReference("newReference");

        assertEquals("newReference", prediction.getReference());
    }

    @Test
    public void testNoArgConstructor() {
        Prediction prediction = new Prediction();

        assertNull(prediction.getDescription());
        assertNull(prediction.getPlaceId());
        assertNull(prediction.getReference());
        assertNull(prediction.getMatchedSubstrings());
        assertNull(prediction.getStructuredFormatting());
        assertNull(prediction.getTerms());
        assertNull(prediction.getTypes());
    }
}