package com.xavier_carpentier.go4lunch.data.response.AutocompleteResponse;
import static org.junit.Assert.*;

import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.MainTextMatchedSubstring;
import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.StructuredFormatting;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class StructuredFormattingTest {

    @Test
    public void testNoArgsConstructor() {
        StructuredFormatting structuredFormatting = new StructuredFormatting();

        assertNull(structuredFormatting.getMainText());
        assertNull(structuredFormatting.getMainTextMatchedSubstrings());
        assertNull(structuredFormatting.getSecondaryText());
    }

    @Test
    public void testAllArgsConstructor() {
        List<MainTextMatchedSubstring> mainTextMatchedSubstrings = Collections.singletonList(new MainTextMatchedSubstring(0, 5));
        StructuredFormatting structuredFormatting = new StructuredFormatting("mainText", mainTextMatchedSubstrings, "secondaryText");

        assertEquals("mainText", structuredFormatting.getMainText());
        assertEquals(mainTextMatchedSubstrings, structuredFormatting.getMainTextMatchedSubstrings());
        assertEquals("secondaryText", structuredFormatting.getSecondaryText());
    }

    @Test
    public void testSetMainText() {
        StructuredFormatting structuredFormatting = new StructuredFormatting();
        structuredFormatting.setMainText("newMainText");

        assertEquals("newMainText", structuredFormatting.getMainText());
    }

    @Test
    public void testSetMainTextMatchedSubstrings() {
        StructuredFormatting structuredFormatting = new StructuredFormatting();
        List<MainTextMatchedSubstring> mainTextMatchedSubstrings = Collections.singletonList(new MainTextMatchedSubstring(0, 5));
        structuredFormatting.setMainTextMatchedSubstrings(mainTextMatchedSubstrings);

        assertEquals(mainTextMatchedSubstrings, structuredFormatting.getMainTextMatchedSubstrings());
    }

    @Test
    public void testSetSecondaryText() {
        StructuredFormatting structuredFormatting = new StructuredFormatting();
        structuredFormatting.setSecondaryText("newSecondaryText");

        assertEquals("newSecondaryText", structuredFormatting.getSecondaryText());
    }

    @Test
    public void testToString() {
        List<MainTextMatchedSubstring> mainTextMatchedSubstrings = Collections.singletonList(new MainTextMatchedSubstring(0, 5));
        StructuredFormatting structuredFormatting = new StructuredFormatting("mainText", mainTextMatchedSubstrings, "secondaryText");

        String expected = "com.xavier_carpentier.go4lunch.data.response.autocomplete_response.StructuredFormatting@" +
                Integer.toHexString(System.identityHashCode(structuredFormatting)) +
                "[mainText=mainText,mainTextMatchedSubstrings=" + mainTextMatchedSubstrings + ",secondaryText=secondaryText]";
        assertEquals(expected, structuredFormatting.toString());
    }
}