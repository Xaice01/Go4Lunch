package com.xavier_carpentier.go4lunch.domain.mapper;

import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.presentation.model.User;

public class MapperDomainUi {
    public User userDomainToUserUi(UserDomain userDomain){
        return new User(userDomain.getUid(),userDomain.getUsername(),userDomain.getEmail(),userDomain.getUrlPicture());
    }
}
