package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RestaurantDetailResponseTest {

    @Test
    public void testNoArgsConstructor() {
        RestaurantDetailResponse response = new RestaurantDetailResponse(null, null, null);

        assertNull(response.getHtmlAttributions());
        assertNull(response.getResult());
        assertNull(response.getStatus());
    }

    @Test
    public void testAllArgsConstructor() {
        List<Object> htmlAttributions = Arrays.asList("attr1", "attr2");
        Result result = new Result(); // Assuming Result class has a no-args constructor
        String status = "OK";

        RestaurantDetailResponse response = new RestaurantDetailResponse(htmlAttributions, result, status);

        assertEquals(htmlAttributions, response.getHtmlAttributions());
        assertEquals(result, response.getResult());
        assertEquals(status, response.getStatus());
    }

    @Test
    public void testSetHtmlAttributions() {
        RestaurantDetailResponse response = new RestaurantDetailResponse(null, null, null);
        List<Object> htmlAttributions = Arrays.asList("attr1", "attr2");
        response.setHtmlAttributions(htmlAttributions);

        assertEquals(htmlAttributions, response.getHtmlAttributions());
    }

    @Test
    public void testSetResult() {
        RestaurantDetailResponse response = new RestaurantDetailResponse(null, null, null);
        Result result = new Result(); // Assuming Result class has a no-args constructor
        response.setResult(result);

        assertEquals(result, response.getResult());
    }

    @Test
    public void testSetStatus() {
        RestaurantDetailResponse response = new RestaurantDetailResponse(null, null, null);
        String status = "OK";
        response.setStatus(status);

        assertEquals(status, response.getStatus());
    }

    @Test
    public void testToString() {
        List<Object> htmlAttributions = Arrays.asList("attr1", "attr2");
        Result result = new Result(); // Assuming Result class has a no-args constructor
        String status = "OK";

        RestaurantDetailResponse response = new RestaurantDetailResponse(htmlAttributions, result, status);

        String expected = "com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response.RestaurantDetailResponse@" +
                Integer.toHexString(System.identityHashCode(response)) +
                "[htmlAttributions=" + htmlAttributions +
                ",result=" + result +
                ",status=" + status +
                "]";

        assertEquals(expected, response.toString());
    }
}
