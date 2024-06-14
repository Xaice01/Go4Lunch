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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.junit.runner.RunWith;

import com.xavier_carpentier.go4lunch.domain.model.RestaurantChoiceDomain;
import com.xavier_carpentier.go4lunch.domain.repository.PlaceRepository;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantItem;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantSearchDomain;

import java.util.Arrays;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class GetListRestaurantsUseCaseTest {

    private GetListRestaurantsUseCase getListRestaurantsUseCase;

    @Mock
    private PlaceRepository placeRepository;

    @Mock
    private UsersRepository userRepository;

    @Mock
    private Observer<List<RestaurantItem>> observer;

    @Captor
    private ArgumentCaptor<List<RestaurantItem>> captor;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getListRestaurantsUseCase = new GetListRestaurantsUseCase(placeRepository, userRepository);
    }

    @Test
    public void testInvoke() {
        // Given
        String userLatitude = "10.0";
        String userLongitude = "20.0";
        MutableLiveData<List<RestaurantSearchDomain>> restaurantSearchDomainsLiveData = new MutableLiveData<>();
        RestaurantSearchDomain restaurant1 = new RestaurantSearchDomain("id1", "name1", "address1", null, 0.0, "50", "50", 0, true);
        RestaurantSearchDomain restaurant2 = new RestaurantSearchDomain("id2", "name2", "address2", null, 0.0, "50", "50", 0, true);
        restaurantSearchDomainsLiveData.setValue(Arrays.asList(restaurant1, restaurant2));

        List<RestaurantItem> restaurantItems = Arrays.asList(
                new RestaurantItem("id1", "name1", "address1", 0, 0, "50", "50", 0, true, null),
                new RestaurantItem("id2", "name2", "address2", 0, 0, "50", "50", 0, null, null)
        );


        for (RestaurantItem restaurantItem : restaurantItems) {
            MutableLiveData<List<RestaurantChoiceDomain>> RestaurantChoiceDomainLiveData = new MutableLiveData<>();
            RestaurantChoiceDomainLiveData.setValue(Arrays.asList(
                    new RestaurantChoiceDomain(),
                    new RestaurantChoiceDomain()
            ));
            when(userRepository.getListWorkmateToChoiceARestaurant(restaurantItem.getUid())).thenReturn(RestaurantChoiceDomainLiveData);
        }

        when(placeRepository.getListRestaurant(userLatitude, userLongitude, "1000", "restaurant")).thenReturn(restaurantSearchDomainsLiveData);

        // When
        LiveData<List<RestaurantItem>> result = getListRestaurantsUseCase.invoke(userLatitude, userLongitude);
        result.observeForever(observer);

        // Then
        verify(observer).onChanged(captor.capture());
        assertEquals(restaurantItems.get(0).getUid(), captor.getValue().get(0).getUid());
        assertEquals(2, captor.getValue().get(0).getWorkmatesToEat());
    }
}