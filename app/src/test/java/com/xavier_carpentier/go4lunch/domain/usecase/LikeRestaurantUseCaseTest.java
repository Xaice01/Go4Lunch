package com.xavier_carpentier.go4lunch.domain.usecase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.domain.repository.FavorisRestaurantRepository;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

import java.util.Arrays;
import java.util.List;

public class LikeRestaurantUseCaseTest {

    private LikeRestaurantUseCase likeRestaurantUseCase;

    @Mock
    private FavorisRestaurantRepository favorisRestaurantRepository;

    @Mock
    private AuthUserRepository userRepository;

    @Mock
    private UserDomain mockUser;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        likeRestaurantUseCase = new LikeRestaurantUseCase(favorisRestaurantRepository, userRepository);

        when(userRepository.getUser()).thenReturn(mockUser);
        when(mockUser.getUid()).thenReturn("testUserId");
    }

    @Test
    public void testAdd() {
        // Given
        String restaurantId = "restaurantId";
        MutableLiveData<Boolean> expectedLiveData = new MutableLiveData<>();
        expectedLiveData.setValue(true);

        when(favorisRestaurantRepository.addRestaurantFavoris("testUserId", restaurantId)).thenReturn(expectedLiveData);

        // When
        MutableLiveData<Boolean> actualLiveData = (MutableLiveData<Boolean>) likeRestaurantUseCase.add(restaurantId);

        // Then
        verify(favorisRestaurantRepository).addRestaurantFavoris("testUserId", restaurantId);
        assertEquals(expectedLiveData.getValue(), actualLiveData.getValue());
    }

    @Test
    public void testRemove() {
        // Given
        String restaurantId = "restaurantId";
        MutableLiveData<Boolean> expectedLiveData = new MutableLiveData<>();
        expectedLiveData.setValue(true);

        when(favorisRestaurantRepository.deleteRestaurantFavoris("testUserId", restaurantId)).thenReturn(expectedLiveData);

        // When
        MutableLiveData<Boolean> actualLiveData = (MutableLiveData<Boolean>) likeRestaurantUseCase.remove(restaurantId);

        // Then
        verify(favorisRestaurantRepository).deleteRestaurantFavoris("testUserId", restaurantId);
        assertEquals(expectedLiveData.getValue(), actualLiveData.getValue());
    }

    @Test
    public void testGetListRestaurant() {
        // Given
        MutableLiveData<List<String>> expectedLiveData = new MutableLiveData<>();
        expectedLiveData.setValue(Arrays.asList("restaurantId1", "restaurantId2"));

        when(favorisRestaurantRepository.getRestaurantFavorisByIdUser("testUserId")).thenReturn(expectedLiveData);

        // When
        MutableLiveData<List<String>> actualLiveData = (MutableLiveData<List<String>>) likeRestaurantUseCase.getListRestaurant();

        // Then
        verify(favorisRestaurantRepository).getRestaurantFavorisByIdUser("testUserId");
        assertEquals(expectedLiveData.getValue(), actualLiveData.getValue());
    }
}