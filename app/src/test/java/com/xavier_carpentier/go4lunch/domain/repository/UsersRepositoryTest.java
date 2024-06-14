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

import com.xavier_carpentier.go4lunch.domain.model.RestaurantChoiceDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class UsersRepositoryTest {

    private UsersRepository usersRepository;

    @Mock
    private UsersRepository mockUsersRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        usersRepository = mockUsersRepository;
    }

    @Test
    public void testCreateUserInDataBase() {
        // Given
        UserDomain user = new UserDomain("id1", "John Doe", "url1");

        // When
        usersRepository.createUserInDataBase(user);

        // Then
        verify(mockUsersRepository).createUserInDataBase(user);
    }

    @Test
    public void testGetAllUsers() {
        // Given
        List<UserDomain> expectedUsers = new ArrayList<>();
        expectedUsers.add(new UserDomain("id1", "John Doe", "url1"));
        expectedUsers.add(new UserDomain("id2", "Jane Doe", "url2"));

        MutableLiveData<List<UserDomain>> liveData = new MutableLiveData<>();
        liveData.setValue(expectedUsers);

        when(usersRepository.getAllUsers()).thenReturn(liveData);

        // When
        LiveData<List<UserDomain>> actualUsers = usersRepository.getAllUsers();

        // Then
        assertEquals(expectedUsers, actualUsers.getValue());
    }

    @Test
    public void testGetAllRestaurantChoiceToDay() {
        // Given
        List<RestaurantChoiceDomain> expectedChoices = new ArrayList<>();
        expectedChoices.add(new RestaurantChoiceDomain(null, "userId1", "User1", null, "restaurantId1", "Restaurant1", "Vicinity1"));
        expectedChoices.add(new RestaurantChoiceDomain(null, "userId2", "User2", null, "restaurantId2", "Restaurant2", "Vicinity2"));

        MutableLiveData<List<RestaurantChoiceDomain>> liveData = new MutableLiveData<>();
        liveData.setValue(expectedChoices);

        when(usersRepository.getAllRestaurantChoiceToDay()).thenReturn(liveData);

        // When
        LiveData<List<RestaurantChoiceDomain>> actualChoices = usersRepository.getAllRestaurantChoiceToDay();

        // Then
        assertEquals(expectedChoices, actualChoices.getValue());
    }

    @Test
    public void testAddRestaurantChoiceToDay() {
        // Given
        String idUser = "userId";
        String nameUser = "UserName";
        String urlUserPicture = "urlUserPicture";
        String idRestaurant = "restaurantId";
        String nameRestaurant = "RestaurantName";
        String vicinity = "Vicinity";

        MutableLiveData<Boolean> expectedLiveData = new MutableLiveData<>();
        expectedLiveData.setValue(true);

        when(usersRepository.addRestaurantChoiceToDay(idUser, nameUser, urlUserPicture, idRestaurant, nameRestaurant, vicinity)).thenReturn(expectedLiveData);

        // When
        LiveData<Boolean> actualLiveData = usersRepository.addRestaurantChoiceToDay(idUser, nameUser, urlUserPicture, idRestaurant, nameRestaurant, vicinity);

        // Then
        assertEquals(true, actualLiveData.getValue());
    }

    @Test
    public void testDeleteRestaurantChoiceToDay() {
        // Given
        String idUser = "userId";

        MutableLiveData<Boolean> expectedLiveData = new MutableLiveData<>();
        expectedLiveData.setValue(true);

        when(usersRepository.deleteRestaurantChoiceToDay(idUser)).thenReturn(expectedLiveData);

        // When
        LiveData<Boolean> actualLiveData = usersRepository.deleteRestaurantChoiceToDay(idUser);

        // Then
        assertEquals(true, actualLiveData.getValue());
    }

    @Test
    public void testGetRestaurantChoiceToDay() {
        // Given
        String idUser = "userId";
        RestaurantChoiceDomain expectedChoice = new RestaurantChoiceDomain(null, idUser, "UserName", null, "restaurantId", "RestaurantName", "Vicinity");

        MutableLiveData<RestaurantChoiceDomain> liveData = new MutableLiveData<>();
        liveData.setValue(expectedChoice);

        when(usersRepository.getRestaurantChoiceToDay(idUser)).thenReturn(liveData);

        // When
        LiveData<RestaurantChoiceDomain> actualChoice = usersRepository.getRestaurantChoiceToDay(idUser);

        // Then
        assertEquals(expectedChoice, actualChoice.getValue());
    }

    @Test
    public void testGetListWorkmateToChoiceARestaurant() {
        // Given
        String idRestaurant = "restaurantId";
        List<RestaurantChoiceDomain> expectedChoices = new ArrayList<>();
        expectedChoices.add(new RestaurantChoiceDomain(null, "userId1", "User1", null, idRestaurant, "Restaurant1", "Vicinity1"));

        MutableLiveData<List<RestaurantChoiceDomain>> liveData = new MutableLiveData<>();
        liveData.setValue(expectedChoices);

        when(usersRepository.getListWorkmateToChoiceARestaurant(idRestaurant)).thenReturn(liveData);

        // When
        LiveData<List<RestaurantChoiceDomain>> actualChoices = usersRepository.getListWorkmateToChoiceARestaurant(idRestaurant);

        // Then
        assertEquals(expectedChoices, actualChoices.getValue());
    }

    @Test
    public void testGetAllRestaurantChoiceToDayAsync() {
        // Given
        List<RestaurantChoiceDomain> expectedChoices = new ArrayList<>();
        expectedChoices.add(new RestaurantChoiceDomain(null, "userId1", "User1", null, "restaurantId1", "Restaurant1", "Vicinity1"));

        when(usersRepository.getAllRestaurantChoiceToDayAsync()).thenReturn(expectedChoices);

        // When
        List<RestaurantChoiceDomain> actualChoices = usersRepository.getAllRestaurantChoiceToDayAsync();

        // Then
        assertEquals(expectedChoices, actualChoices);
    }
}