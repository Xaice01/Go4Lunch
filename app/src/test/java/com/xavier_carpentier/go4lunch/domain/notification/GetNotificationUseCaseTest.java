package com.xavier_carpentier.go4lunch.domain.notification;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.xavier_carpentier.go4lunch.domain.model.RestaurantChoiceDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GetNotificationUseCaseTest {

    private GetNotificationUseCase getNotificationUseCase;

    @Mock
    private AuthUserRepository authUserRepository;

    @Mock
    private UsersRepository usersRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getNotificationUseCase = new GetNotificationUseCase(authUserRepository, usersRepository);
    }

    @Test
    public void testInvoke_withRestaurantChoices() {
        // Given
        UserDomain user = new UserDomain("userId", "John Doe", "urlPicture");
        RestaurantChoiceDomain choice1 = new RestaurantChoiceDomain(null, "userId", "John Doe", null, "restaurantId1", "Restaurant Name 1", "123 Main St");
        RestaurantChoiceDomain choice2 = new RestaurantChoiceDomain(null, "workmateId1", "Jane Smith", null, "restaurantId1", "Restaurant Name 1", "123 Main St");
        RestaurantChoiceDomain choice3 = new RestaurantChoiceDomain(null, "workmateId2", "Jack White", null, "restaurantId1", "Restaurant Name 1", "123 Main St");

        List<RestaurantChoiceDomain> restaurantChoices = Arrays.asList(choice1, choice2, choice3);

        when(authUserRepository.getUser()).thenReturn(user);
        when(usersRepository.getAllRestaurantChoiceToDayAsync()).thenReturn(restaurantChoices);

        // When
        NotificationDomain result = getNotificationUseCase.invoke();

        // Then
        assertEquals("John Doe", result.getNameWorkmate());
        assertEquals(Arrays.asList("Jane Smith", "Jack White"), result.getWorkmateToEatInThisRestaurant());
        assertEquals("restaurantId1", result.getIdRestaurant());
        assertEquals("Restaurant Name 1", result.getRestaurantName());
        assertEquals("123 Main St", result.getRestaurantVicinity());
    }

    @Test
    public void testInvoke_withoutRestaurantChoices() {
        // Given
        UserDomain user = new UserDomain("userId", "John Doe", "urlPicture");

        List<RestaurantChoiceDomain> restaurantChoices = Collections.emptyList();

        when(authUserRepository.getUser()).thenReturn(user);
        when(usersRepository.getAllRestaurantChoiceToDayAsync()).thenReturn(restaurantChoices);

        // When
        NotificationDomain result = getNotificationUseCase.invoke();

        // Then
        assertEquals(null, result.getNameWorkmate());
        assertEquals(Collections.emptyList(), result.getWorkmateToEatInThisRestaurant());
        assertEquals(null, result.getIdRestaurant());
        assertEquals(null, result.getRestaurantName());
        assertEquals(null, result.getRestaurantVicinity());
    }
}
