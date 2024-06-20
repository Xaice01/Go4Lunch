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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.junit.runner.RunWith;

import com.xavier_carpentier.go4lunch.domain.model.RestaurantChoiceDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;

import java.util.Arrays;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class GetIfWorkmateEatInThisRestaurantUseCaseTest {

    private GetIfWorkmateEatInThisRestaurantUseCase getIfWorkmateEatInThisRestaurantUseCase;

    @Mock
    private AuthUserRepository authUserRepository;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private Observer<Boolean> observer;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getIfWorkmateEatInThisRestaurantUseCase = new GetIfWorkmateEatInThisRestaurantUseCase(usersRepository, authUserRepository);
    }

    @Test
    public void testInvoke() {
        // Given
        String restaurantId = "restaurantId";
        String userId = "userId";
        String userName = "User1";
        String userPictureUrl = "http://example.com/user1.png";

        MutableLiveData<List<RestaurantChoiceDomain>> choicesLiveData = new MutableLiveData<>();
        RestaurantChoiceDomain choice1 = new RestaurantChoiceDomain(null, userId, userName, userPictureUrl, restaurantId, "Restaurant1", "Address1");
        choicesLiveData.setValue(Arrays.asList(choice1));

        UserDomain userDomain = new UserDomain(userId, userName, userPictureUrl);

        when(usersRepository.getListWorkmateToChoiceARestaurant(restaurantId)).thenReturn(choicesLiveData);
        when(authUserRepository.getUser()).thenReturn(userDomain);

        // When
        LiveData<Boolean> result = getIfWorkmateEatInThisRestaurantUseCase.invoke(restaurantId);
        result.observeForever(observer);

        // Then
        verify(observer).onChanged(true);
        assertEquals(true, result.getValue());
    }
}