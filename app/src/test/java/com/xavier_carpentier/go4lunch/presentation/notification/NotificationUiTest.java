package com.xavier_carpentier.go4lunch.presentation.notification;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NotificationUiTest {

    private NotificationUi notificationUi;
    private final String nameWorkmate = "John Doe";
    private final List<String> workmateToEatInThisRestaurant = Arrays.asList("Alice", "Bob");
    private final String idRestaurant = "restaurant123";
    private final String restaurantName = "Best Restaurant";
    private final String restaurantVicinity = "123 Main St";

    @Before
    public void setUp() {
        notificationUi = new NotificationUi(nameWorkmate, workmateToEatInThisRestaurant, idRestaurant, restaurantName, restaurantVicinity);
    }

    @Test
    public void testGetNameWorkmate() {
        assertEquals(nameWorkmate, notificationUi.getNameWorkmate());
    }

    @Test
    public void testGetWorkmateToEatInThisRestaurant() {
        assertEquals(workmateToEatInThisRestaurant, notificationUi.getWorkmateToEatInThisRestaurant());
    }

    @Test
    public void testGetIdRestaurant() {
        assertEquals(idRestaurant, notificationUi.getIdRestaurant());
    }

    @Test
    public void testGetRestaurantName() {
        assertEquals(restaurantName, notificationUi.getRestaurantName());
    }

    @Test
    public void testGetRestaurantVicinity() {
        assertEquals(restaurantVicinity, notificationUi.getRestaurantVicinity());
    }
}