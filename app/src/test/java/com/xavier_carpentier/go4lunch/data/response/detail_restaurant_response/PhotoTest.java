package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;
import static com.xavier_carpentier.go4lunch.BuildConfig.GOOGLE_API_KEY;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PhotoTest {

    @Test
    public void testNoArgsConstructor() {
        Photo photo = new Photo();
        assertNull(photo.getHeight());
        assertNull(photo.getHtmlAttributions());
        assertNull(photo.getPhotoReference());
        assertNull(photo.getWidth());
    }

    @Test
    public void testSettersAndGetters() {
        Photo photo = new Photo();

        Integer height = 400;
        photo.setHeight(height);
        assertEquals(height, photo.getHeight());

        List<String> htmlAttributions = Arrays.asList("Attribution1", "Attribution2");
        photo.setHtmlAttributions(htmlAttributions);
        assertEquals(htmlAttributions, photo.getHtmlAttributions());

        String photoReference = "photoRef123";
        photo.setPhotoReference(photoReference);
        assertEquals(photoReference, photo.getPhotoReference());

        Integer width = 800;
        photo.setWidth(width);
        assertEquals(width, photo.getWidth());
    }

    @Test
    public void testGetPhotoURL() {
        Photo photo = new Photo();
        String photoReference = "photoRef123";
        photo.setPhotoReference(photoReference);

        // Assuming GOOGLE_API_KEY is defined and accessible
        String expectedURL = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1080&photo_reference=" + photoReference + "&key=" + GOOGLE_API_KEY;
        assertEquals(expectedURL, photo.getPhotoURL());
    }

    @Test
    public void testToString() {
        Photo photo = new Photo();

        Integer height = 400;
        List<String> htmlAttributions = Arrays.asList("Attribution1", "Attribution2");
        String photoReference = "photoRef123";
        Integer width = 800;

        photo.setHeight(height);
        photo.setHtmlAttributions(htmlAttributions);
        photo.setPhotoReference(photoReference);
        photo.setWidth(width);

        String expected = Photo.class.getName() + '@' + Integer.toHexString(System.identityHashCode(photo)) + '[' +
                "height=" + height +
                ",htmlAttributions=" + htmlAttributions +
                ",photoReference=" + photoReference +
                ",width=" + width +
                ']';

        assertEquals(expected, photo.toString());
    }
}
