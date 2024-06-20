package com.xavier_carpentier.go4lunch.domain.notification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class NotificationDomainTest {

    private NotificationDomain notification1;
    private NotificationDomain notification2;
    private NotificationDomain notification3;

    @Before
    public void setUp() {
        notification1 = new NotificationDomain(
                "John Doe",
                Arrays.asList("Jane Smith", "Jack White"),
                "restaurantId1",
                "Restaurant Name 1",
                "123 Main St"
        );

        notification2 = new NotificationDomain(
                "John Doe",
                Arrays.asList("Jane Smith", "Jack White"),
                "restaurantId1",
                "Restaurant Name 1",
                "123 Main St"
        );

        notification3 = new NotificationDomain(
                "Alice Brown",
                Collections.singletonList("Bob Green"),
                "restaurantId2",
                "Restaurant Name 2",
                "456 Second St"
        );
    }

    @Test
    public void testGetNameWorkmate() {
        assertEquals("John Doe", notification1.getNameWorkmate());
    }

    @Test
    public void testGetWorkmateToEatInThisRestaurant() {
        assertEquals(Arrays.asList("Jane Smith", "Jack White"), notification1.getWorkmateToEatInThisRestaurant());
    }

    @Test
    public void testGetIdRestaurant() {
        assertEquals("restaurantId1", notification1.getIdRestaurant());
    }

    @Test
    public void testGetRestaurantName() {
        assertEquals("Restaurant Name 1", notification1.getRestaurantName());
    }

    @Test
    public void testGetRestaurantVicinity() {
        assertEquals("123 Main St", notification1.getRestaurantVicinity());
    }

    @Test
    public void testEquals() {
        assertEquals(notification1, notification2);
        assertNotEquals(notification1, notification3);
    }

    @Test
    public void testHashCode() {
        assertEquals(notification1.hashCode(), notification2.hashCode());
        assertNotEquals(notification1.hashCode(), notification3.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "NotificationDomain{nameWorkmate='John Doe', workmateToEatInThisRestaurant=[Jane Smith, Jack White], idRestaurant='restaurantId1', restaurantName='Restaurant Name 1', restaurantVicinity='123 Main St'}";
        assertEquals(expectedString, notification1.toString());
    }
}
