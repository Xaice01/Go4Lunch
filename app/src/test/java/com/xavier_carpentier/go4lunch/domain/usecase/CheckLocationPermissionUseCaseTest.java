package com.xavier_carpentier.go4lunch.domain.usecase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.junit.runner.RunWith;

import com.xavier_carpentier.go4lunch.domain.repository.PermissionLocationRepository;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class CheckLocationPermissionUseCaseTest {

    private CheckLocationPermissionUseCase checkLocationPermissionUseCase;

    @Mock
    private PermissionLocationRepository permissionLocationRepository;

    @Mock
    private Observer<Boolean> observer;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        checkLocationPermissionUseCase = new CheckLocationPermissionUseCase(permissionLocationRepository);
    }

    @Test
    public void testInvoke() {
        // Given
        MutableLiveData<Boolean> permissionLiveData = new MutableLiveData<>();
        permissionLiveData.setValue(true);

        when(permissionLocationRepository.checkLocationPermission()).thenReturn(permissionLiveData);

        // When
        LiveData<Boolean> result = checkLocationPermissionUseCase.invoke();
        result.observeForever(observer);

        // Then
        verify(permissionLocationRepository).checkLocationPermission();
        verify(observer).onChanged(true);
        assertEquals(true, result.getValue());
    }
}