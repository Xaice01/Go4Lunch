package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ResultTest {

    @Test
    public void testNoArgsConstructor() {
        Result result = new Result();

        assertNull(result.getAddressComponents());
        assertNull(result.getAdrAddress());
        assertNull(result.getBusinessStatus());
        assertNull(result.getCurrentOpeningHours());
        assertNull(result.getEditorialSummary());
        assertNull(result.getFormattedAddress());
        assertNull(result.getFormattedPhoneNumber());
        assertNull(result.getGeometry());
        assertNull(result.getIcon());
        assertNull(result.getIconBackgroundColor());
        assertNull(result.getIconMaskBaseUri());
        assertNull(result.getInternationalPhoneNumber());
        assertNull(result.getName());
        assertNull(result.getOpeningHours());
        assertNull(result.getPhotos());
        assertNull(result.getPlaceId());
        assertNull(result.getPlusCode());
        assertNull(result.getRating());
        assertNull(result.getReference());
        assertNull(result.getReviews());
        assertNull(result.getTypes());
        assertNull(result.getUrl());
        assertNull(result.getUserRatingsTotal());
        assertNull(result.getUtcOffset());
        assertNull(result.getVicinity());
        assertNull(result.getWebsite());
        assertNull(result.getWheelchairAccessibleEntrance());
    }

    @Test
    public void testSettersAndGetters() {
        Result result = new Result();

        List<AddressComponent> addressComponents = Arrays.asList(new AddressComponent());
        result.setAddressComponents(addressComponents);
        assertEquals(addressComponents, result.getAddressComponents());

        String adrAddress = "adrAddress";
        result.setAdrAddress(adrAddress);
        assertEquals(adrAddress, result.getAdrAddress());

        String businessStatus = "businessStatus";
        result.setBusinessStatus(businessStatus);
        assertEquals(businessStatus, result.getBusinessStatus());

        CurrentOpeningHours currentOpeningHours = new CurrentOpeningHours();
        result.setCurrentOpeningHours(currentOpeningHours);
        assertEquals(currentOpeningHours, result.getCurrentOpeningHours());

        EditorialSummary editorialSummary = new EditorialSummary();
        result.setEditorialSummary(editorialSummary);
        assertEquals(editorialSummary, result.getEditorialSummary());

        String formattedAddress = "formattedAddress";
        result.setFormattedAddress(formattedAddress);
        assertEquals(formattedAddress, result.getFormattedAddress());

        String formattedPhoneNumber = "formattedPhoneNumber";
        result.setFormattedPhoneNumber(formattedPhoneNumber);
        assertEquals(formattedPhoneNumber, result.getFormattedPhoneNumber());

        Geometry geometry = new Geometry();
        result.setGeometry(geometry);
        assertEquals(geometry, result.getGeometry());

        String icon = "icon";
        result.setIcon(icon);
        assertEquals(icon, result.getIcon());

        String iconBackgroundColor = "iconBackgroundColor";
        result.setIconBackgroundColor(iconBackgroundColor);
        assertEquals(iconBackgroundColor, result.getIconBackgroundColor());

        String iconMaskBaseUri = "iconMaskBaseUri";
        result.setIconMaskBaseUri(iconMaskBaseUri);
        assertEquals(iconMaskBaseUri, result.getIconMaskBaseUri());

        String internationalPhoneNumber = "internationalPhoneNumber";
        result.setInternationalPhoneNumber(internationalPhoneNumber);
        assertEquals(internationalPhoneNumber, result.getInternationalPhoneNumber());

        String name = "name";
        result.setName(name);
        assertEquals(name, result.getName());

        OpeningHours openingHours = new OpeningHours();
        result.setOpeningHours(openingHours);
        assertEquals(openingHours, result.getOpeningHours());

        List<Photo> photos = Arrays.asList(new Photo());
        result.setPhotos(photos);
        assertEquals(photos, result.getPhotos());

        String placeId = "placeId";
        result.setPlaceId(placeId);
        assertEquals(placeId, result.getPlaceId());

        PlusCode plusCode = new PlusCode();
        result.setPlusCode(plusCode);
        assertEquals(plusCode, result.getPlusCode());

        Double rating = 4.5;
        result.setRating(rating);
        assertEquals(rating, result.getRating());

        String reference = "reference";
        result.setReference(reference);
        assertEquals(reference, result.getReference());

        List<Review> reviews = Arrays.asList(new Review());
        result.setReviews(reviews);
        assertEquals(reviews, result.getReviews());

        List<String> types = Arrays.asList("type1", "type2");
        result.setTypes(types);
        assertEquals(types, result.getTypes());

        String url = "url";
        result.setUrl(url);
        assertEquals(url, result.getUrl());

        Integer userRatingsTotal = 100;
        result.setUserRatingsTotal(userRatingsTotal);
        assertEquals(userRatingsTotal, result.getUserRatingsTotal());

        Integer utcOffset = 60;
        result.setUtcOffset(utcOffset);
        assertEquals(utcOffset, result.getUtcOffset());

        String vicinity = "vicinity";
        result.setVicinity(vicinity);
        assertEquals(vicinity, result.getVicinity());

        String website = "website";
        result.setWebsite(website);
        assertEquals(website, result.getWebsite());

        Boolean wheelchairAccessibleEntrance = true;
        result.setWheelchairAccessibleEntrance(wheelchairAccessibleEntrance);
        assertEquals(wheelchairAccessibleEntrance, result.getWheelchairAccessibleEntrance());
    }

    @Test
    public void testToString() {
        List<AddressComponent> addressComponents = Arrays.asList(new AddressComponent());
        String adrAddress = "adrAddress";
        String businessStatus = "businessStatus";
        CurrentOpeningHours currentOpeningHours = new CurrentOpeningHours();
        EditorialSummary editorialSummary = new EditorialSummary();
        String formattedAddress = "formattedAddress";
        String formattedPhoneNumber = "formattedPhoneNumber";
        Geometry geometry = new Geometry();
        String icon = "icon";
        String iconBackgroundColor = "iconBackgroundColor";
        String iconMaskBaseUri = "iconMaskBaseUri";
        String internationalPhoneNumber = "internationalPhoneNumber";
        String name = "name";
        OpeningHours openingHours = new OpeningHours();
        List<Photo> photos = Arrays.asList(new Photo());
        String placeId = "placeId";
        PlusCode plusCode = new PlusCode();
        Double rating = 4.5;
        String reference = "reference";
        List<Review> reviews = Arrays.asList(new Review());
        List<String> types = Arrays.asList("type1", "type2");
        String url = "url";
        Integer userRatingsTotal = 100;
        Integer utcOffset = 60;
        String vicinity = "vicinity";
        String website = "website";
        Boolean wheelchairAccessibleEntrance = true;

        Result result = new Result();
        result.setAddressComponents(addressComponents);
        result.setAdrAddress(adrAddress);
        result.setBusinessStatus(businessStatus);
        result.setCurrentOpeningHours(currentOpeningHours);
        result.setEditorialSummary(editorialSummary);
        result.setFormattedAddress(formattedAddress);
        result.setFormattedPhoneNumber(formattedPhoneNumber);
        result.setGeometry(geometry);
        result.setIcon(icon);
        result.setIconBackgroundColor(iconBackgroundColor);
        result.setIconMaskBaseUri(iconMaskBaseUri);
        result.setInternationalPhoneNumber(internationalPhoneNumber);
        result.setName(name);
        result.setOpeningHours(openingHours);
        result.setPhotos(photos);
        result.setPlaceId(placeId);
        result.setPlusCode(plusCode);
        result.setRating(rating);
        result.setReference(reference);
        result.setReviews(reviews);
        result.setTypes(types);
        result.setUrl(url);
        result.setUserRatingsTotal(userRatingsTotal);
        result.setUtcOffset(utcOffset);
        result.setVicinity(vicinity);
        result.setWebsite(website);
        result.setWheelchairAccessibleEntrance(wheelchairAccessibleEntrance);

        String expected = Result.class.getName() + '@' + Integer.toHexString(System.identityHashCode(result)) + '[' +
                "addressComponents=" + addressComponents +
                ",adrAddress=" + adrAddress +
                ",businessStatus=" + businessStatus +
                ",currentOpeningHours=" + currentOpeningHours +
                ",editorialSummary=" + editorialSummary +
                ",formattedAddress=" + formattedAddress +
                ",formattedPhoneNumber=" + formattedPhoneNumber +
                ",geometry=" + geometry +
                ",icon=" + icon +
                ",iconBackgroundColor=" + iconBackgroundColor +
                ",iconMaskBaseUri=" + iconMaskBaseUri +
                ",internationalPhoneNumber=" + internationalPhoneNumber +
                ",name=" + name +
                ",openingHours=" + openingHours +
                ",photos=" + photos +
                ",placeId=" + placeId +
                ",plusCode=" + plusCode +
                ",rating=" + rating +
                ",reference=" + reference +
                ",reviews=" + reviews +
                ",types=" + types +
                ",url=" + url +
                ",userRatingsTotal=" + userRatingsTotal +
                ",utcOffset=" + utcOffset +
                ",vicinity=" + vicinity +
                ",website=" + website +
                ",wheelchairAccessibleEntrance=" + wheelchairAccessibleEntrance +
                ']';

        assertEquals(expected, result.toString());
    }
}
