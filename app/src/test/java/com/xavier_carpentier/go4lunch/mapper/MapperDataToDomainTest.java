package com.xavier_carpentier.go4lunch.mapper;

import static com.xavier_carpentier.go4lunch.data.mappers.MapperDataToDomain.ListAuthProviderTypeToListAuthProviderTypeDomain;
import static com.xavier_carpentier.go4lunch.data.mappers.MapperDataToDomain.firebaseUserToUserDomain;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;
import com.xavier_carpentier.go4lunch.datasource.utils.AuthProviderType;
import com.xavier_carpentier.go4lunch.domain.model.AuthProviderTypeDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MapperDataToDomainTest {

    @Mock
    private Uri uriTest;
    FirebaseUser mockFirebaseUser;


    @Before
    public void setUp(){
        mockFirebaseUser = mock(FirebaseUser.class);
        when(mockFirebaseUser.getUid()).thenReturn("123456789");
        when(mockFirebaseUser.getDisplayName()).thenReturn("testusername");
        when(mockFirebaseUser.getEmail()).thenReturn("test@mail.com");
        when(mockFirebaseUser.getPhotoUrl()).thenReturn(uriTest);
    }

    @Test
    public void firebaseUserToUserDomainTest(){
        //Given
        String uidTest = mockFirebaseUser.getUid();
        String userNameTest = mockFirebaseUser.getDisplayName();
        String emailTest = mockFirebaseUser.getEmail();

        //When
        UserDomain user = firebaseUserToUserDomain(mockFirebaseUser);

        //Then
        assertEquals(user.getUid(), uidTest);
        assertEquals(user.getUsername(), userNameTest);
        assertEquals(user.getEmail(), emailTest);
        assertEquals(user.getUrlPicture(), uriTest);
    }

    @Test
    public void ListAuthProviderTypeToListAuthProviderTypeDomainTest(){
        //Given
        List<AuthProviderType> providersToTest =new ArrayList<>();
        providersToTest.add(AuthProviderType.TWITTER);
        providersToTest.add(AuthProviderType.GOOGLE);

        //When
        List<AuthProviderTypeDomain> listProviderDomain = new ArrayList<>(ListAuthProviderTypeToListAuthProviderTypeDomain(providersToTest));

        //Then
        assertEquals(listProviderDomain.get(0).name(),providersToTest.get(0).name());
        assertEquals(listProviderDomain.get(1).name(),providersToTest.get(1).name());

    }

}
