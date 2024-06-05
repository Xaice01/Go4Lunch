package com.xavier_carpentier.go4lunch.utils;


import androidx.work.WorkManager;
import com.xavier_carpentier.go4lunch.domain.notification.ScheduleNotificationUseCase;

public class NotificationScheduler {

    private static ScheduleNotificationUseCase scheduleNotificationUseCase = null;

    public NotificationScheduler(WorkManager workManager) {
        scheduleNotificationUseCase = new ScheduleNotificationUseCase(workManager);
    }

    public static void scheduleDailyNotification() {
        scheduleNotificationUseCase.execute();
    }
}