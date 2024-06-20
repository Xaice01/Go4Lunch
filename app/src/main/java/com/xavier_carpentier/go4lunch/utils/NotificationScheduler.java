package com.xavier_carpentier.go4lunch.utils;


import androidx.work.WorkManager;
import com.xavier_carpentier.go4lunch.domain.notification.ScheduleNotificationUseCase;
@SuppressWarnings("InstantiationUtilityClass")
public class NotificationScheduler {

    @SuppressWarnings("InstantiationUtilityClass")
    private static ScheduleNotificationUseCase scheduleNotificationUseCase = null;

    @SuppressWarnings("InstantiationUtilityClass")
    public NotificationScheduler(WorkManager workManager) {
        scheduleNotificationUseCase = new ScheduleNotificationUseCase(workManager);
    }
    //for test
    public NotificationScheduler(ScheduleNotificationUseCase scheduleNotificationUseCase) {
        this.scheduleNotificationUseCase = scheduleNotificationUseCase;
    }

    public static void scheduleDailyNotification() {
        scheduleNotificationUseCase.execute();
    }
}