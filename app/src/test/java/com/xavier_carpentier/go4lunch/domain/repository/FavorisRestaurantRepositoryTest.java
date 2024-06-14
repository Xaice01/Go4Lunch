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

import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class FavorisRestaurantRepositoryTest {

    private FavorisRestaurantRepository favorisRestaurantRepository;

    @Mock
    private FavorisRestaurantRepository mockFavorisRestaurantRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        favorisRestaurantRepository = mockFavorisRestaurantRepository;
    }

    @Test
    public void testGetRestaurantFavorisByIdUser() {
        // Given
        String userId = "userId";
        List<String> expectedFavoris = new ArrayList<>();
        expectedFavoris.add("restaurantId1");
        expectedFavoris.add("restaurantId2");

        MutableLiveData<List<String>> liveData = new MutableLiveData<>();
        liveData.setValue(expectedFavoris);

        when(favorisRestaurantRepository.getRestaurantFavorisByIdUser(userId)).thenReturn(liveData);

        // When
        LiveData<List<String>> actualFavoris = favorisRestaurantRepository.getRestaurantFavorisByIdUser(userId);

        // Then
        verify(mockFavorisRestaurantRepository).getRestaurantFavorisByIdUser(userId);
        assertEquals(expectedFavoris, actualFavoris.getValue());
    }

    @Test
    public void testAddRestaurantFavoris() {
        // Given
        String userId = "userId";
        String restaurantId = "restaurantId";

        MutableLiveData<Boolean> expectedLiveData = new MutableLiveData<>();
        expectedLiveData.setValue(true);

        when(favorisRestaurantRepository.addRestaurantFavoris(userId, restaurantId)).thenReturn(expectedLiveData);

        // When
        LiveData<Boolean> actualLiveData = favorisRestaurantRepository.addRestaurantFavoris(userId, restaurantId);

        // Then
        verify(mockFavorisRestaurantRepository).addRestaurantFavoris(userId, restaurantId);
        assertEquals(true, actualLiveData.getValue());
    }

    @Test
    public void testDeleteRestaurantFavoris() {
        // Given
        String userId = "userId";
        String restaurantId = "restaurantId";

        MutableLiveData<Boolean> expectedLiveData = new MutableLiveData<>();
        expectedLiveData.setValue(true);

        when(favorisRestaurantRepository.deleteRestaurantFavoris(userId, restaurantId)).thenReturn(expectedLiveData);

        // When
        LiveData<Boolean> actualLiveData = favorisRestaurantRepository.deleteRestaurantFavoris(userId, restaurantId);

        // Then
        verify(mockFavorisRestaurantRepository).deleteRestaurantFavoris(userId, restaurantId);
        assertEquals(true, actualLiveData.getValue());
    }
}