package com.xavier_carpentier.go4lunch.domain.usecase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.Timestamp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xavier_carpentier.go4lunch.domain.model.RestaurantChoiceDomain;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;

public class GetWorkmateUseCaseTest {

    private GetWorkmateUseCase getWorkmateUseCase;

    @Mock
    private UsersRepository usersRepository;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getWorkmateUseCase = new GetWorkmateUseCase(usersRepository);
    }

    @Test
    public void testInvoke() {
        // Given
        String userId = "userId";
        MutableLiveData<RestaurantChoiceDomain> expectedLiveData = new MutableLiveData<>();
        RestaurantChoiceDomain expectedRestaurantChoiceDomain = new RestaurantChoiceDomain(
                new Timestamp(0, 0), "userId", "John Doe", null, "restaurantId", "restaurantName", "vicinity"
        );
        expectedLiveData.setValue(expectedRestaurantChoiceDomain);

        when(usersRepository.getRestaurantChoiceToDay(userId)).thenReturn(expectedLiveData);

        // When
        LiveData<Workmate> actualLiveData = getWorkmateUseCase.invoke(userId);

        // Then
        verify(usersRepository).getRestaurantChoiceToDay(userId);
        assertEquals(MapperDomainUi.RestaurantChoiceDomainToWorkmate(expectedLiveData.getValue()), actualLiveData.getValue());
    }
}
