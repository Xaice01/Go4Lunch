package com.xavier_carpentier.go4lunch.domain.usecase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.junit.runner.RunWith;

import com.google.firebase.Timestamp;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantChoiceDomain;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;

import java.util.Arrays;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class GetListWorkmatesUseCaseTest {

    private GetListWorkmatesUseCase getListWorkmatesUseCase;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private Observer<List<Workmate>> observer;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getListWorkmatesUseCase = new GetListWorkmatesUseCase(usersRepository);
    }

    @Test
    public void testInvoke() {
        // Given
        MutableLiveData<List<UserDomain>> usersLiveData = new MutableLiveData<>();
        UserDomain user1 = new UserDomain("id1", "User1", null);
        UserDomain user2 = new UserDomain("id2", "User2", null);
        usersLiveData.setValue(Arrays.asList(user1, user2));

        MutableLiveData<List<RestaurantChoiceDomain>> restaurantChoicesLiveData = new MutableLiveData<>();
        RestaurantChoiceDomain choice1 = new RestaurantChoiceDomain(new Timestamp(0,0), "id1", "User1", null, "restaurantId1", "Restaurant1", "Address1");
        RestaurantChoiceDomain choice2 = new RestaurantChoiceDomain(new Timestamp(0,0), "id2", "User2", null, "restaurantId2", "Restaurant2", "Address2");
        restaurantChoicesLiveData.setValue(Arrays.asList(choice1, choice2));

        List<Workmate> expectedWorkmates = Arrays.asList(
                new Workmate("id1", "User1", null, "restaurantId1", "Restaurant1"),
                new Workmate("id2", "User2", null, "restaurantId2", "Restaurant2")
        );

        when(usersRepository.getAllUsers()).thenReturn(usersLiveData);
        when(usersRepository.getAllRestaurantChoiceToDay()).thenReturn(restaurantChoicesLiveData);

        // When
        LiveData<List<Workmate>> result = getListWorkmatesUseCase.invoke();
        result.observeForever(observer);

        // Then
        assertEquals(expectedWorkmates.get(0).getUrlPicture(), result.getValue().get(0).getUrlPicture());
        assertEquals(expectedWorkmates.get(0).getRestaurantName(), result.getValue().get(0).getRestaurantName());
        assertEquals(expectedWorkmates.get(0).getUidRestaurant(), result.getValue().get(0).getUidRestaurant());
        assertEquals(expectedWorkmates.get(0).getUid(), result.getValue().get(0).getUid());
        assertEquals(expectedWorkmates.get(0).getUsername(), result.getValue().get(0).getUsername());

        assertEquals(expectedWorkmates.get(1).getUrlPicture(), result.getValue().get(1).getUrlPicture());
        assertEquals(expectedWorkmates.get(1).getRestaurantName(), result.getValue().get(1).getRestaurantName());
        assertEquals(expectedWorkmates.get(1).getUidRestaurant(), result.getValue().get(1).getUidRestaurant());
        assertEquals(expectedWorkmates.get(1).getUid(), result.getValue().get(1).getUid());
        assertEquals(expectedWorkmates.get(1).getUsername(), result.getValue().get(1).getUsername());
    }
}