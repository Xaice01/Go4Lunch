package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PlusCodeTest {

    @Test
    public void testNoArgsConstructor() {
        PlusCode plusCode = new PlusCode();
        assertNull(plusCode.getCompoundCode());
        assertNull(plusCode.getGlobalCode());
    }

    @Test
    public void testSettersAndGetters() {
        PlusCode plusCode = new PlusCode();

        String compoundCode = "compoundCode";
        plusCode.setCompoundCode(compoundCode);
        assertEquals(compoundCode, plusCode.getCompoundCode());

        String globalCode = "globalCode";
        plusCode.setGlobalCode(globalCode);
        assertEquals(globalCode, plusCode.getGlobalCode());
    }

    @Test
    public void testToString() {
        PlusCode plusCode = new PlusCode();

        String compoundCode = "compoundCode";
        String globalCode = "globalCode";

        plusCode.setCompoundCode(compoundCode);
        plusCode.setGlobalCode(globalCode);

        String expected = PlusCode.class.getName() + '@' + Integer.toHexString(System.identityHashCode(plusCode)) + '[' +
                "compoundCode=" + compoundCode +
                ",globalCode=" + globalCode +
                ']';

        assertEquals(expected, plusCode.toString());
    }
}
