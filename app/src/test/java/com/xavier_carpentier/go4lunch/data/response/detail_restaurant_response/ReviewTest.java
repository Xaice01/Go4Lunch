package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ReviewTest {

    @Test
    public void testNoArgsConstructor() {
        Review review = new Review();
        assertNull(review.getAuthorName());
        assertNull(review.getAuthorUrl());
        assertNull(review.getLanguage());
        assertNull(review.getOriginalLanguage());
        assertNull(review.getProfilePhotoUrl());
        assertNull(review.getRating());
        assertNull(review.getRelativeTimeDescription());
        assertNull(review.getText());
        assertNull(review.getTime());
        assertNull(review.getTranslated());
    }

    @Test
    public void testSettersAndGetters() {
        Review review = new Review();

        String authorName = "John Doe";
        review.setAuthorName(authorName);
        assertEquals(authorName, review.getAuthorName());

        String authorUrl = "http://example.com";
        review.setAuthorUrl(authorUrl);
        assertEquals(authorUrl, review.getAuthorUrl());

        String language = "en";
        review.setLanguage(language);
        assertEquals(language, review.getLanguage());

        String originalLanguage = "en";
        review.setOriginalLanguage(originalLanguage);
        assertEquals(originalLanguage, review.getOriginalLanguage());

        String profilePhotoUrl = "http://example.com/photo.jpg";
        review.setProfilePhotoUrl(profilePhotoUrl);
        assertEquals(profilePhotoUrl, review.getProfilePhotoUrl());

        Integer rating = 5;
        review.setRating(rating);
        assertEquals(rating, review.getRating());

        String relativeTimeDescription = "2 days ago";
        review.setRelativeTimeDescription(relativeTimeDescription);
        assertEquals(relativeTimeDescription, review.getRelativeTimeDescription());

        String text = "Great place!";
        review.setText(text);
        assertEquals(text, review.getText());

        Integer time = 1234567890;
        review.setTime(time);
        assertEquals(time, review.getTime());

        Boolean translated = true;
        review.setTranslated(translated);
        assertEquals(translated, review.getTranslated());
    }

    @Test
    public void testToString() {
        Review review = new Review();

        String authorName = "John Doe";
        String authorUrl = "http://example.com";
        String language = "en";
        String originalLanguage = "en";
        String profilePhotoUrl = "http://example.com/photo.jpg";
        Integer rating = 5;
        String relativeTimeDescription = "2 days ago";
        String text = "Great place!";
        Integer time = 1234567890;
        Boolean translated = true;

        review.setAuthorName(authorName);
        review.setAuthorUrl(authorUrl);
        review.setLanguage(language);
        review.setOriginalLanguage(originalLanguage);
        review.setProfilePhotoUrl(profilePhotoUrl);
        review.setRating(rating);
        review.setRelativeTimeDescription(relativeTimeDescription);
        review.setText(text);
        review.setTime(time);
        review.setTranslated(translated);

        String expected = Review.class.getName() + '@' + Integer.toHexString(System.identityHashCode(review)) + '[' +
                "authorName=" + authorName +
                ",authorUrl=" + authorUrl +
                ",language=" + language +
                ",originalLanguage=" + originalLanguage +
                ",profilePhotoUrl=" + profilePhotoUrl +
                ",rating=" + rating +
                ",relativeTimeDescription=" + relativeTimeDescription +
                ",text=" + text +
                ",time=" + time +
                ",translated=" + translated +
                ']';

        assertEquals(expected, review.toString());
    }
}
