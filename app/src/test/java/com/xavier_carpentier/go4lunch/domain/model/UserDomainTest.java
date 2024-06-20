package com.xavier_carpentier.go4lunch.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class UserDomainTest {

    @Test
    public void testUserDomain() {
        UserDomain user = new UserDomain("id1", "John Doe", "url1");

        assertEquals("id1", user.getUid());
        assertEquals("John Doe", user.getUsername());
        assertEquals("url1", user.getUrlPicture());
        assertTrue(user.getUidRestaurantFavoris().isEmpty());
    }

    @Test
    public void testEquals() {
        UserDomain user1 = new UserDomain("id1", "John Doe", "url1");
        UserDomain user2 = new UserDomain("id1", "John Doe", "url1");
        UserDomain user3 = new UserDomain("id2", "Jane Doe", "url2");

        assertEquals(user1.getUid(), user2.getUid());
        assertNotEquals(user1.getUid(), user3.getUid());
    }

    @Test
    public void testSetUsername() {
        UserDomain user = new UserDomain();
        String username = "newUsername";

        user.setUsername(username);

        assertEquals(username, user.getUsername());
    }

    @Test
    public void testSetUid() {
        UserDomain user = new UserDomain();
        String uid = "newUid";

        user.setUid(uid);

        assertEquals(uid, user.getUid());
    }

    @Test
    public void testSetUrlPicture() {
        UserDomain user = new UserDomain();
        String urlPicture = "http://example.com/newpic.jpg";

        user.setUrlPicture(urlPicture);

        assertEquals(urlPicture, user.getUrlPicture());
    }

    @Test
    public void testSetUidRestaurantFavoris() {
        UserDomain user = new UserDomain();
        List<String> favoris = new ArrayList<>();
        favoris.add("restaurant1");
        favoris.add("restaurant2");

        user.setUidRestaurantFavoris(favoris);

        assertEquals(favoris, user.getUidRestaurantFavoris());
    }

}
