package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;

import java.util.ArrayList;
import java.util.List;

public class GetListWorkmateToEatByIdRestaurantUseCase {
    private final AuthUserRepository userRepository;

    public GetListWorkmateToEatByIdRestaurantUseCase(AuthUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<List<Workmate>> invoke(){
        //TODO create a list with user and restaurant to choose today

        Workmate fakeWorkmate1 = new Workmate("1", "Fake1", null, "2", "FakeRestaurant2", "French");
        Workmate fakeWorkmate2 = new Workmate("2", "Fake2", null, "2", "FakeRestaurant2", "French");
        MutableLiveData<List<Workmate>> workmateLivedata = new MutableLiveData<>();

        List<Workmate> fakeData =new ArrayList<>();
        fakeData.add(fakeWorkmate1);
        fakeData.add(fakeWorkmate2);
        workmateLivedata.setValue(fakeData);

        return workmateLivedata;
    }
}
