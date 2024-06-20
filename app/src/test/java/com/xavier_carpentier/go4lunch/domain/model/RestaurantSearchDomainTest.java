package com.xavier_carpentier.go4lunch.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class RestaurantSearchDomainTest {

    @Test
    public void testRestaurantSearchDomain() {
        RestaurantSearchDomain restaurant = new RestaurantSearchDomain("id1", "Restaurant Name", "Address", "PhotoUrl", 4.5, "lat", "lng", 10, true);

        assertEquals("id1", restaurant.getPlaceId());
        assertEquals("Restaurant Name", restaurant.getRestaurantName());
        assertEquals("Address", restaurant.getVicinity());
        assertEquals("PhotoUrl", restaurant.getPhotoReferenceUrl());
        assertEquals(4.5, restaurant.getRating(), 0);
        assertEquals("lat", restaurant.getLatitude());
        assertEquals("lng", restaurant.getLongitude());
        assertTrue(restaurant.getOpen());
    }

    @Test
    public void testEquals() {
        RestaurantSearchDomain restaurant1 = new RestaurantSearchDomain("id1", "Restaurant Name", "Address", "PhotoUrl", 4.5, "lat", "lng", 10, true);
        RestaurantSearchDomain restaurant2 = new RestaurantSearchDomain("id1", "Restaurant Name", "Address", "PhotoUrl", 4.5, "lat", "lng", 10, true);
        RestaurantSearchDomain restaurant3 = new RestaurantSearchDomain("id2", "Another Restaurant", "Another Address", "AnotherPhotoUrl", 3.5, "lat2", "lng2", 20, false);

        assertEquals(restaurant1, restaurant2);
        assertNotEquals(restaurant1, restaurant3);
    }

    @Test
    public void testHashCode() {
        RestaurantSearchDomain restaurant1 = new RestaurantSearchDomain("id1", "Restaurant Name", "Address", "PhotoUrl", 4.5, "lat", "lng", 10, true);
        RestaurantSearchDomain restaurant2 = new RestaurantSearchDomain("id1", "Restaurant Name", "Address", "PhotoUrl", 4.5, "lat", "lng", 10, true);

        assertEquals(restaurant1.hashCode(), restaurant2.hashCode());
    }

    @Test
    public void testToString() {
        RestaurantSearchDomain restaurant = new RestaurantSearchDomain("id1", "Restaurant Name", "Address", "PhotoUrl", 4.5, "lat", "lng", 10, true);
        String expected = "RestaurantSearchDomain{placeId='id1', restaurantName='Restaurant Name', vicinity='Address', photoReferenceUrl='PhotoUrl', rating=4.5, latitude='lat', longitude='lng', distance=10, isOpen=true}";

        assertEquals(expected, restaurant.toString());
    }
}
