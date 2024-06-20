package com.xavier_carpentier.go4lunch.presentation.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User("id1", "John Doe", "http://example.com/picture");
    }

    @Test
    public void testGetters() {
        assertEquals("id1", user.getUid());
        assertEquals("John Doe", user.getUsername());
        assertEquals("http://example.com/picture", user.getUrlPicture());
    }

}