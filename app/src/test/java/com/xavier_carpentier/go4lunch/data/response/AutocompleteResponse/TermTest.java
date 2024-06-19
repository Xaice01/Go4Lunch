package com.xavier_carpentier.go4lunch.data.response.AutocompleteResponse;
import org.junit.Test;
import static org.junit.Assert.*;

import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.Term;

public class TermTest {

    @Test
    public void testNoArgsConstructor() {
        Term term = new Term();

        assertNull(term.getOffset());
        assertNull(term.getValue());
    }

    @Test
    public void testAllArgsConstructor() {
        Term term = new Term(10, "value");

        assertEquals(Integer.valueOf(10), term.getOffset());
        assertEquals("value", term.getValue());
    }

    @Test
    public void testSetOffset() {
        Term term = new Term();
        term.setOffset(20);

        assertEquals(Integer.valueOf(20), term.getOffset());
    }

    @Test
    public void testSetValue() {
        Term term = new Term();
        term.setValue("newValue");

        assertEquals("newValue", term.getValue());
    }

    @Test
    public void testToString() {
        Term term = new Term(15, "testValue");

        String expected = "com.xavier_carpentier.go4lunch.data.response.autocomplete_response.Term@" +
                Integer.toHexString(System.identityHashCode(term)) +
                "[offset=15,value=testValue]";

        assertEquals(expected, term.toString());
    }
}
