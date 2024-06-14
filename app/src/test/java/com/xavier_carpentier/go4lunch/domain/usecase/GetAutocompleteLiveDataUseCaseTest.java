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

import com.xavier_carpentier.go4lunch.domain.repository.PlaceRepository;
import com.xavier_carpentier.go4lunch.presentation.model.AutocompletePrediction;
import com.xavier_carpentier.go4lunch.domain.model.AutocompletePredictionDomain;

import java.util.Arrays;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class GetAutocompleteLiveDataUseCaseTest {

    private GetAutocompleteLiveDataUseCase getAutocompleteLiveDataUseCase;

    @Mock
    private PlaceRepository placeRepository;

    @Mock
    private Observer<List<AutocompletePrediction>> observer;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getAutocompleteLiveDataUseCase = new GetAutocompleteLiveDataUseCase(placeRepository);
    }

    @Test
    public void testInvoke() {
        // Given
        String input = "pizza";
        String latitude = "10.0";
        String longitude = "20.0";
        MutableLiveData<List<AutocompletePredictionDomain>> autocompleteLiveData = new MutableLiveData<>();
        AutocompletePredictionDomain prediction1 = new AutocompletePredictionDomain("placeId1", "description1");
        AutocompletePredictionDomain prediction2 = new AutocompletePredictionDomain("placeId2", "description2");
        autocompleteLiveData.setValue(Arrays.asList(prediction1, prediction2));

        List<AutocompletePrediction> expectedPredictions = Arrays.asList(
                new AutocompletePrediction("placeId1", "description1"),
                new AutocompletePrediction("placeId2", "description2")
        );

        when(placeRepository.getAutocomplete(input, latitude, longitude, "5000", "restaurant")).thenReturn(autocompleteLiveData);

        // When
        LiveData<List<AutocompletePrediction>> result = getAutocompleteLiveDataUseCase.invoke(input, latitude, longitude);
        result.observeForever(observer);

        // Then
        verify(observer).onChanged(expectedPredictions);
        assertEquals(expectedPredictions, result.getValue());
    }
}