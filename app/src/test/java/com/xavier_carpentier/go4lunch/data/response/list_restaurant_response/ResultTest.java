package com.xavier_carpentier.go4lunch.data.response.list_restaurant_response;
import org.junit.Test;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ResultTest {

    @Test
    public void testNoArgsConstructor() {
        Result result = new Result();
        assertNull(result.getBusinessStatus());
        assertNull(result.getGeometry());
        assertNull(result.getIcon());
        assertNull(result.getIconBackgroundColor());
        assertNull(result.getIconMaskBaseUri());
        assertNull(result.getName());
        assertNull(result.getPhotos());
        assertNull(result.getPlaceId());
        assertNull(result.getRating());
        assertNull(result.getReference());
        assertNull(result.getScope());
        assertNull(result.getTypes());
        assertNull(result.getUserRatingsTotal());
        assertNull(result.getVicinity());
        assertNull(result.getOpeningHours());
        assertNull(result.getPriceLevel());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        String businessStatus = "businessStatus";
        Geometry geometry = new Geometry();
        String icon = "icon";
        String iconBackgroundColor = "iconBackgroundColor";
        String iconMaskBaseUri = "iconMaskBaseUri";
        String name = "name";
        List<Photo> photos = Collections.singletonList(new Photo());
        String placeId = "placeId";
        Double rating = 4.5;
        String reference = "reference";
        String scope = "scope";
        List<String> types = Collections.singletonList("type");
        Integer userRatingsTotal = 100;
        String vicinity = "vicinity";
        OpeningHours openingHours = new OpeningHours();
        Integer priceLevel = 2;

        Result result = new Result(businessStatus, geometry, icon, iconBackgroundColor, iconMaskBaseUri, name, photos, placeId, rating, reference, scope, types, userRatingsTotal, vicinity, openingHours, priceLevel);

        assertEquals(businessStatus, result.getBusinessStatus());
        assertEquals(geometry, result.getGeometry());
        assertEquals(icon, result.getIcon());
        assertEquals(iconBackgroundColor, result.getIconBackgroundColor());
        assertEquals(iconMaskBaseUri, result.getIconMaskBaseUri());
        assertEquals(name, result.getName());
        assertEquals(photos, result.getPhotos());
        assertEquals(placeId, result.getPlaceId());
        assertEquals(rating, result.getRating());
        assertEquals(reference, result.getReference());
        assertEquals(scope, result.getScope());
        assertEquals(types, result.getTypes());
        assertEquals(userRatingsTotal, result.getUserRatingsTotal());
        assertEquals(vicinity, result.getVicinity());
        assertEquals(openingHours, result.getOpeningHours());
        assertEquals(priceLevel, result.getPriceLevel());
    }

    @Test
    public void testSetters() {
        Result result = new Result();

        String businessStatus = "businessStatus";
        result.setBusinessStatus(businessStatus);
        assertEquals(businessStatus, result.getBusinessStatus());

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

        String name = "name";
        result.setName(name);
        assertEquals(name, result.getName());

        List<Photo> photos = Collections.singletonList(new Photo());
        result.setPhotos(photos);
        assertEquals(photos, result.getPhotos());

        String placeId = "placeId";
        result.setPlaceId(placeId);
        assertEquals(placeId, result.getPlaceId());

        Double rating = 4.5;
        result.setRating(rating);
        assertEquals(rating, result.getRating());

        String reference = "reference";
        result.setReference(reference);
        assertEquals(reference, result.getReference());

        String scope = "scope";
        result.setScope(scope);
        assertEquals(scope, result.getScope());

        List<String> types = Collections.singletonList("type");
        result.setTypes(types);
        assertEquals(types, result.getTypes());

        Integer userRatingsTotal = 100;
        result.setUserRatingsTotal(userRatingsTotal);
        assertEquals(userRatingsTotal, result.getUserRatingsTotal());

        String vicinity = "vicinity";
        result.setVicinity(vicinity);
        assertEquals(vicinity, result.getVicinity());

        OpeningHours openingHours = new OpeningHours();
        result.setOpeningHours(openingHours);
        assertEquals(openingHours, result.getOpeningHours());

        Integer priceLevel = 2;
        result.setPriceLevel(priceLevel);
        assertEquals(priceLevel, result.getPriceLevel());
    }

    @Test
    public void testToString() {
        Result result = new Result();

        String businessStatus = "businessStatus";
        result.setBusinessStatus(businessStatus);

        Geometry geometry = new Geometry();
        result.setGeometry(geometry);

        String icon = "icon";
        result.setIcon(icon);

        String iconBackgroundColor = "iconBackgroundColor";
        result.setIconBackgroundColor(iconBackgroundColor);

        String iconMaskBaseUri = "iconMaskBaseUri";
        result.setIconMaskBaseUri(iconMaskBaseUri);

        String name = "name";
        result.setName(name);

        List<Photo> photos = Collections.singletonList(new Photo());
        result.setPhotos(photos);

        String placeId = "placeId";
        result.setPlaceId(placeId);

        Double rating = 4.5;
        result.setRating(rating);

        String reference = "reference";
        result.setReference(reference);

        String scope = "scope";
        result.setScope(scope);

        List<String> types = Collections.singletonList("type");
        result.setTypes(types);

        Integer userRatingsTotal = 100;
        result.setUserRatingsTotal(userRatingsTotal);

        String vicinity = "vicinity";
        result.setVicinity(vicinity);

        OpeningHours openingHours = new OpeningHours();
        result.setOpeningHours(openingHours);

        Integer priceLevel = 2;
        result.setPriceLevel(priceLevel);

        String expected = Result.class.getName() + '@' + Integer.toHexString(System.identityHashCode(result)) + '[' +
                "businessStatus=" + ((result.getBusinessStatus() == null) ? "<null>" : result.getBusinessStatus()) +
                ",geometry=" + ((result.getGeometry() == null) ? "<null>" : result.getGeometry()) +
                ",icon=" + ((result.getIcon() == null) ? "<null>" : result.getIcon()) +
                ",iconBackgroundColor=" + ((result.getIconBackgroundColor() == null) ? "<null>" : result.getIconBackgroundColor()) +
                ",iconMaskBaseUri=" + ((result.getIconMaskBaseUri() == null) ? "<null>" : result.getIconMaskBaseUri()) +
                ",name=" + ((result.getName() == null) ? "<null>" : result.getName()) +
                ",photos=" + ((result.getPhotos() == null) ? "<null>" : result.getPhotos()) +
                ",placeId=" + ((result.getPlaceId() == null) ? "<null>" : result.getPlaceId()) +
                ",rating=" + ((result.getRating() == null) ? "<null>" : result.getRating()) +
                ",reference=" + ((result.getReference() == null) ? "<null>" : result.getReference()) +
                ",scope=" + ((result.getScope() == null) ? "<null>" : result.getScope()) +
                ",types=" + ((result.getTypes() == null) ? "<null>" : result.getTypes()) +
                ",userRatingsTotal=" + ((result.getUserRatingsTotal() == null) ? "<null>" : result.getUserRatingsTotal()) +
                ",vicinity=" + ((result.getVicinity() == null) ? "<null>" : result.getVicinity()) +
                ",openingHours=" + ((result.getOpeningHours() == null) ? "<null>" : result.getOpeningHours()) +
                ",priceLevel=" + ((result.getPriceLevel() == null) ? "<null>" : result.getPriceLevel()) +
                ']';

        assertEquals(expected, result.toString());
    }
}
