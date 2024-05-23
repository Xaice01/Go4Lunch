package com.xavier_carpentier.go4lunch.mapper;

import static com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi.listAuthProviderTypeDomainToListAuthProviderTypeUi;
import static com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi.userDomainToUserUi;

import com.xavier_carpentier.go4lunch.domain.model.AuthProviderTypeDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.presentation.model.AuthProviderTypeUi;
import com.xavier_carpentier.go4lunch.presentation.model.User;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class MapperDomainUiTest extends TestCase {
    @Mock
    private String urlTest;

    @Test
    public void userDomainToUserUiTest(){
        //Given
        String uidTest = "123456789";
        String userNameTest = "testusername";


        UserDomain userDomain = new UserDomain(uidTest,userNameTest,urlTest);

        //When
        User user = userDomainToUserUi(userDomain);

        //Then
        assertEquals(user.getUid(), uidTest);
        assertEquals(user.getUsername(), userNameTest);
        assertEquals(user.getUrlPicture(), urlTest);
    }

    @Test
    public void ListAuthProviderTypeDomainToListAuthProviderTypeUiTest(){
        //Given
        List<AuthProviderTypeDomain> providersToTest =new ArrayList<>();
        providersToTest.add(AuthProviderTypeDomain.TWITTER);
        providersToTest.add(AuthProviderTypeDomain.GOOGLE);

        //When
        List<AuthProviderTypeUi> listProviderUi = new ArrayList<>(listAuthProviderTypeDomainToListAuthProviderTypeUi(providersToTest));

        //Then
        assertEquals(listProviderUi.get(0).name(),providersToTest.get(0).name());
        assertEquals(listProviderUi.get(1).name(),providersToTest.get(1).name());

    }

}
