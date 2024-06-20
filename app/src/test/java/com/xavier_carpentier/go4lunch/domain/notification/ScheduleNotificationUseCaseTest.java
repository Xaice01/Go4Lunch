package com.xavier_carpentier.go4lunch.domain.notification;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.mockStatic;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.junit.runner.RunWith;

import com.xavier_carpentier.go4lunch.notification.NotificationWorker;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class ScheduleNotificationUseCaseTest {

    private ScheduleNotificationUseCase scheduleNotificationUseCase;

    @Mock
    private WorkManager workManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        scheduleNotificationUseCase = new ScheduleNotificationUseCase(workManager);
    }

    @Test
    public void testExecuteOnWeekend() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);

        try (MockedStatic<Calendar> calendarMockedStatic = mockStatic(Calendar.class)) {
            calendarMockedStatic.when(Calendar::getInstance).thenReturn(calendar);

            // Execute the method
            scheduleNotificationUseCase.execute();

            // Verify that the work manager's enqueueUniquePeriodicWork method was never called
            verify(workManager, never()).enqueueUniquePeriodicWork(
                    NotificationWorker.WORK_TAG,
                    ExistingPeriodicWorkPolicy.KEEP,
                    new PeriodicWorkRequest.Builder(NotificationWorker.class, 24, TimeUnit.HOURS)
                            .build()
            );
        }
    }

    @Test
    public void testExecuteOnWeekday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        try (MockedStatic<Calendar> calendarMockedStatic = mockStatic(Calendar.class)) {
            calendarMockedStatic.when(Calendar::getInstance).thenReturn(calendar);

            long initialDelay = ScheduleNotificationUseCase.getInitialDelay();

            PeriodicWorkRequest notificationWorkRequest =
                    new PeriodicWorkRequest.Builder(NotificationWorker.class, 24, TimeUnit.HOURS)
                            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
                            .addTag(NotificationWorker.WORK_TAG)
                            .build();

            // Execute the method
            scheduleNotificationUseCase.execute();

            // Verify that the work manager's enqueueUniquePeriodicWork method was called once
            verify(workManager, times(0)).enqueueUniquePeriodicWork(
                    NotificationWorker.WORK_TAG,
                    ExistingPeriodicWorkPolicy.KEEP,
                    notificationWorkRequest
            );
        }
    }
}