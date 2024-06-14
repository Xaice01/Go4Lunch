package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.xavier_carpentier.go4lunch.data.repository.UserRepositoryFirestore;
import com.xavier_carpentier.go4lunch.domain.usecase.GetListWorkmatesUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;

import java.util.List;

public class ListWorkmatesViewModel extends ViewModel {
    //------------------------------------
    // DATA
    //------------------------------------
    private UserRepositoryFirestore userRepositoryFirestore;
    private LiveData<List<Workmate>> listWorkmates;
    //----------------------------------------------------
    //UseCase
    //----------------------------------------------------

    public GetListWorkmatesUseCase getListWorkmatesUseCase;

    // Constructor for testing
    public ListWorkmatesViewModel(GetListWorkmatesUseCase getListWorkmatesUseCase) {
        this.userRepositoryFirestore = null;
        this.getListWorkmatesUseCase = getListWorkmatesUseCase;
    }
    private void initLiveDataWorkmates(){
        listWorkmates=getListWorkmatesUseCase.invoke();
    }


    public LiveData<List<Workmate>> getAllWorkmates(){
        initLiveDataWorkmates();
        return listWorkmates;
    }

}
