package com.xavier_carpentier.go4lunch.domain.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.junit.runner.RunWith;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class PermissionLocationRepositoryTest {

    private PermissionLocationRepository permissionLocationRepository;

    @Mock
    private PermissionLocationRepository mockPermissionLocationRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        permissionLocationRepository = mockPermissionLocationRepository;
    }

    @Test
    public void testCheckLocationPermission() {
        // Given
        MutableLiveData<Boolean> expectedLiveData = new MutableLiveData<>();
        expectedLiveData.setValue(true);

        when(permissionLocationRepository.checkLocationPermission()).thenReturn(expectedLiveData);

        // When
        LiveData<Boolean> actualLiveData = permissionLocationRepository.checkLocationPermission();

        // Then
        verify(mockPermissionLocationRepository).checkLocationPermission();
        assertEquals(expectedLiveData.getValue(), actualLiveData.getValue());
    }
}