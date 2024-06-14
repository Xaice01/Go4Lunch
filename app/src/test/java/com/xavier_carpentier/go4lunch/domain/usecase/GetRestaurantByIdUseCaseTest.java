package com.xavier_carpentier.go4lunch.domain.usecase;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xavier_carpentier.go4lunch.domain.model.RestaurantDomain;
import com.xavier_carpentier.go4lunch.domain.repository.PlaceRepository;

import java.util.Collections;
import java.util.List;

public class GetRestaurantByIdUseCaseTest {

    private GetRestaurantByIdUseCase getRestaurantByIdUseCase;

    @Mock
    private PlaceRepository placeRepository;

    @Mock
    private LikeRestaurantUseCase likeRestaurantUseCase;


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
}