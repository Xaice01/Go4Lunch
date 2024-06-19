package com.xavier_carpentier.go4lunch.presentation.model;

import static org.junit.Assert.assertEquals;

import android.net.Uri;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class RestaurantItemTest {

    private RestaurantItem restaurantItem;

    @Before
    public void setUp() {
        ShadowLog.stream = System.out; // Enable logging

        Uri pictureUri = Uri.parse("http://example.com/picture");
        restaurantItem = new RestaurantItem("id1", "RestaurantName", "123 Main St", 5, 4.5, "10.0", "20.0", 10, true, pictureUri);
    }

    @Test
    public void testGetters() {
        assertEquals("id1", restaurantItem.getUid());
        assertEquals("RestaurantName", restaurantItem.getName());
        assertEquals("123 Main St", restaurantItem.getAddress());
        assertEquals(5, restaurantItem.getDistance());
        assertEquals((Double)4.5,(Double) restaurantItem.getNote());
        assertEquals("10.0", restaurantItem.getLatitude());
        assertEquals("20.0", restaurantItem.getLongitude());
        assertEquals(10, restaurantItem.getWorkmatesToEat());
        assertEquals(true, restaurantItem.getIsOpen());
        assertEquals(Uri.parse("http://example.com/picture"), restaurantItem.getPicture());
    }

    @Test
    public void testSetWorkmatesToEat() {
        restaurantItem.setWorkmatesToEat(5);
        assertEquals(5, restaurantItem.getWorkmatesToEat());
    }
}