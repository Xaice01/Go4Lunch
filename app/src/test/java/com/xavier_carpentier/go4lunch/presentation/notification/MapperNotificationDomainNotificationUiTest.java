package com.xavier_carpentier.go4lunch.presentation.notification;

import com.xavier_carpentier.go4lunch.domain.notification.NotificationDomain;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;

public class MapperNotificationDomainNotificationUiTest {

    @Test
    public void testNotificationDomainToNotificationUi() {
        // Given
        NotificationDomain notificationDomain = new NotificationDomain(
                "idRestaurant",
                Collections.singletonList("nameWorkmate"),
                "restaurantName",
                "restaurantVicinity",
                "workmateToEatInThisRestaurant"
        );

        // When
        NotificationUi notificationUi = MapperNotificationDomainNotificationUi.notificationDomainToNotificationUi(notificationDomain);

        // Then
        assertNotNull(notificationUi);
        assertEquals(notificationDomain.getNameWorkmate(), notificationUi.getNameWorkmate());
        assertEquals(notificationDomain.getWorkmateToEatInThisRestaurant(), notificationUi.getWorkmateToEatInThisRestaurant());
        assertEquals(notificationDomain.getIdRestaurant(), notificationUi.getIdRestaurant());
        assertEquals(notificationDomain.getRestaurantName(), notificationUi.getRestaurantName());
        assertEquals(notificationDomain.getRestaurantVicinity(), notificationUi.getRestaurantVicinity());
    }
}