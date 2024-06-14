package com.xavier_carpentier.go4lunch.mapper;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import android.net.Uri;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.junit.runner.RunWith;

import com.google.firebase.Timestamp;

import com.xavier_carpentier.go4lunch.domain.model.AuthProviderTypeDomain;
import com.xavier_carpentier.go4lunch.domain.model.AutocompletePredictionDomain;
import com.xavier_carpentier.go4lunch.domain.model.LocationDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantChoiceDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantSearchDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.AuthProviderTypeUi;
import com.xavier_carpentier.go4lunch.presentation.model.AutocompletePrediction;
import com.xavier_carpentier.go4lunch.presentation.model.LocationUi;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantDetail;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantItem;
import com.xavier_carpentier.go4lunch.presentation.model.User;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class MapperDomainUiTest {

    private MapperDomainUi mapper;

    @Before
    public void setUp() {
        mapper = new MapperDomainUi();
    }

    @Test
    public void testUserDomainToUserUi() {
        UserDomain userDomain = new UserDomain("id1", "John Doe", "url1");
        User expectedUser = new User("id1", "John Doe", "url1");

        User actualUser = MapperDomainUi.userDomainToUserUi(userDomain);

        assertEquals(expectedUser.getUid(), actualUser.getUid());
        assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        assertEquals(expectedUser.getUrlPicture(), actualUser.getUrlPicture());
    }

    @Test
    public void testUserUiToUserDomain() {
        User user = new User("id1", "John Doe", "url1");
        UserDomain expectedUserDomain = new UserDomain("id1", "John Doe", "url1");

        UserDomain actualUserDomain = MapperDomainUi.userUiToUserDomain(user);

        assertEquals(expectedUserDomain.getUid(), actualUserDomain.getUid());
        assertEquals(expectedUserDomain.getUsername(), actualUserDomain.getUsername());
        assertEquals(expectedUserDomain.getUrlPicture(), actualUserDomain.getUrlPicture());
    }

    @Test
    public void testListAuthProviderTypeDomainToListAuthProviderTypeUi() {
        List<AuthProviderTypeDomain> authProviderTypeDomains = Arrays.asList(
                AuthProviderTypeDomain.TWITTER,
                AuthProviderTypeDomain.EMAIL,
                AuthProviderTypeDomain.FACEBOOK,
                AuthProviderTypeDomain.GOOGLE
        );
        List<AuthProviderTypeUi> expectedAuthProviderTypeUis = Arrays.asList(
                AuthProviderTypeUi.TWITTER,
                AuthProviderTypeUi.EMAIL,
                AuthProviderTypeUi.FACEBOOK,
                AuthProviderTypeUi.GOOGLE
        );

        List<AuthProviderTypeUi> actualAuthProviderTypeUis = MapperDomainUi.listAuthProviderTypeDomainToListAuthProviderTypeUi(authProviderTypeDomains);

        assertEquals(expectedAuthProviderTypeUis, actualAuthProviderTypeUis);
    }

    @Test
    public void testListAutocompletePredictionDomainToUi() {
        List<AutocompletePredictionDomain> autocompletePredictionDomains = Arrays.asList(
                new AutocompletePredictionDomain("placeId1", "description1"),
                new AutocompletePredictionDomain("placeId2", "description2")
        );
        List<AutocompletePrediction> expectedAutocompletePredictions = Arrays.asList(
                new AutocompletePrediction("placeId1", "description1"),
                new AutocompletePrediction("placeId2", "description2")
        );

        List<AutocompletePrediction> actualAutocompletePredictions = MapperDomainUi.listAutocompletePredictionDomainToListAutocompletePrediction(autocompletePredictionDomains);

        assertEquals(expectedAutocompletePredictions, actualAutocompletePredictions);
    }

    @Test
    public void testRestaurantDomainToRestaurantDetailUi() {
        RestaurantDomain restaurantDomain = new RestaurantDomain("id1", "name1", "address1", "type1", 2.5, "0789989898", "siteweb");
        RestaurantDetail expectedRestaurantDetail = new RestaurantDetail("id1", "type1", "name1", "address1", 2.5, "0789989898", true, "siteweb");

        RestaurantDetail actualRestaurantDetail = MapperDomainUi.restaurantDomainToRestaurantDetail(restaurantDomain);

        assertEquals(expectedRestaurantDetail.getUid(), actualRestaurantDetail.getUid());
        assertEquals(expectedRestaurantDetail.getName(), actualRestaurantDetail.getName());
        assertEquals(expectedRestaurantDetail.getAddress(), actualRestaurantDetail.getAddress());
        assertEquals(expectedRestaurantDetail.getPicture(), actualRestaurantDetail.getPicture());
        assertEquals(expectedRestaurantDetail.getNote(), actualRestaurantDetail.getNote());
        assertEquals(expectedRestaurantDetail.getPhone_number(), actualRestaurantDetail.getPhone_number());
        assertEquals(expectedRestaurantDetail.getWebSite(), actualRestaurantDetail.getWebSite());
    }

    @Test
    public void testlistRestaurantSearchDomainTolistRestaurantItem() {
        List<RestaurantSearchDomain> restaurantDomains = Arrays.asList(
                new RestaurantSearchDomain("id1", "name1", "address1", "url1", 0.0, "50", "50", 0, true),
                new RestaurantSearchDomain("id2", "name2", "address2", "url2", 0.0, "50", "50", 0, true)
        );
        List<RestaurantItem> expectedRestaurantItems = Arrays.asList(
                new RestaurantItem("id1", "name1", "address1", 0, 0, "50", "50", 0, true, Uri.parse("url1")),
                new RestaurantItem("id2", "name2", "address2", 0, 0, "50", "50", 0, null, Uri.parse("url2"))
        );

        List<RestaurantItem> actualRestaurantItems = MapperDomainUi.listRestaurantSearchDomainTolistRestaurantItem(restaurantDomains);

        assertEquals(expectedRestaurantItems.get(0).getUid(), actualRestaurantItems.get(0).getUid());
        assertEquals(expectedRestaurantItems.get(0).getIsOpen(), actualRestaurantItems.get(0).getIsOpen());
        assertEquals(expectedRestaurantItems.get(0).getAddress(), actualRestaurantItems.get(0).getAddress());
        assertEquals(expectedRestaurantItems.get(0).getName(), actualRestaurantItems.get(0).getName());
        assertEquals(expectedRestaurantItems.get(0).getDistance(), actualRestaurantItems.get(0).getDistance());
        assertEquals(expectedRestaurantItems.get(0).getNote(), actualRestaurantItems.get(0).getNote());
        assertEquals(expectedRestaurantItems.get(0).getPicture(), actualRestaurantItems.get(0).getPicture());
    }

    @Test
    public void testListRestaurantChoiceDomainToListWorkmate() {
        List<RestaurantChoiceDomain> restaurantChoiceDomains = Arrays.asList(
                new RestaurantChoiceDomain(new Timestamp(0, 0), "idUser1", "userName1", null, "idRestaurant1", "restaurantName1", "vicinity1"),
                new RestaurantChoiceDomain(new Timestamp(0, 0), "idUser2", "userName2", null, "idRestaurant2", "restaurantName2", "vicinity2")
        );
        List<Workmate> expectedWorkmates = Arrays.asList(
                new Workmate("idUser1", "userName1", null, "idRestaurant1", "restaurantName1"),
                new Workmate("idUser2", "userName2", null, "idRestaurant2", "restaurantName2")
        );

        List<Workmate> actualWorkmates = MapperDomainUi.listRestaurantChoiceDomainToListWorkmate(restaurantChoiceDomains);

        assertEquals(expectedWorkmates.get(0).getUrlPicture(), actualWorkmates.get(0).getUrlPicture());
        assertEquals(expectedWorkmates.get(0).getRestaurantName(), actualWorkmates.get(0).getRestaurantName());
        assertEquals(expectedWorkmates.get(0).getUidRestaurant(), actualWorkmates.get(0).getUidRestaurant());
        assertEquals(expectedWorkmates.get(0).getUid(), actualWorkmates.get(0).getUid());
        assertEquals(expectedWorkmates.get(0).getUsername(), actualWorkmates.get(0).getUsername());

        assertEquals(expectedWorkmates.get(1).getUrlPicture(), actualWorkmates.get(1).getUrlPicture());
        assertEquals(expectedWorkmates.get(1).getRestaurantName(), actualWorkmates.get(1).getRestaurantName());
        assertEquals(expectedWorkmates.get(1).getUidRestaurant(), actualWorkmates.get(1).getUidRestaurant());
        assertEquals(expectedWorkmates.get(1).getUid(), actualWorkmates.get(1).getUid());
        assertEquals(expectedWorkmates.get(1).getUsername(), actualWorkmates.get(1).getUsername());
    }

    @Test
    public void testRestaurantChoiceDomainToWorkmate() {
        RestaurantChoiceDomain restaurantChoiceDomain = new RestaurantChoiceDomain(new Timestamp(0, 0), "idUser1", "userName1", null, "idRestaurant1", "restaurantName1", "vicinity1");
        Workmate expectedWorkmate = new Workmate("idUser1", "userName1", null, "idRestaurant1", "restaurantName1");

        Workmate actualWorkmate = MapperDomainUi.RestaurantChoiceDomainToWorkmate(restaurantChoiceDomain);

        assertEquals(expectedWorkmate.getUrlPicture(), actualWorkmate.getUrlPicture());
        assertEquals(expectedWorkmate.getRestaurantName(), actualWorkmate.getRestaurantName());
        assertEquals(expectedWorkmate.getUidRestaurant(), actualWorkmate.getUidRestaurant());
        assertEquals(expectedWorkmate.getUid(), actualWorkmate.getUid());
        assertEquals(expectedWorkmate.getUsername(), actualWorkmate.getUsername());
    }

    @Test
    public void testListUserDomainToListWorkmate() {
        List<UserDomain> userDomains = Arrays.asList(
                new UserDomain("idUser1", "userName1", null),
                new UserDomain("idUser2", "userName2", null)
        );
        List<Workmate> expectedWorkmates = Arrays.asList(
                new Workmate("idUser1", "userName1", null, null, null),
                new Workmate("idUser2", "userName2", null, null, null)
        );

        List<Workmate> actualWorkmates = MapperDomainUi.listUserDomainToListWorkmate(userDomains);

        assertEquals(expectedWorkmates.get(0).getUid(), actualWorkmates.get(0).getUid());
        assertEquals(expectedWorkmates.get(0).getUsername(), actualWorkmates.get(0).getUsername());
        assertEquals(expectedWorkmates.get(0).getUrlPicture(), actualWorkmates.get(0).getUrlPicture());
        assertEquals(expectedWorkmates.get(1).getUid(), actualWorkmates.get(1).getUid());
        assertEquals(expectedWorkmates.get(1).getUsername(), actualWorkmates.get(1).getUsername());
        assertEquals(expectedWorkmates.get(1).getUrlPicture(), actualWorkmates.get(1).getUrlPicture());
    }

    @Test
    public void testLocationDomainToLocationUi() {
        LocationDomain locationDomain = new LocationDomain("10", "20");
        LocationUi expectedLocationUi = new LocationUi("10", "20");

        LocationUi actualLocationUi = MapperDomainUi.locationDomainToLocationUi(locationDomain);

        assertEquals(expectedLocationUi, actualLocationUi);
    }
}
