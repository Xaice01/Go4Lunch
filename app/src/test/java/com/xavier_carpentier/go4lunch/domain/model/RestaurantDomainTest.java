package com.xavier_carpentier.go4lunch.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class RestaurantDomainTest {

    @Test
    public void testRestaurantDomain() {
        RestaurantDomain restaurant = new RestaurantDomain("id1", "Restaurant Name", "Address", "Type", 4.5, "1234567890", "website.com");


        assertEquals("id1", restaurant.getUidRestaurant());
        assertEquals("Restaurant Name", restaurant.getRestaurantName());
        assertEquals("Address", restaurant.getVicinity());
        assertEquals("Type", restaurant.getPhotoReferenceUrl());
        assertEquals(4.5, restaurant.getRating(), 0);
        assertEquals("1234567890", restaurant.getPhoneNumber());
        assertEquals("website.com", restaurant.getWebsiteUrl());
    }

    @Test
    public void testEquals() {
        RestaurantDomain restaurant1 = new RestaurantDomain("id1", "Restaurant Name", "Address", "Type", 4.5, "1234567890", "website.com");
        RestaurantDomain restaurant2 = new RestaurantDomain("id1", "Restaurant Name", "Address", "Type", 4.5, "1234567890", "website.com");
        RestaurantDomain restaurant3 = new RestaurantDomain("id2", "Another Restaurant", "Another Address", "Another Type", 3.5, "0987654321", "anotherwebsite.com");

        assertEquals(restaurant1, restaurant2);
        assertNotEquals(restaurant1, restaurant3);
    }

    @Test
    public void testHashCode() {
        RestaurantDomain restaurant1 = new RestaurantDomain("id1", "Restaurant Name", "Address", "Type", 4.5, "1234567890", "website.com");
        RestaurantDomain restaurant2 = new RestaurantDomain("id1", "Restaurant Name", "Address", "Type", 4.5, "1234567890", "website.com");

        assertEquals(restaurant1.hashCode(), restaurant2.hashCode());
    }

    @Test
    public void testToString() {
        RestaurantDomain restaurant = new RestaurantDomain("id1", "Restaurant Name", "Address", "Type", 4.5, "1234567890", "website.com");
        String expected = "RestaurantDomain{placeId='id1', restaurantName='Restaurant Name', vicinity='Address', photoReferenceUrl='Type', rating=4.5, phoneNumber='1234567890', websiteUrl='website.com'}";

        assertEquals(expected, restaurant.toString());
    }
}
