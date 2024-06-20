package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AddressComponentTest {

    @Test
    public void testNoArgsConstructor() {
        AddressComponent addressComponent = new AddressComponent();
        assertNull(addressComponent.getLongName());
        assertNull(addressComponent.getShortName());
        assertNull(addressComponent.getTypes());
    }

    @Test
    public void testSettersAndGetters() {
        AddressComponent addressComponent = new AddressComponent();

        String longName = "123 Main St";
        addressComponent.setLongName(longName);
        assertEquals(longName, addressComponent.getLongName());

        String shortName = "Main St";
        addressComponent.setShortName(shortName);
        assertEquals(shortName, addressComponent.getShortName());

        String type1 = "street_address";
        String type2 = "political";
        addressComponent.setTypes(Arrays.asList(type1, type2));
        assertEquals(Arrays.asList(type1, type2), addressComponent.getTypes());
    }

    @Test
    public void testToString() {
        AddressComponent addressComponent = new AddressComponent();

        String longName = "123 Main St";
        addressComponent.setLongName(longName);

        String shortName = "Main St";
        addressComponent.setShortName(shortName);

        String type1 = "street_address";
        String type2 = "political";
        addressComponent.setTypes(Arrays.asList(type1, type2));

        String expected = AddressComponent.class.getName() + '@' + Integer.toHexString(System.identityHashCode(addressComponent)) + '[' +
                "longName=" + ((addressComponent.getLongName() == null) ? "<null>" : addressComponent.getLongName()) +
                ",shortName=" + ((addressComponent.getShortName() == null) ? "<null>" : addressComponent.getShortName()) +
                ",types=" + ((addressComponent.getTypes() == null) ? "<null>" : addressComponent.getTypes()) +
                ']';

        assertEquals(expected, addressComponent.toString());
    }
}
