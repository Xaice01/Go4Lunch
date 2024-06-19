package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.xavier_carpentier.go4lunch.data.repository.PlaceRepositoryRetrofit;
import com.xavier_carpentier.go4lunch.data.repository.UserRepositoryFirestore;
import com.xavier_carpentier.go4lunch.domain.notification.ScheduleNotificationUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.CheckLocationPermissionUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetAutocompleteLiveDataUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetLocationUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetWorkmateUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.AutocompletePrediction;
import com.xavier_carpentier.go4lunch.presentation.model.LocationUi;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;
import com.xavier_carpentier.go4lunch.presentation.ui.utils.Event;
import com.xavier_carpentier.go4lunch.utils.NotificationScheduler;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private Application application;
    @Mock
    private PlaceRepositoryRetrofit placeRepositoryRetrofit;
    @Mock
    private UserRepositoryFirestore userRepositoryFirestore;
    @Mock
    private NotificationScheduler notificationScheduler;
    @Mock
    private GetAutocompleteLiveDataUseCase getAutocompleteLiveDataUseCase;
    @Mock
    private GetWorkmateUseCase getWorkmateUseCase;
    @Mock
    private CheckLocationPermissionUseCase checkLocationPermissionUseCase;
    @Mock
    private GetLocationUseCase getLocationUseCase;
    @Mock
    private ScheduleNotificationUseCase scheduleNotificationUseCase;
    @Captor
    private ArgumentCaptor<Event<String>> eventCaptor;

    private MainViewModel mainViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(application.getApplicationContext()).thenReturn(application);

        notificationScheduler = new NotificationScheduler(scheduleNotificationUseCase);

        mainViewModel = new MainViewModel(
                application,
                placeRepositoryRetrofit,
                userRepositoryFirestore,
                getAutocompleteLiveDataUseCase,
                getWorkmateUseCase,
                checkLocationPermissionUseCase,
                getLocationUseCase,
                notificationScheduler
        );
    }

    @Test
    public void testCheckWorkmateRestaurant() {
        MutableLiveData<Workmate> workmateLiveData = new MutableLiveData<>();
        Workmate workmate = new Workmate("testUid", "testName", null, "UidRestaurant", "testRestaurant");
        workmateLiveData.setValue(workmate);

        when(getWorkmateUseCase.invoke(anyString())).thenReturn(workmateLiveData);

        Observer<Event<String>> observer = mock(Observer.class);
        mainViewModel.getNavigateToDetailEvent().observeForever(observer);

        mainViewModel.setUserUid("testUid");
        mainViewModel.checkWorkmateRestaurant();

        verify(observer).onChanged(eventCaptor.capture());
        assertEquals("UidRestaurant", eventCaptor.getValue().getContentIfNotHandled());
    }

    @Test
    public void testSetLocation_WithPermission() {
        MutableLiveData<Boolean> permissionLiveData = new MutableLiveData<>();
        permissionLiveData.setValue(true);
        when(checkLocationPermissionUseCase.invoke()).thenReturn(permissionLiveData);

        mainViewModel.setLocation();

        verify(getLocationUseCase).startLocationUpdates();
    }

    @Test
    public void testSetLocation_WithoutPermission() {
        MutableLiveData<Boolean> permissionLiveData = new MutableLiveData<>();
        permissionLiveData.setValue(false);
        when(checkLocationPermissionUseCase.invoke()).thenReturn(permissionLiveData);

        mainViewModel.setLocation();

        assertEquals("50.62925", mainViewModel.latitude);
        assertEquals("3.057256", mainViewModel.longitude);
    }

    @Test
    public void testCheckPermissionLocation() {
        MutableLiveData<Boolean> permissionLiveData = new MutableLiveData<>();
        when(checkLocationPermissionUseCase.invoke()).thenReturn(permissionLiveData);

        LiveData<Boolean> result = mainViewModel.checkPermissionLocation();

        assertEquals(permissionLiveData.getValue(), result.getValue());
    }

    @Test
    public void testOnSomeActionThatRequiresPermission() {
        Observer<Boolean> observer = mock(Observer.class);
        mainViewModel.checkPermissionLocation().observeForever(observer);

        mainViewModel.onSomeActionThatRequiresPermission();

        verify(observer).onChanged(true);
    }

    @Test
    public void testPermissionHandled() {
        Observer<Boolean> observer = mock(Observer.class);
        mainViewModel.checkPermissionLocation().observeForever(observer);

        mainViewModel.permissionHandled();

        verify(observer).onChanged(false);
    }

    @Test
    public void testLiveDataLocationUiWithQuery() {
        MutableLiveData<LocationUi> locationLiveData = new MutableLiveData<>();
        LocationUi locationUi = new LocationUi("50.62925", "3.057256");
        locationLiveData.setValue(locationUi);

        when(getLocationUseCase.invoke()).thenReturn(locationLiveData);

        LiveData<LocationUi> locationUiLiveData = Transformations.switchMap(
                mainViewModel.userQueryMutableLiveData, userQuery -> {
                    if (userQuery == null || userQuery.isEmpty() || userQuery.length() < 3) {
                        return new MutableLiveData<>();
                    } else {
                        return getLocationUseCase.invoke();
                    }
                });

        Observer<LocationUi> observer = mock(Observer.class);
        locationUiLiveData.observeForever(observer);

        mainViewModel.onQueryChanged("test");

        verify(getLocationUseCase).invoke();
    }

    @Test
    public void testLiveDataLocationUiWithQueryEmpty() {
        MutableLiveData<LocationUi> locationLiveData = new MutableLiveData<>();
        LocationUi locationUi = new LocationUi("50.62925", "3.057256");
        locationLiveData.setValue(locationUi);

        when(getLocationUseCase.invoke()).thenReturn(locationLiveData);

        LiveData<LocationUi> locationUiLiveData = Transformations.switchMap(
                mainViewModel.userQueryMutableLiveData, userQuery -> {
                    if (userQuery == null || userQuery.isEmpty() || userQuery.length() < 3) {
                        return new MutableLiveData<>();
                    } else {
                        return getLocationUseCase.invoke();
                    }
                });

        Observer<LocationUi> observer = mock(Observer.class);
        locationUiLiveData.observeForever(observer);

        mainViewModel.onQueryChanged("");

        verifyNoInteractions(getLocationUseCase);
    }

    @Test
    public void testPredictionsLiveDataWithLocation() {
        MutableLiveData<LocationUi> locationLiveData = new MutableLiveData<>();
        LocationUi locationUi = new LocationUi("50.62925", "3.057256");

        MutableLiveData<List<AutocompletePrediction>> predictionsLiveData = new MutableLiveData<>();
        List<AutocompletePrediction> predictions = new ArrayList<>();
        predictions.add(new AutocompletePrediction("id1", "name1"));
        predictions.add(new AutocompletePrediction("id2", "name2"));
        predictionsLiveData.setValue(predictions);

        when(getLocationUseCase.invoke()).thenReturn(locationLiveData);
        when(getAutocompleteLiveDataUseCase.invoke(anyString(), anyString(), anyString())).thenReturn(predictionsLiveData);

        Observer<List<AutocompletePrediction>> observer = mock(Observer.class);
        mainViewModel.getLiveDataPrediction().observeForever(observer);

        mainViewModel.onQueryChanged("test");

        // Ensure locationLiveData triggers the update
        locationLiveData.setValue(locationUi);

        verify(getAutocompleteLiveDataUseCase).invoke("test", "50.62925", "3.057256");

    }

    @Test
    public void testPredictionsLiveDataWithLocationNull() {
        MutableLiveData<LocationUi> locationLiveData = new MutableLiveData<>();

        MutableLiveData<List<AutocompletePrediction>> predictionsLiveData = new MutableLiveData<>();
        List<AutocompletePrediction> predictions = new ArrayList<>();
        predictions.add(new AutocompletePrediction("id1", "name1"));
        predictions.add(new AutocompletePrediction("id2", "name2"));
        predictionsLiveData.setValue(predictions);

        when(getLocationUseCase.invoke()).thenReturn(locationLiveData);
        when(getAutocompleteLiveDataUseCase.invoke(anyString(), anyString(), anyString())).thenReturn(predictionsLiveData);

        Observer<List<AutocompletePrediction>> observer = mock(Observer.class);
        mainViewModel.getLiveDataPrediction().observeForever(observer);

        mainViewModel.onQueryChanged("test");

        locationLiveData.setValue(null);

        verifyNoInteractions(getAutocompleteLiveDataUseCase);
    }


    @Test
    public void testOnQueryChanged() {
        String query = "testQuery";
        mainViewModel.onQueryChanged(query);
        assertEquals(query, mainViewModel.userQueryMutableLiveData.getValue());
    }

    @Test
    public void testOnPredictionPlaceIdReset() {
        mainViewModel.onPredictionPlaceIdReset();
        assertNull(mainViewModel.userQueryMutableLiveData.getValue());
    }

    @Test
    public void testOnCleared() {
        mainViewModel.onCleared();
        verify(getLocationUseCase).stopLocationUpdates();
    }

    @Test
    public void testSetUserUid() {
        String uid = "testUid";
        mainViewModel.setUserUid(uid);
        assertEquals(uid, mainViewModel.userUidLiveData.getValue());
    }

    @Test
    public void testScheduleDailyNotification() {
        mainViewModel.scheduleDailyNotification();
        verify(scheduleNotificationUseCase).execute();
    }
}