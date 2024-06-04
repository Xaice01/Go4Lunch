package com.xavier_carpentier.go4lunch.presentation.notification;

import com.xavier_carpentier.go4lunch.domain.notification.NotificationDomain;

public class MapperNotificationDomainNotificationUi {

    public static NotificationUi notificationDomainToNotificationUi(NotificationDomain notificationDomain){
        return new NotificationUi(notificationDomain.getNameWorkmate(),notificationDomain.getWorkmateToEatInThisRestaurant(),notificationDomain.getIdRestaurant(),notificationDomain.getRestaurantName(), notificationDomain.getRestaurantVicinity());
    }
}
