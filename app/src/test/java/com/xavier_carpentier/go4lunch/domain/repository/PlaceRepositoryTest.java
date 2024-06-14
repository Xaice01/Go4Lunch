package com.xavier_carpentier.go4lunch.domain.repository;

import static org.junit.Assert.assertEquals;
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

import com.xavier_carpentier.go4lunch.domain.model.AutocompletePredictionDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantSearchDomain;

import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class PlaceRepositoryTest {

    private PlaceRepository placeRepository;

    @Mock
    private PlaceRepository mockPlaceRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        placeRepository = mockPlaceRepository;
    }

    @Test
    public void testGetAutocomplete() {
        // Given
        String input = "restaurant";
        String latitude = "48.8566";
        String longitude = "2.3522";
        String radius = "5000";
        String types = "restaurant";

        List<AutocompletePredictionDomain> expectedPredictions = new ArrayList<>();
        expectedPredictions.add(new AutocompletePredictionDomain("placeId1", "description1"));
        expectedPredictions.add(new AutocompletePredictionDomain("placeId2", "description2"));

        MutableLiveData<List<AutocompletePredictionDomain>> liveData = new MutableLiveData<>();
        liveData.setValue(expectedPredictions);

        when(placeRepository.getAutocomplete(input, latitude, longitude, radius, types)).thenReturn(liveData);

        // When
        LiveData<List<AutocompletePredictionDomain>> actualPredictions = placeRepository.getAutocomplete(input, latitude, longitude, radius, types);

        // Then
        assertEquals(expectedPredictions, actualPredictions.getValue());
    }

    @Test
    public void testGetListRestaurant() {
        // Given
        String latitude = "48.8566";
        String longitude = "2.3522";
        String radius = "5000";
        String types = "restaurant";

        List<RestaurantSearchDomain> expectedRestaurants = new ArrayList<>();
        expectedRestaurants.add(new RestaurantSearchDomain("id1", "name1", "address1", "photo1", 4.5, "latitude1", "longitude1", 100, true));
        expectedRestaurants.add(new RestaurantSearchDomain("id2", "name2", "address2", "photo2", 4.0, "latitude2", "longitude2", 200, true));

        MutableLiveData<List<RestaurantSearchDomain>> liveData = new MutableLiveData<>();
        liveData.setValue(expectedRestaurants);

        when(placeRepository.getListRestaurant(latitude, longitude, radius, types)).thenReturn(liveData);

        // When
        LiveData<List<RestaurantSearchDomain>> actualRestaurants = placeRepository.getListRestaurant(latitude, longitude, radius, types);

        // Then
        assertEquals(expectedRestaurants, actualRestaurants.getValue());
    }

    @Test
    public void testGetRestaurant() {
        // Given
        String uidRestaurant = "id1";
        RestaurantDomain expectedRestaurant = new RestaurantDomain("id1", "name1", "address1", "type1", 4.5, "0789989898", "website");

        MutableLiveData<RestaurantDomain> liveData = new MutableLiveData<>();
        liveData.setValue(expectedRestaurant);

        when(placeRepository.getRestaurant(uidRestaurant)).thenReturn(liveData);

        // When
        LiveData<RestaurantDomain> actualRestaurant = placeRepository.getRestaurant(uidRestaurant);

        // Then
        assertEquals(expectedRestaurant, actualRestaurant.getValue());
    }
}