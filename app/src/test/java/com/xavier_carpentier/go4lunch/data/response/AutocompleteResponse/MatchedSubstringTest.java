package com.xavier_carpentier.go4lunch.data.response.AutocompleteResponse;
import org.junit.Test;
import static org.junit.Assert.*;

import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.MatchedSubstring;

public class MatchedSubstringTest {

    @Test
    public void testNoArgsConstructor() {
        MatchedSubstring matchedSubstring = new MatchedSubstring();

        assertNull(matchedSubstring.getLength());
        assertNull(matchedSubstring.getOffset());
    }

    @Test
    public void testAllArgsConstructor() {
        MatchedSubstring matchedSubstring = new MatchedSubstring(5, 10);

        assertEquals(Integer.valueOf(5), matchedSubstring.getLength());
        assertEquals(Integer.valueOf(10), matchedSubstring.getOffset());
    }

    @Test
    public void testSetLength() {
        MatchedSubstring matchedSubstring = new MatchedSubstring();
        matchedSubstring.setLength(15);

        assertEquals(Integer.valueOf(15), matchedSubstring.getLength());
    }

    @Test
    public void testSetOffset() {
        MatchedSubstring matchedSubstring = new MatchedSubstring();
        matchedSubstring.setOffset(20);

        assertEquals(Integer.valueOf(20), matchedSubstring.getOffset());
    }

    @Test
    public void testToString() {
        MatchedSubstring matchedSubstring = new MatchedSubstring(5, 10);

        String expected = "com.xavier_carpentier.go4lunch.data.response.autocomplete_response.MatchedSubstring@" +
                Integer.toHexString(System.identityHashCode(matchedSubstring)) +
                "[length=5,offset=10]";

        assertEquals(expected, matchedSubstring.toString());
    }
}
