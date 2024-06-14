package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.xavier_carpentier.go4lunch.data.repository.PlaceRepositoryRetrofit;
import com.xavier_carpentier.go4lunch.data.repository.UserRepositoryFirestore;
import com.xavier_carpentier.go4lunch.domain.usecase.CheckLocationPermissionUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetAutocompleteLiveDataUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetLocationUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetWorkmateUseCase;
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
    @Captor
    private ArgumentCaptor<Event<String>> eventCaptor;

    private MainViewModel mainViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(application.getApplicationContext()).thenReturn(application);

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
        Workmate workmate = new Workmate("testUid", "testName",  null, "UidRestaurant","testRestaurant");
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
    public void testSetLocation() {
        MutableLiveData<Boolean> permissionLiveData = new MutableLiveData<>();
        permissionLiveData.setValue(false);
        when(checkLocationPermissionUseCase.invoke()).thenReturn(permissionLiveData);

        mainViewModel.setLocation();

        assertEquals("50.62925", mainViewModel.latitude);
        assertEquals("3.057256", mainViewModel.longitude);
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
}
