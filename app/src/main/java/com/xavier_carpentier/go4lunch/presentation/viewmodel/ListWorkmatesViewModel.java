package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;
import com.xavier_carpentier.go4lunch.data.repository.UserRepositoryFirestore;
import com.xavier_carpentier.go4lunch.domain.usecase.GetListWorkmatesUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;

import java.util.List;

public class ListWorkmatesViewModel extends ViewModel {
    //------------------------------------
    // DATA
    //------------------------------------
    private final AuthRepositoryFirebase authRepositoryFirebase = AuthRepositoryFirebase.getInstance();
    private final UserRepositoryFirestore userRepositoryFirestore = UserRepositoryFirestore.getInstance();
    private LiveData<List<Workmate>> listWorkmates;
    //----------------------------------------------------
    //UseCase
    //----------------------------------------------------

    private final GetListWorkmatesUseCase getListWorkmatesUseCase = new GetListWorkmatesUseCase(userRepositoryFirestore);

    private void initLiveDataWorkmates(){
        listWorkmates=getListWorkmatesUseCase.invoke();
    }


    public LiveData<List<Workmate>> getAllWorkmates(){
        initLiveDataWorkmates();
        return listWorkmates;
    }

}
