package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;
import com.xavier_carpentier.go4lunch.data.repository.FavorisRestaurantRepositoryFirestore;
import com.xavier_carpentier.go4lunch.data.repository.PlaceRepositoryRetrofit;
import com.xavier_carpentier.go4lunch.data.repository.UserRepositoryFirestore;
import com.xavier_carpentier.go4lunch.domain.usecase.AddRestaurantChoiceToDayUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.DeleteRestaurantChoiceToDayUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetIfWorkmateEatInThisRestaurantUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetListWorkmateToEatByIdRestaurantUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetRestaurantByIdUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.LikeRestaurantUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantDetail;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DetailRestaurantViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private AuthRepositoryFirebase authRepositoryFirebase;
    @Mock
    private PlaceRepositoryRetrofit placeRepositoryRetrofit;
    @Mock
    private UserRepositoryFirestore userRepositoryFirestore;
    @Mock
    private FavorisRestaurantRepositoryFirestore favorisRestaurantRepositoryFirestore;
    @Mock
    private AddRestaurantChoiceToDayUseCase addRestaurantChoiceToDayUseCase;
    @Mock
    private DeleteRestaurantChoiceToDayUseCase deleteRestaurantChoiceToDayUseCase;
    @Mock
    private GetIfWorkmateEatInThisRestaurantUseCase getIfWorkmateEatInThisRestaurantUseCase;
    @Mock
    private GetListWorkmateToEatByIdRestaurantUseCase getListWorkmateByIdRestaurantUseCase;
    @Mock
    private LikeRestaurantUseCase likeRestaurantUseCase;
    @Mock
    private GetRestaurantByIdUseCase getRestaurantByIdUseCase;

    private DetailRestaurantViewModel viewModel;
    private MutableLiveData<RestaurantDetail> restaurantDetailLiveData;
    private MutableLiveData<List<Workmate>> workmatesLiveData;
    private MutableLiveData<Boolean> isLikeLiveData;
    private MutableLiveData<Boolean> eatHereLiveData;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        restaurantDetailLiveData = new MutableLiveData<>();
        workmatesLiveData = new MutableLiveData<>();
        isLikeLiveData = new MutableLiveData<>();
        eatHereLiveData = new MutableLiveData<>();

        when(getRestaurantByIdUseCase.invoke(anyString())).thenReturn(restaurantDetailLiveData);
        when(getListWorkmateByIdRestaurantUseCase.invoke(anyString())).thenReturn(workmatesLiveData);
        when(getIfWorkmateEatInThisRestaurantUseCase.invoke(anyString())).thenReturn(eatHereLiveData);

        viewModel = new DetailRestaurantViewModel(authRepositoryFirebase, placeRepositoryRetrofit, userRepositoryFirestore, favorisRestaurantRepositoryFirestore, addRestaurantChoiceToDayUseCase, deleteRestaurantChoiceToDayUseCase, getIfWorkmateEatInThisRestaurantUseCase, getListWorkmateByIdRestaurantUseCase, likeRestaurantUseCase, getRestaurantByIdUseCase);
    }

    @Test
    public void testInitRestaurant() {
        // Given
        RestaurantDetail mockDetail = new RestaurantDetail("1", "pic_url", "Test Restaurant", "Test Address", 4.0, "1234567890", false, "www.test.com");
        restaurantDetailLiveData.setValue(mockDetail);

        // When
        viewModel.initRestaurant("1");

        // Then
        LiveData<RestaurantDetail> result = viewModel.getRestaurantDetail();
        assertNotNull(result);
        assertEquals(mockDetail, result.getValue());
    }

    @Test
    public void testOnLikeClick() {
        // Given
        RestaurantDetail mockDetail = new RestaurantDetail("1", "pic_url", "Test Restaurant", "Test Address", 4.0, "1234567890", false, "www.test.com");
        restaurantDetailLiveData.setValue(mockDetail);
        viewModel.initRestaurant("1");


        MutableLiveData<Boolean> likeResultLiveData = new MutableLiveData<>();
        likeResultLiveData.setValue(true);

        when(likeRestaurantUseCase.add(anyString())).thenReturn(likeResultLiveData);
        when(likeRestaurantUseCase.remove(anyString())).thenReturn(likeResultLiveData);

        // When
        viewModel.OnLikeCLick();

        // Then
        verify(likeRestaurantUseCase).add("1");
    }

    @Test
    public void testOnLikeClickRemove() {
        // Given
        RestaurantDetail mockDetail = new RestaurantDetail("2", "pic_url", "Test Restaurant", "Test Address", 4.0, "1234567890", false, "www.test.com");
        restaurantDetailLiveData.setValue(mockDetail);
        viewModel.initRestaurant("2");


        MutableLiveData<Boolean> likeResultLiveData = new MutableLiveData<>();
        likeResultLiveData.setValue(true);

        when(likeRestaurantUseCase.add(anyString())).thenReturn(likeResultLiveData);
        when(likeRestaurantUseCase.remove(anyString())).thenReturn(likeResultLiveData);

        // When
        viewModel.OnLikeCLick();
        viewModel.OnLikeCLick();

        // Then
        verify(likeRestaurantUseCase).remove("2");
    }


    @Test
    public void testOnFavorisClickAddFavoris() {
        RestaurantDetail mockDetail = new RestaurantDetail("2", "pic_url", "Test Restaurant", "Test Address", 4.0, "1234567890", false, "www.test.com");
        restaurantDetailLiveData.setValue(mockDetail);
        viewModel.initRestaurant("2");


        MutableLiveData<Boolean> likeResultLiveData = new MutableLiveData<>();
        likeResultLiveData.setValue(true);


        when(addRestaurantChoiceToDayUseCase.invoke(anyString(), anyString(),anyString())).thenReturn(likeResultLiveData);
        when(deleteRestaurantChoiceToDayUseCase.invoke()).thenReturn(likeResultLiveData);

        // When
        viewModel.onFavorisClick();

        // Then
        verify(addRestaurantChoiceToDayUseCase).invoke("2", Objects.requireNonNull(restaurantDetailLiveData.getValue()).getName(),restaurantDetailLiveData.getValue().getAddress());
    }

    @Test
    public void testOnFavorisClickDeleteFavoris() {
        RestaurantDetail mockDetail = new RestaurantDetail("2", "pic_url", "Test Restaurant", "Test Address", 4.0, "1234567890", false, "www.test.com");
        restaurantDetailLiveData.setValue(mockDetail);
        viewModel.initRestaurant("2");


        MutableLiveData<Boolean> likeResultLiveData = new MutableLiveData<>();
        likeResultLiveData.setValue(true);


        when(addRestaurantChoiceToDayUseCase.invoke(anyString(), anyString(),anyString())).thenReturn(likeResultLiveData);
        when(deleteRestaurantChoiceToDayUseCase.invoke()).thenReturn(likeResultLiveData);

        // When
        viewModel.onFavorisClick();
        viewModel.onFavorisClick();

        // Then
        verify(deleteRestaurantChoiceToDayUseCase).invoke();
    }

    @Test
    public void testGetWorkmateToEat() {
        // Given
        viewModel.initRestaurant("1");

        // When
        LiveData<List<Workmate>> result = viewModel.getWorkmateToEat();

        // Then
        assertNotNull(result);
        verify(getListWorkmateByIdRestaurantUseCase).invoke("1");
    }

    @Test
    public void testChoiceToEatHere() {
        // Given
        viewModel.initRestaurant("1");

        // When
        LiveData<Boolean> result = viewModel.choiceToEatHere("1");

        // Then
        assertNotNull(result);
        verify(getIfWorkmateEatInThisRestaurantUseCase).invoke("1");
    }
    @Test
    public void testGetRatingRestaurantInStringBuilder() {
        RestaurantDetail restaurantDetail = new RestaurantDetail("1", "pic_url", "Test Restaurant", "Test Address", 4.0, "1234567890", false, "www.test.com");
        MutableLiveData<RestaurantDetail> restaurantDetailLiveData = new MutableLiveData<>(restaurantDetail);
        when(getRestaurantByIdUseCase.invoke("1")).thenReturn(restaurantDetailLiveData);

        viewModel.initRestaurant("1");
        StringBuilder rating = viewModel.getRatingRestaurantInStingBuilder();
        assertEquals("⭐⭐", rating.toString());
    }

    @Test
    public void testGetTypeAndAddress() {
        RestaurantDetail restaurantDetail = new RestaurantDetail("1", "pic_url", "Test Restaurant", "Test Address", 5.0, "1234567890", false, "www.test.com");
        MutableLiveData<RestaurantDetail> restaurantDetailLiveData = new MutableLiveData<>(restaurantDetail);
        when(getRestaurantByIdUseCase.invoke("1")).thenReturn(restaurantDetailLiveData);

        viewModel.initRestaurant("1");
        String typeAndAddress = viewModel.getTypeAndAddress();
        assertEquals(" restaurant - Test Address", typeAndAddress);
    }

    @Test
    public void testGetPhoneNumber() {
        RestaurantDetail restaurantDetail = new RestaurantDetail("1", "pic_url", "Test Restaurant", "Test Address", 5.0, "1234567890", false, "www.test.com");
        MutableLiveData<RestaurantDetail> restaurantDetailLiveData = new MutableLiveData<>(restaurantDetail);
        when(getRestaurantByIdUseCase.invoke("1")).thenReturn(restaurantDetailLiveData);

        viewModel.initRestaurant("1");
        String phoneNumber = viewModel.getPhoneNumber();
        assertEquals("1234567890", phoneNumber);
    }

    @Test
    public void testGetWebsiteUrl() {
        RestaurantDetail restaurantDetail = new RestaurantDetail("1", "pic_url", "Test Restaurant", "Test Address", 5.0, "1234567890", false, "www.test.com");
        MutableLiveData<RestaurantDetail> restaurantDetailLiveData = new MutableLiveData<>(restaurantDetail);
        when(getRestaurantByIdUseCase.invoke("1")).thenReturn(restaurantDetailLiveData);

        viewModel.initRestaurant("1");
        String websiteUrl = viewModel.getWebsiteUrl();
        assertEquals("www.test.com", websiteUrl);
    }
}