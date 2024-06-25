package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.annotation.Nullable;

import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.presentation.model.User;

public class GetCurrentUserUseCase{
    private final AuthUserRepository userRepository;

    public GetCurrentUserUseCase(AuthUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public User invoke(){
        UserDomain user=userRepository.getUser();
        if(user==null){
            return null;
        }else{
            return MapperDomainUi.userDomainToUserUi(userRepository.getUser());
        }
    }
}
