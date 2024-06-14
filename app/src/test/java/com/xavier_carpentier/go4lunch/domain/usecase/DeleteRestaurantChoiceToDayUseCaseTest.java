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

import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class DeleteRestaurantChoiceToDayUseCaseTest {

    private DeleteRestaurantChoiceToDayUseCase deleteRestaurantChoiceToDayUseCase;

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
        deleteRestaurantChoiceToDayUseCase = new DeleteRestaurantChoiceToDayUseCase(usersRepository, authUserRepository);
    }

    @Test
    public void testInvoke() {
        // Given
        String userId = "userId";
        String userName = "UserName";
        String urlPicture = "urlPicture";
        UserDomain user = new UserDomain(userId, userName, urlPicture);

        MutableLiveData<Boolean> deleteLiveData = new MutableLiveData<>();
        deleteLiveData.setValue(true);

        when(authUserRepository.getUser()).thenReturn(user);
        when(usersRepository.deleteRestaurantChoiceToDay(userId)).thenReturn(deleteLiveData);

        // When
        LiveData<Boolean> result = deleteRestaurantChoiceToDayUseCase.invoke();
        result.observeForever(observer);

        // Then
        verify(authUserRepository).getUser();
        verify(usersRepository).deleteRestaurantChoiceToDay(userId);
        verify(observer).onChanged(true);
        assertEquals(true, result.getValue());
    }
}