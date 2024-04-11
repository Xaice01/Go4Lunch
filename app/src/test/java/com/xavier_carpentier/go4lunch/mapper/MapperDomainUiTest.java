package com.xavier_carpentier.go4lunch.mapper;

import static com.xavier_carpentier.go4lunch.domain.mapper.MapperDomainUi.userDomainToUserUi;

import android.net.Uri;

import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.presentation.model.User;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MapperDomainUiTest extends TestCase {
    @Mock
    private Uri uriTest;

    @Test
    public void userDomainToUserUiTest(){
        //Given
        String uidTest = "123456789";
        String userNameTest = "testusername";
        String emailTest = "test@mail.com";


        UserDomain userDomain = new UserDomain(uidTest,userNameTest,emailTest,uriTest);

        //When
        User user = userDomainToUserUi(userDomain);

        //Then
        assertEquals(user.getUid(), uidTest);
        assertEquals(user.getUsername(), userNameTest);
        assertEquals(user.getEmail(), emailTest);
        assertEquals(user.getUrlPicture(), uriTest);
    }


}
