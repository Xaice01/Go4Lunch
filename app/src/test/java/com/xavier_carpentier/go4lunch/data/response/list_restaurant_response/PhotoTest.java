package com.xavier_carpentier.go4lunch.data.response.list_restaurant_response;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.xavier_carpentier.go4lunch.BuildConfig;

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
    public void testAllArgsConstructorAndGetters() {
        Integer height = 1080;
        List<String> htmlAttributions = Arrays.asList("Attribution1", "Attribution2");
        String photoReference = "somePhotoReference";
        Integer width = 1920;

        Photo photo = new Photo(height, htmlAttributions, photoReference, width);

        assertEquals(height, photo.getHeight());
        assertEquals(htmlAttributions, photo.getHtmlAttributions());
        assertEquals(photoReference, photo.getPhotoReference());
        assertEquals(width, photo.getWidth());
    }

    @Test
    public void testSetters() {
        Photo photo = new Photo();

        Integer height = 1080;
        photo.setHeight(height);
        assertEquals(height, photo.getHeight());

        List<String> htmlAttributions = Collections.singletonList("Attribution1");
        photo.setHtmlAttributions(htmlAttributions);
        assertEquals(htmlAttributions, photo.getHtmlAttributions());

        String photoReference = "somePhotoReference";
        photo.setPhotoReference(photoReference);
        assertEquals(photoReference, photo.getPhotoReference());

        Integer width = 1920;
        photo.setWidth(width);
        assertEquals(width, photo.getWidth());
    }

    @Test
    public void testGetPhotoURL() {
        Photo photo = new Photo();
        String photoReference = "somePhotoReference";
        photo.setPhotoReference(photoReference);

        String expectedUrl = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1080&photo_reference=" + photoReference + "&key=" + BuildConfig.GOOGLE_API_KEY;
        assertEquals(expectedUrl, photo.getPhotoURL());
    }

    @Test
    public void testToString() {
        Photo photo = new Photo();

        Integer height = 1080;
        photo.setHeight(height);

        List<String> htmlAttributions = Collections.singletonList("Attribution1");
        photo.setHtmlAttributions(htmlAttributions);

        String photoReference = "somePhotoReference";
        photo.setPhotoReference(photoReference);

        Integer width = 1920;
        photo.setWidth(width);

        String expected = Photo.class.getName() + '@' + Integer.toHexString(System.identityHashCode(photo)) + '[' +
                "height=" + ((photo.getHeight() == null) ? "<null>" : photo.getHeight()) +
                ",htmlAttributions=" + ((photo.getHtmlAttributions() == null) ? "<null>" : photo.getHtmlAttributions()) +
                ",photoReference=" + ((photo.getPhotoReference() == null) ? "<null>" : photo.getPhotoReference()) +
                ",width=" + ((photo.getWidth() == null) ? "<null>" : photo.getWidth()) +
                ']';

        assertEquals(expected, photo.toString());
    }
}
