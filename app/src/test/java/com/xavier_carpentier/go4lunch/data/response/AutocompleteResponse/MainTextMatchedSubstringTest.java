package com.xavier_carpentier.go4lunch.data.response.AutocompleteResponse;
import org.junit.Test;
import static org.junit.Assert.*;

import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.MainTextMatchedSubstring;

public class MainTextMatchedSubstringTest {

    @Test
    public void testNoArgsConstructor() {
        MainTextMatchedSubstring mainTextMatchedSubstring = new MainTextMatchedSubstring();

        assertNull(mainTextMatchedSubstring.getLength());
        assertNull(mainTextMatchedSubstring.getOffset());
    }

    @Test
    public void testAllArgsConstructor() {
        MainTextMatchedSubstring mainTextMatchedSubstring = new MainTextMatchedSubstring(5, 10);

        assertEquals(Integer.valueOf(5), mainTextMatchedSubstring.getLength());
        assertEquals(Integer.valueOf(10), mainTextMatchedSubstring.getOffset());
    }

    @Test
    public void testSetLength() {
        MainTextMatchedSubstring mainTextMatchedSubstring = new MainTextMatchedSubstring();
        mainTextMatchedSubstring.setLength(15);

        assertEquals(Integer.valueOf(15), mainTextMatchedSubstring.getLength());
    }

    @Test
    public void testSetOffset() {
        MainTextMatchedSubstring mainTextMatchedSubstring = new MainTextMatchedSubstring();
        mainTextMatchedSubstring.setOffset(20);

        assertEquals(Integer.valueOf(20), mainTextMatchedSubstring.getOffset());
    }

    @Test
    public void testToString() {
        MainTextMatchedSubstring mainTextMatchedSubstring = new MainTextMatchedSubstring(5, 10);

        String expected = "com.xavier_carpentier.go4lunch.data.response.autocomplete_response.MainTextMatchedSubstring@" +
                Integer.toHexString(System.identityHashCode(mainTextMatchedSubstring)) +
                "[length=5,offset=10]";

        assertEquals(expected, mainTextMatchedSubstring.toString());
    }

}