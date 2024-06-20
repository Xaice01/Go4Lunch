package com.xavier_carpentier.go4lunch.domain.notification;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.xavier_carpentier.go4lunch.notification.NotificationWorker;

public class ScheduleNotificationUseCase {

    private final WorkManager workManager;

    public ScheduleNotificationUseCase(WorkManager workManager) {
        this.workManager = workManager;
    }

    public void execute() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
            return; // do not notified
        }

        long initialDelay = getInitialDelay();

        PeriodicWorkRequest notificationWorkRequest =
                new PeriodicWorkRequest.Builder(NotificationWorker.class, 24, TimeUnit.HOURS)
                        .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
                        .addTag(NotificationWorker.WORK_TAG)
                        .build();

        workManager.enqueueUniquePeriodicWork(
                NotificationWorker.WORK_TAG,
                ExistingPeriodicWorkPolicy.KEEP,
                notificationWorkRequest
        );
    }

    public static long getInitialDelay() {
        Calendar currentTime = Calendar.getInstance();
        Calendar nextRunTime = (Calendar) currentTime.clone();
        nextRunTime.set(Calendar.HOUR_OF_DAY, 12);
        nextRunTime.set(Calendar.MINUTE, 0);
        nextRunTime.set(Calendar.SECOND, 0);
        nextRunTime.set(Calendar.MILLISECOND, 0);

        if (currentTime.after(nextRunTime)) {
            nextRunTime.add(Calendar.DAY_OF_MONTH, 1);
        }

        return nextRunTime.getTimeInMillis() - currentTime.getTimeInMillis();
    }
}