package com.xavier_carpentier.go4lunch.presentation.model;

import static org.junit.Assert.assertEquals;

import android.net.Uri;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class WorkmateTest {

    private Workmate workmate;

    @Before
    public void setUp() {
        Uri urlPicture = Uri.parse("http://example.com/picture");
        workmate = new Workmate("idUser1", "userName1", urlPicture, "idRestaurant1", "restaurantName1");
    }

    @Test
    public void testGetters() {
        assertEquals("idUser1", workmate.getUid());
        assertEquals("userName1", workmate.getUsername());
        assertEquals(Uri.parse("http://example.com/picture"), workmate.getUrlPicture());
        assertEquals("idRestaurant1", workmate.getUidRestaurant());
        assertEquals("restaurantName1", workmate.getRestaurantName());
    }

    @Test
    public void testSetters() {
        workmate.setUidRestaurant("idRestaurant2");
        workmate.setRestaurantName("restaurantName2");

        assertEquals("idRestaurant2", workmate.getUidRestaurant());
        assertEquals("restaurantName2", workmate.getRestaurantName());
    }
}