package com.xavier_carpentier.go4lunch.data.repository;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE)
public class PermissionLocationRepositoryTest {

    @Mock
    private Context mockContext;

    private PermissionLocationRepository permissionLocationRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        permissionLocationRepository = new PermissionLocationRepository(mockContext);
    }

    @Test
    public void testCheckLocationPermissionGranted() {
        // Arrange
        when(ContextCompat.checkSelfPermission(mockContext, Manifest.permission.ACCESS_FINE_LOCATION))
                .thenReturn(PackageManager.PERMISSION_GRANTED);

        // Act
        LiveData<Boolean> result = permissionLocationRepository.checkLocationPermission();

        // Assert
        assertEquals(true, result.getValue());
    }

    @Test
    public void testCheckLocationPermissionDenied() {
        // Arrange
        when(ContextCompat.checkSelfPermission(mockContext, Manifest.permission.ACCESS_FINE_LOCATION))
                .thenReturn(PackageManager.PERMISSION_DENIED);

        // Act
        LiveData<Boolean> result = permissionLocationRepository.checkLocationPermission();

        // Assert
        assertEquals(false, result.getValue());
    }

    @Test
    public void testSetLocationPermissionGranted() {
        // Arrange
        when(ContextCompat.checkSelfPermission(mockContext, Manifest.permission.ACCESS_FINE_LOCATION))
                .thenReturn(PackageManager.PERMISSION_GRANTED);

        // Act
        permissionLocationRepository.setLocationPermission();

        // Assert
        MutableLiveData<Boolean> isPermissionLiveData = (MutableLiveData<Boolean>) permissionLocationRepository.checkLocationPermission();
        assertEquals(true, isPermissionLiveData.getValue());
    }

    @Test
    public void testSetLocationPermissionDenied() {
        // Arrange
        when(ContextCompat.checkSelfPermission(mockContext, Manifest.permission.ACCESS_FINE_LOCATION))
                .thenReturn(PackageManager.PERMISSION_DENIED);

        // Act
        permissionLocationRepository.setLocationPermission();

        // Assert
        MutableLiveData<Boolean> isPermissionLiveData = (MutableLiveData<Boolean>) permissionLocationRepository.checkLocationPermission();
        assertEquals(false, isPermissionLiveData.getValue());
    }
}
