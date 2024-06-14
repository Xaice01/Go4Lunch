package com.xavier_carpentier.go4lunch.domain.model;

import com.google.firebase.Timestamp;
import org.junit.Test;
import static org.junit.Assert.*;

public class RestaurantChoiceDomainTest {

    @Test
    public void testRestaurantChoiceDomain() {
        Timestamp timestamp = new Timestamp(0, 0);
        RestaurantChoiceDomain choice = new RestaurantChoiceDomain(timestamp, "userId1", "John Doe", "url1", "restaurantId1", "Restaurant Name 1", "123 Main St");

        assertEquals(timestamp, choice.getTimestamp());
        assertEquals("userId1", choice.getIdUser());
        assertEquals("John Doe", choice.getUserName());
        assertEquals("url1", choice.getUrlUserPicture());
        assertEquals("restaurantId1", choice.getIdRestaurant());
        assertEquals("Restaurant Name 1", choice.getRestaurantName());
        assertEquals("123 Main St", choice.getVicinity());
    }

    @Test
    public void testEquals() {
        Timestamp timestamp = new Timestamp(0, 0);
        RestaurantChoiceDomain choice1 = new RestaurantChoiceDomain(timestamp, "userId1", "John Doe", "url1", "restaurantId1", "Restaurant Name 1", "123 Main St");
        RestaurantChoiceDomain choice2 = new RestaurantChoiceDomain(timestamp, "userId1", "John Doe", "url1", "restaurantId1", "Restaurant Name 1", "123 Main St");
        RestaurantChoiceDomain choice3 = new RestaurantChoiceDomain(timestamp, "userId2", "Jane Doe", "url2", "restaurantId2", "Restaurant Name 2", "456 Another St");

        assertEquals(choice1, choice2);
        assertNotEquals(choice1, choice3);
    }

    @Test
    public void testHashCode() {
        Timestamp timestamp = new Timestamp(0, 0);
        RestaurantChoiceDomain choice1 = new RestaurantChoiceDomain(timestamp, "userId1", "John Doe", "url1", "restaurantId1", "Restaurant Name 1", "123 Main St");
        RestaurantChoiceDomain choice2 = new RestaurantChoiceDomain(timestamp, "userId1", "John Doe", "url1", "restaurantId1", "Restaurant Name 1", "123 Main St");

        assertEquals(choice1.hashCode(), choice2.hashCode());
    }

    @Test
    public void testToString() {
        Timestamp timestamp = new Timestamp(0, 0);
        RestaurantChoiceDomain choice = new RestaurantChoiceDomain(timestamp, "userId1", "John Doe", "url1", "restaurantId1", "Restaurant Name 1", "123 Main St");
        String expected = "RestaurantChoiceDomain{timestamp=" + timestamp + ", idUser='userId1', userName='John Doe', urlUserPicture='url1', idRestaurant='restaurantId1', restaurantName='Restaurant Name 1', vicinity='123 Main St'}";

        assertEquals(expected, choice.toString());
    }
}
