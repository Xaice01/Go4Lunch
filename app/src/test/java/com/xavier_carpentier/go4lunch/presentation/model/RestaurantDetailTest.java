package com.xavier_carpentier.go4lunch.presentation.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class RestaurantDetailTest {

    private RestaurantDetail restaurantDetail;

    @Before
    public void setUp() {
        restaurantDetail = new RestaurantDetail("uid123", "http://example.com/picture.jpg", "Restaurant Name", "123 Main St", 4.5, "0698989898", true, "http://example.com");
    }

    @Test
    public void testGetUid() {
        assertEquals("uid123", restaurantDetail.getUid());
    }

    @Test
    public void testGetPicture() {
        assertEquals("http://example.com/picture.jpg", restaurantDetail.getPicture());
    }

    @Test
    public void testGetName() {
        assertEquals("Restaurant Name", restaurantDetail.getName());
    }

    @Test
    public void testGetAddress() {
        assertEquals("123 Main St", restaurantDetail.getAddress());
    }

    @Test
    public void testGetNote() {
        assertEquals(Double.valueOf(4.5), restaurantDetail.getNote());
    }

    @Test
    public void testGetPhoneNumber() {
        assertEquals("0698989898", restaurantDetail.getPhone_number());
    }

    @Test
    public void testIsLike() {
        assertTrue(restaurantDetail.isLike());
    }

    @Test
    public void testGetWebSite() {
        assertEquals("http://example.com", restaurantDetail.getWebSite());
    }

    @Test
    public void testSetLike() {
        restaurantDetail.setLike(false);
        assertFalse(restaurantDetail.isLike());

        restaurantDetail.setLike(true);
        assertTrue(restaurantDetail.isLike());
    }
}