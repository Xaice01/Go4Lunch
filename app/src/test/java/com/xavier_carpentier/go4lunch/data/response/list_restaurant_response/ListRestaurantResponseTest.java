package com.xavier_carpentier.go4lunch.data.response.list_restaurant_response;
import org.junit.Test;
import java.util.Collections;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.List;

public class ListRestaurantResponseTest {

    @Test
    public void testNoArgsConstructor() {
        ListRestaurantResponse response = new ListRestaurantResponse();
        assertNull(response.getHtmlAttributions());
        assertNull(response.getNextPageToken());
        assertNull(response.getResults());
        assertNull(response.getStatus());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        List<Object> htmlAttributions = Collections.singletonList(new Object());
        String nextPageToken = "nextPageToken";
        List<Result> results = Collections.singletonList(new Result());
        String status = "status";

        ListRestaurantResponse response = new ListRestaurantResponse(htmlAttributions, nextPageToken, results, status);

        assertEquals(htmlAttributions, response.getHtmlAttributions());
        assertEquals(nextPageToken, response.getNextPageToken());
        assertEquals(results, response.getResults());
        assertEquals(status, response.getStatus());
    }

    @Test
    public void testSetters() {
        ListRestaurantResponse response = new ListRestaurantResponse();

        List<Object> htmlAttributions = Collections.singletonList(new Object());
        response.setHtmlAttributions(htmlAttributions);
        assertEquals(htmlAttributions, response.getHtmlAttributions());

        String nextPageToken = "nextPageToken";
        response.setNextPageToken(nextPageToken);
        assertEquals(nextPageToken, response.getNextPageToken());

        List<Result> results = Collections.singletonList(new Result());
        response.setResults(results);
        assertEquals(results, response.getResults());

        String status = "status";
        response.setStatus(status);
        assertEquals(status, response.getStatus());
    }

    @Test
    public void testToString() {
        ListRestaurantResponse response = new ListRestaurantResponse();

        List<Object> htmlAttributions = Collections.singletonList(new Object());
        response.setHtmlAttributions(htmlAttributions);

        String nextPageToken = "nextPageToken";
        response.setNextPageToken(nextPageToken);

        List<Result> results = Collections.singletonList(new Result());
        response.setResults(results);

        String status = "status";
        response.setStatus(status);

        String expected = ListRestaurantResponse.class.getName() + '@' + Integer.toHexString(System.identityHashCode(response)) + '[' +
                "htmlAttributions=" + ((response.getHtmlAttributions() == null) ? "<null>" : response.getHtmlAttributions()) +
                ",nextPageToken=" + ((response.getNextPageToken() == null) ? "<null>" : response.getNextPageToken()) +
                ",results=" + ((response.getResults() == null) ? "<null>" : response.getResults()) +
                ",status=" + ((response.getStatus() == null) ? "<null>" : response.getStatus()) +
                ']';

        assertEquals(expected, response.toString());
    }
}
