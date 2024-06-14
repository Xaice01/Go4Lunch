package com.xavier_carpentier.go4lunch.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserDomainTest {

    @Test
    public void testUserDomain() {
        UserDomain user = new UserDomain("id1", "John Doe", "url1");

        assertEquals("id1", user.getUid());
        assertEquals("John Doe", user.getUsername());
        assertEquals("url1", user.getUrlPicture());
    }

    @Test
    public void testEquals() {
        UserDomain user1 = new UserDomain("id1", "John Doe", "url1");
        UserDomain user2 = new UserDomain("id1", "John Doe", "url1");
        UserDomain user3 = new UserDomain("id2", "Jane Doe", "url2");

        assertEquals(user1.getUid(), user2.getUid());
        assertNotEquals(user1.getUid(), user3.getUid());
    }

}
