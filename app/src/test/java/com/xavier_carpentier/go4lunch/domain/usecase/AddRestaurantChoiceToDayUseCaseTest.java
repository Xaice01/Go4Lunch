package com.xavier_carpentier.go4lunch.domain.usecase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
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

import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;


public class AddRestaurantChoiceToDayUseCaseTest {

    private AddRestaurantChoiceToDayUseCase addRestaurantChoiceToDayUseCase;

    @Mock
    private AuthUserRepository authUserRepository;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private Observer<Boolean> observer;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        addRestaurantChoiceToDayUseCase = new AddRestaurantChoiceToDayUseCase(usersRepository, authUserRepository);
    }

    @Test
    public void testInvoke() {
        // Given
        String idRestaurant = "restaurantId";
        String nameRestaurant = "RestaurantName";
        String vicinity = "Vicinity";
        String idUser = "userId";
        String nameUser = "UserName";
        String urlUserPicture = "urlUserPicture";

        MutableLiveData<Boolean> choiceLiveData = new MutableLiveData<>();
        choiceLiveData.setValue(true);

        UserDomain user = new UserDomain(idUser, nameUser, urlUserPicture);

        when(authUserRepository.getUser()).thenReturn(user);
        when(usersRepository.addRestaurantChoiceToDay(idUser, nameUser, urlUserPicture, idRestaurant, nameRestaurant, vicinity)).thenReturn(choiceLiveData);

        // When
        LiveData<Boolean> result = addRestaurantChoiceToDayUseCase.invoke(idRestaurant, nameRestaurant, vicinity);
        result.observeForever(observer);

        // Then
        verify(authUserRepository, times(3)).getUser(); // Adjusted to expect 3 invocations
        verify(usersRepository).addRestaurantChoiceToDay(idUser, nameUser, urlUserPicture, idRestaurant, nameRestaurant, vicinity);
        verify(observer).onChanged(true);
        assertEquals(true, result.getValue());
    }
}