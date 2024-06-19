package com.xavier_carpentier.go4lunch.domain.usecase;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xavier_carpentier.go4lunch.domain.model.RestaurantDomain;
import com.xavier_carpentier.go4lunch.domain.repository.PlaceRepository;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantDetail;

import java.util.Collections;
import java.util.List;

public class GetRestaurantByIdUseCaseTest {

    private GetRestaurantByIdUseCase getRestaurantByIdUseCase;

    @Mock
    private PlaceRepository placeRepository;

    @Mock
    private LikeRestaurantUseCase likeRestaurantUseCase;
    @Mock
    private Observer<RestaurantDetail> observer;


    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getRestaurantByIdUseCase = new GetRestaurantByIdUseCase(placeRepository, likeRestaurantUseCase);
    }

    @Test
    public void testInvoke() {
        // Given
        String restaurantUid = "restaurantUid";
        MutableLiveData<List<String>> likedRestaurantLiveData = new MutableLiveData<>();
        likedRestaurantLiveData.setValue(Collections.singletonList("restaurantUid"));

        MutableLiveData<RestaurantDomain> restaurantDetailLiveData = new MutableLiveData<>();
        RestaurantDomain restaurantDomain = new RestaurantDomain("restaurantUid", "type", "name", "address", 4.0, "phone", "website");
        restaurantDetailLiveData.setValue(restaurantDomain);

        when(likeRestaurantUseCase.getListRestaurant()).thenReturn(likedRestaurantLiveData);
        when(placeRepository.getRestaurant(restaurantUid)).thenReturn(restaurantDetailLiveData);

        // When
        getRestaurantByIdUseCase.invoke(restaurantUid);

        // Then
        verify(likeRestaurantUseCase).getListRestaurant();
        verify(placeRepository).getRestaurant(restaurantUid);

    }
    @Test
    public void testUpdateRestaurantLikeStatus_Liked() {
        // Given
        RestaurantDetail restaurant = new RestaurantDetail("restaurantUid", "pictureUrl", "name", "address", 4.0, "phone", false, "website");
        List<String> favoris = Collections.singletonList("restaurantUid");

        // When
        getRestaurantByIdUseCase.updateRestaurantLikeStatus(restaurant, favoris);

        // Then
        assertEquals(true, restaurant.isLike());
    }

    @Test
    public void testUpdateRestaurantLikeStatus_NotLiked() {
        // Given
        RestaurantDetail restaurant = new RestaurantDetail("restaurantUid", "pictureUrl", "name", "address", 4.0, "phone", false, "website");
        List<String> favoris = Collections.singletonList("anotherRestaurantUid");

        // When
        getRestaurantByIdUseCase.updateRestaurantLikeStatus(restaurant, favoris);

        // Then
        assertEquals(false, restaurant.isLike());
    }

    @Test
    public void testUpdateRestaurantLikeStatus_NullRestaurant() {
        // Given
        RestaurantDetail restaurant = null;
        List<String> favoris = Collections.singletonList("restaurantUid");

        // When
        getRestaurantByIdUseCase.updateRestaurantLikeStatus(restaurant, favoris);

        // Then
        verify(observer, never()).onChanged(null);
    }

    @Test
    public void testUpdateRestaurantLikeStatus_NullFavoris() {
        // Given
        RestaurantDetail restaurant = new RestaurantDetail("restaurantUid", "pictureUrl", "name", "address", 4.0, "phone", false, "website");
        List<String> favoris = null;

        // When
        getRestaurantByIdUseCase.updateRestaurantLikeStatus(restaurant, favoris);

        // Then
        verify(observer, never()).onChanged(restaurant);
    }
}