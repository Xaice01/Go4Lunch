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

import com.xavier_carpentier.go4lunch.domain.model.RestaurantChoiceDomain;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;

import java.util.Arrays;
import java.util.List;

public class GetListWorkmateToEatByIdRestaurantUseCaseTest {

    private GetListWorkmateToEatByIdRestaurantUseCase getListWorkmateToEatByIdRestaurantUseCase;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private Observer<List<Workmate>> observer;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getListWorkmateToEatByIdRestaurantUseCase = new GetListWorkmateToEatByIdRestaurantUseCase(usersRepository);
    }

    @Test
    public void testInvoke() {
        // Given
        String restaurantId = "restaurantId";
        MutableLiveData<List<RestaurantChoiceDomain>> restaurantChoiceLiveData = new MutableLiveData<>();
        RestaurantChoiceDomain choice1 = new RestaurantChoiceDomain(null, "id1", "User1", null, restaurantId, "Restaurant1", "Address1");
        RestaurantChoiceDomain choice2 = new RestaurantChoiceDomain(null, "id2", "User2", null, restaurantId, "Restaurant2", "Address2");
        restaurantChoiceLiveData.setValue(Arrays.asList(choice1, choice2));

        List<Workmate> expectedWorkmates = Arrays.asList(
                new Workmate("id1", "User1", null, restaurantId, "Restaurant1"),
                new Workmate("id2", "User2", null, restaurantId, "Restaurant2")
        );

        when(usersRepository.getListWorkmateToChoiceARestaurant(restaurantId)).thenReturn(restaurantChoiceLiveData);

        // When
        LiveData<List<Workmate>> result = getListWorkmateToEatByIdRestaurantUseCase.invoke(restaurantId);
        result.observeForever(observer);

        // Then
        verify(observer).onChanged(expectedWorkmates);
        assertEquals(expectedWorkmates, result.getValue());
    }
}