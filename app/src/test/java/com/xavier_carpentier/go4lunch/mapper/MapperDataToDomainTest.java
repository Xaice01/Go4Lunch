package com.xavier_carpentier.go4lunch.mapper;

import static com.xavier_carpentier.go4lunch.data.mappers.MapperDataToDomain.firebaseUserToUserDomain;

import android.location.Location;
import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;
import com.xavier_carpentier.go4lunch.data.mappers.MapperDataToDomain;
import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.Prediction;
import com.xavier_carpentier.go4lunch.data.response.autocomplete_response.StructuredFormatting;
import com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response.Photo;
import com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response.RestaurantDetailResponse;
import com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response.Result;
import com.xavier_carpentier.go4lunch.data.response.list_restaurant_response.Geometry;
import com.xavier_carpentier.go4lunch.data.response.list_restaurant_response.OpeningHours;
import com.xavier_carpentier.go4lunch.datasource.utils.AuthProviderType;
import com.xavier_carpentier.go4lunch.domain.model.AuthProviderTypeDomain;
import com.xavier_carpentier.go4lunch.domain.model.AutocompletePredictionDomain;
import com.xavier_carpentier.go4lunch.domain.model.LocationDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantSearchDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MapperDataToDomainTest {

    @Mock
    private Uri uriTest;
    private FirebaseUser mockFirebaseUser;
    private RestaurantDetailResponse mockRestaurantDetailResponse;
    private List<Prediction> mockPredictionList;
    private List<com.xavier_carpentier.go4lunch.data.response.list_restaurant_response.Result> mockListRestaurantResponse;
    private List<AuthProviderType> mockAuthProviderTypeList;
    private MapperDataToDomain mapper;

    @Before
    public void setUp() {
        mapper = new MapperDataToDomain();
        mockFirebaseUser = Mockito.mock(FirebaseUser.class);
        mockRestaurantDetailResponse = Mockito.mock(RestaurantDetailResponse.class);
        mockPredictionList = new ArrayList<>();
        mockListRestaurantResponse = new ArrayList<>();
        mockAuthProviderTypeList = new ArrayList<>();
        when(mockFirebaseUser.getUid()).thenReturn("123456789");
        when(mockFirebaseUser.getDisplayName()).thenReturn("testusername");

        when(mockFirebaseUser.getPhotoUrl()).thenReturn(uriTest);
    }

    @Test
    public void testFirebaseUserToUserDomain_withPhotoUrl() {
        when(mockFirebaseUser.getUid()).thenReturn("123456789");
        when(mockFirebaseUser.getDisplayName()).thenReturn("testusername");
        when(mockFirebaseUser.getPhotoUrl()).thenReturn(uriTest);
        when(uriTest.toString()).thenReturn("http://example.com/photo.jpg");

        UserDomain user = firebaseUserToUserDomain(mockFirebaseUser);

        assertNotNull(user);
        assertEquals("123456789", user.getUid());
        assertEquals("testusername", user.getUsername());
        assertEquals("http://example.com/photo.jpg", user.getUrlPicture());
    }

    @Test
    public void testFirebaseUserToUserDomain_withoutPhotoUrl() {
        when(mockFirebaseUser.getUid()).thenReturn("12345");
        when(mockFirebaseUser.getDisplayName()).thenReturn("Test User");
        when(mockFirebaseUser.getPhotoUrl()).thenReturn(null);

        UserDomain userDomain = MapperDataToDomain.firebaseUserToUserDomain(mockFirebaseUser);

        assertNotNull(userDomain);
        assertEquals("12345", userDomain.getUid());
        assertEquals("Test User", userDomain.getUsername());
        assertNull(userDomain.getUrlPicture());
    }

    @Test
    public void testListAuthProviderTypeToListAuthProviderTypeDomain() {
        mockAuthProviderTypeList.add(AuthProviderType.EMAIL);
        mockAuthProviderTypeList.add(AuthProviderType.GOOGLE);

        List<AuthProviderTypeDomain> result = MapperDataToDomain.ListAuthProviderTypeToListAuthProviderTypeDomain(mockAuthProviderTypeList);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(AuthProviderTypeDomain.EMAIL));
        assertTrue(result.contains(AuthProviderTypeDomain.GOOGLE));
    }

    @Test
    public void testRestaurantDetailResponseToRestaurantDomain_withValidData() {
        com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response.Result mockResult = Mockito.mock(com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response.Result.class);
        when(mockRestaurantDetailResponse.getResult()).thenReturn(mockResult);
        when(mockResult.getPlaceId()).thenReturn("123");
        when(mockResult.getName()).thenReturn("Test Restaurant");
        when(mockResult.getVicinity()).thenReturn("Test Vicinity");
        when(mockResult.getPhotos()).thenReturn(Arrays.asList(Mockito.mock(Photo.class)));
        when(mockResult.getPhotos().get(0).getPhotoURL()).thenReturn("http://example.com/photo.jpg");
        when(mockResult.getRating()).thenReturn(4.5);
        when(mockResult.getFormattedPhoneNumber()).thenReturn("123-456-7890");
        when(mockResult.getWebsite()).thenReturn("http://example.com");

        RestaurantDomain restaurantDomain = MapperDataToDomain.restaurantDetailResponseToRestaurantDomain(mockRestaurantDetailResponse);

        assertNotNull(restaurantDomain);
        assertEquals("123", restaurantDomain.getUidRestaurant());
        assertEquals("Test Restaurant", restaurantDomain.getRestaurantName());
        assertEquals("Test Vicinity", restaurantDomain.getVicinity());
        assertEquals("http://example.com/photo.jpg", restaurantDomain.getPhotoReferenceUrl());
        assertEquals(4.5, restaurantDomain.getRating(), 0);
        assertEquals("123-456-7890", restaurantDomain.getPhoneNumber());
        assertEquals("http://example.com", restaurantDomain.getWebsiteUrl());
    }

    @Test
    public void testRestaurantDetailResponseToRestaurantDomain_withNullResponse() {
        RestaurantDomain restaurantDomain = MapperDataToDomain.restaurantDetailResponseToRestaurantDomain(null);

        assertNull(restaurantDomain);
    }

    @Test
    public void testListPredictionToAutocompletePredictionDomain_withValidData() {
        Prediction mockPrediction = Mockito.mock(Prediction.class);
        when(mockPrediction.getPlaceId()).thenReturn("123");
        StructuredFormatting mockStructuredFormatting = Mockito.mock(StructuredFormatting.class);
        when(mockStructuredFormatting.getMainText()).thenReturn("Test Place");
        when(mockPrediction.getStructuredFormatting()).thenReturn(mockStructuredFormatting);

        mockPredictionList.add(mockPrediction);

        List<AutocompletePredictionDomain> result = MapperDataToDomain.listPredictionToAutocompletePredictionDomain(mockPredictionList);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("123", result.get(0).getRestaurantId());
        assertEquals("Test Place", result.get(0).getRestaurantName());
    }

    @Test
    public void testListPredictionToAutocompletePredictionDomain_withNullData() {
        mockPredictionList.add(Mockito.mock(Prediction.class));
        List<AutocompletePredictionDomain> result = MapperDataToDomain.listPredictionToAutocompletePredictionDomain(mockPredictionList);

        assertNull(result);
    }

    @Test
    public void testLocationToLocationDomain() {
        Location mockLocation = Mockito.mock(Location.class);
        when(mockLocation.getLatitude()).thenReturn(40.748817);
        when(mockLocation.getLongitude()).thenReturn(-73.985428);

        LocationDomain locationDomain = MapperDataToDomain.locationToLocationDomain(mockLocation);

        assertNotNull(locationDomain);
        assertEquals("40.748817", locationDomain.getLatitude());
        assertEquals("-73.985428", locationDomain.getLongitude());
    }
}