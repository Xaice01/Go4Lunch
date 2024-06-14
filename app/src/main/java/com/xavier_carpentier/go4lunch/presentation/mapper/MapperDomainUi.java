package com.xavier_carpentier.go4lunch.presentation.mapper;

import com.xavier_carpentier.go4lunch.domain.model.AuthProviderTypeDomain;
import com.xavier_carpentier.go4lunch.domain.model.AutocompletePredictionDomain;
import com.xavier_carpentier.go4lunch.domain.model.LocationDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantChoiceDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantSearchDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.presentation.model.AuthProviderTypeUi;
import com.xavier_carpentier.go4lunch.presentation.model.AutocompletePrediction;
import com.xavier_carpentier.go4lunch.presentation.model.LocationUi;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantDetail;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantItem;
import com.xavier_carpentier.go4lunch.presentation.model.User;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;

import android.net.Uri;
import java.util.ArrayList;
import java.util.List;

public class MapperDomainUi {
    public static User userDomainToUserUi(UserDomain userDomain){
        return new User(userDomain.getUid(),userDomain.getUsername(),userDomain.getUrlPicture());
    }

    public static UserDomain userUiToUserDomain(User user){
        return new UserDomain(user.getUid(),user.getUsername(),user.getUrlPicture());
    }

    public static List<AuthProviderTypeUi> listAuthProviderTypeDomainToListAuthProviderTypeUi(List<AuthProviderTypeDomain> authProviderTypeDomains){

        List<AuthProviderTypeUi> providers =new ArrayList<>();

        for(AuthProviderTypeDomain provider:authProviderTypeDomains) {
            switch (provider) {
                case TWITTER:
                    providers.add(AuthProviderTypeUi.TWITTER);
                    break;
                case EMAIL:
                    providers.add(AuthProviderTypeUi.EMAIL);
                    break;

                case GOOGLE:
                    providers.add(AuthProviderTypeUi.GOOGLE);
                    break;

                case FACEBOOK:
                    providers.add(AuthProviderTypeUi.FACEBOOK);
                    break;
            }
        }

        return providers;
    }

    public static RestaurantDetail restaurantDomainToRestaurantDetail(RestaurantDomain restaurantDomain){
        String uidRestaurant;
        String restaurantName;
        String vicinity;
        String photoReferenceUrl;
        Double rating;
        String phoneNumber;
        String websiteUrl;


        uidRestaurant = restaurantDomain.getUidRestaurant();
        restaurantName = restaurantDomain.getRestaurantName();
        vicinity = restaurantDomain.getVicinity();
        photoReferenceUrl = restaurantDomain.getPhotoReferenceUrl();
        rating = restaurantDomain.getRating();
        phoneNumber = restaurantDomain.getPhoneNumber();
        websiteUrl = restaurantDomain.getWebsiteUrl();
        return new RestaurantDetail(uidRestaurant,photoReferenceUrl,restaurantName,vicinity,rating,phoneNumber,false,websiteUrl);
    }

    public static List<AutocompletePrediction> listAutocompletePredictionDomainToListAutocompletePrediction(List<AutocompletePredictionDomain> predictionDomainList){
        List<AutocompletePrediction> predictionList = new ArrayList<>();

        for(AutocompletePredictionDomain predictionDomain : predictionDomainList){
            if (predictionDomain.getRestaurantId() != null) {

                predictionList.add(new AutocompletePrediction(predictionDomain.getRestaurantId(), predictionDomain.getRestaurantName()));

            } else {
                return null;
            }
        }
        return predictionList;

    }

    public static List<RestaurantItem> listRestaurantSearchDomainTolistRestaurantItem (List<RestaurantSearchDomain> restaurantSearchDomainList){
        List<RestaurantItem> restaurantItemList = new ArrayList<>();

        for(RestaurantSearchDomain restaurantSearchDomain : restaurantSearchDomainList){
            int rate;
            if(restaurantSearchDomain.getPlaceId() != null){
                if(restaurantSearchDomain.getRating()!=null){
                    rate = restaurantSearchDomain.getRating().intValue();
                }else{
                    rate =0;
                }
                Uri uriPhoto;
                if(restaurantSearchDomain.getPhotoReferenceUrl()!=null){
                    uriPhoto = Uri.parse(restaurantSearchDomain.getPhotoReferenceUrl());
                }else {
                    uriPhoto=null;
                }
                restaurantItemList.add(new RestaurantItem(restaurantSearchDomain.getPlaceId(),restaurantSearchDomain.getRestaurantName(),restaurantSearchDomain.getVicinity(),restaurantSearchDomain.getDistance(),rate,restaurantSearchDomain.getLatitude(),restaurantSearchDomain.getLongitude(),0,restaurantSearchDomain.getOpen(),uriPhoto));
            }
        }
        return restaurantItemList;
    }

    public static List<Workmate> listRestaurantChoiceDomainToListWorkmate(List<RestaurantChoiceDomain> restaurantChoiceDomains){
        List<Workmate> workmateList = new ArrayList<>();
        for(RestaurantChoiceDomain restaurantChoiceDomain : restaurantChoiceDomains){
            Uri uriPicture;
            if(restaurantChoiceDomain.getUrlUserPicture()!=null) {
                uriPicture = Uri.parse(restaurantChoiceDomain.getUrlUserPicture());
            } else {
                uriPicture = null;
            }
            workmateList.add(new Workmate(restaurantChoiceDomain.getIdUser(),restaurantChoiceDomain.getUserName(),uriPicture,restaurantChoiceDomain.getIdRestaurant(),restaurantChoiceDomain.getRestaurantName()));
        }
        return workmateList;
    }

    public static Workmate RestaurantChoiceDomainToWorkmate(RestaurantChoiceDomain restaurantChoiceDomain){
        Uri uriPicture;
        if(restaurantChoiceDomain!=null) {
            if (restaurantChoiceDomain.getUrlUserPicture() != null) {
                uriPicture = Uri.parse(restaurantChoiceDomain.getUrlUserPicture());
            } else {
                uriPicture = null;
            }
            return new Workmate(restaurantChoiceDomain.getIdUser(), restaurantChoiceDomain.getUserName(), uriPicture, restaurantChoiceDomain.getIdRestaurant(), restaurantChoiceDomain.getRestaurantName());
        }else{
            return null;
        }
    }


    public static List<Workmate> listUserDomainToListWorkmate(List<UserDomain> userDomains){
        List<Workmate> workmateList = new ArrayList<>();
        for(UserDomain userDomain : userDomains){
            Uri uriPicture;
            if(userDomain.getUrlPicture()!=null){
                uriPicture = Uri.parse(userDomain.getUrlPicture());
            }else{
                uriPicture = null;
            }
            workmateList.add(new Workmate(userDomain.getUid(),userDomain.getUsername(),uriPicture,null,null));
        }
        return workmateList;
    }

    public static LocationUi locationDomainToLocationUi(LocationDomain location) {
        return new LocationUi(location.getLatitude(), location.getLongitude());
    }
}
