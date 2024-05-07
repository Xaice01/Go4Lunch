package com.xavier_carpentier.go4lunch.domain.repository;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

import java.util.List;

public interface UsersRepository {

    void createUserInDataBase(UserDomain user);

    //LiveData<List<UserDomain>> getAllUsers();

}
