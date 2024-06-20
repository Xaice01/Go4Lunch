package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.net.Uri;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.xavier_carpentier.go4lunch.data.repository.UserRepositoryFirestore;
import com.xavier_carpentier.go4lunch.domain.usecase.GetListWorkmatesUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class ListWorkmatesViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private GetListWorkmatesUseCase getListWorkmatesUseCase;

    private ListWorkmatesViewModel listWorkmatesViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        listWorkmatesViewModel= new ListWorkmatesViewModel(getListWorkmatesUseCase);
    }

    @Test
    public void testGetAllWorkmates() {
        List<Workmate> workmates = new ArrayList<>();
        Uri testUri = Uri.parse("http://test.com/test.png");
        workmates.add(new Workmate("testUid", "testName", testUri, "testUidRestaurant","testRestaurant"));

        MutableLiveData<List<Workmate>> liveDataWorkmates = new MutableLiveData<>();
        liveDataWorkmates.setValue(workmates);

        when(getListWorkmatesUseCase.invoke()).thenReturn(liveDataWorkmates);

        LiveData<List<Workmate>> result = listWorkmatesViewModel.getAllWorkmates();
        assertEquals(workmates, result.getValue());

        verify(getListWorkmatesUseCase).invoke();
    }
}