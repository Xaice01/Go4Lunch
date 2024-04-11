package com.xavier_carpentier.go4lunch.domain.repository;

import android.content.Context;

import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

import java.util.List;

public interface UserRepository {
    List<String> getBuilderListAuthenticationProvider(Context context);
    UserDomain getUser();
    void logout();
}
