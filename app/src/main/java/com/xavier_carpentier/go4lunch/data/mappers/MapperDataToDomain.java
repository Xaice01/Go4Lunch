package com.xavier_carpentier.go4lunch.data.mappers;

import android.location.Location;

import com.google.firebase.auth.FirebaseUser;
import com.xavier_carpentier.go4lunch.data.entity.autocomplete_response.Prediction;
import com.xavier_carpentier.go4lunch.data.entity.detail_restaurant_response.RestaurantDetailResponse;
import com.xavier_carpentier.go4lunch.data.entity.list_restaurant_response.Result;
import com.xavier_carpentier.go4lunch.datasource.utils.AuthProviderType;
import com.xavier_carpentier.go4lunch.domain.model.AuthProviderTypeDomain;
import com.xavier_carpentier.go4lunch.domain.model.AutocompletePredictionDomain;
import com.xavier_carpentier.go4lunch.domain.model.LocationDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantSearchDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;

import java.util.ArrayList;
import java.util.List;

public class MapperDataToDomain {

    public static UserDomain firebaseUserToUserDomain(FirebaseUser firebaseUser){
        if(firebaseUser.getPhotoUrl()==null)
        {
            return new UserDomain(firebaseUser.getUid(),firebaseUser.getDisplayName(),null);
        }
        return new UserDomain(firebaseUser.getUid(),firebaseUser.getDisplayName(), firebaseUser.getPhotoUrl().toString());
    }

    public static List<AuthProviderTypeDomain> ListAuthProviderTypeToListAuthProviderTypeDomain(List<AuthProviderType> listAuthProviderType){

        List<AuthProviderTypeDomain> providers =new ArrayList<>();

        for(AuthProviderType provider:listAuthProviderType) {
            switch (provider) {
                case TWITTER:
                    providers.add(AuthProviderTypeDomain.TWITTER);
                    break;
                case EMAIL:
                    providers.add(AuthProviderTypeDomain.EMAIL);
                    break;

                case GOOGLE:
                    providers.add(AuthProviderTypeDomain.GOOGLE);
                    break;

                case FACEBOOK:
                    providers.add(AuthProviderTypeDomain.FACEBOOK);
                    break;
            }
        }

        return providers;
    }

    public static RestaurantDomain restaurantDetailResponseToRestaurantDomain(RestaurantDetailResponse restaurantDetailResponse){
        String uidRestaurant;
        String restaurantName;
        String vicinity;
        String photoReferenceUrl;
        Double rating;
        String phoneNumber;
        String websiteUrl;

        if(restaurantDetailResponse!=null){
            uidRestaurant = restaurantDetailResponse.getResult().getPlaceId();
            restaurantName = restaurantDetailResponse.getResult().getName();
            vicinity = restaurantDetailResponse.getResult().getVicinity();
            photoReferenceUrl = restaurantDetailResponse.getResult().getPhotos().get(0).getPhotoURL();
            rating = restaurantDetailResponse.getResult().getRating();
            phoneNumber = restaurantDetailResponse.getResult().getFormattedPhoneNumber();
            websiteUrl = restaurantDetailResponse.getResult().getWebsite();

            if (uidRestaurant!=null && restaurantName!=null && vicinity!=null){
                return new RestaurantDomain(uidRestaurant,restaurantName,vicinity,photoReferenceUrl,rating,phoneNumber,websiteUrl);
            }else {
                return null;
            }
        }else{
            return null;
        }

    }

    public static List<AutocompletePredictionDomain> listPredictionToAutocompletePredictionDomain (List<Prediction> predictionList){
        List<AutocompletePredictionDomain> predictionDomainList = new ArrayList<>();

        for(Prediction prediction : predictionList){
            if (prediction.getPlaceId() != null && prediction.getStructuredFormatting() != null && prediction.getStructuredFormatting().getMainText() != null) {

                predictionDomainList.add(new AutocompletePredictionDomain(prediction.getPlaceId(), prediction.getStructuredFormatting().getMainText()));

            } else {
                return null;
            }
        }
        return predictionDomainList;
    }

    public static List<RestaurantSearchDomain> listResultRestaurantResponseToListRestaurantSearchDomain (List<Result> RestaurantResponseList,String latitudeUser,String longitudeUser){
        List<RestaurantSearchDomain> restaurantSearchDomainList = new ArrayList<>();

        for(Result restaurantResponse : RestaurantResponseList){
            if (restaurantResponse.getPlaceId() != null && restaurantResponse.getPhotos()!=null && restaurantResponse.getPhotos().get(0).getPhotoReference() != null) {

                //get distance between user and restaurant
                Location userLocation=new Location("userLocation");
                userLocation.setLatitude(Double.parseDouble(latitudeUser));
                userLocation.setLongitude(Double.parseDouble(longitudeUser));

                Double latitudeRestaurant = restaurantResponse.getGeometry().getLocation().getLat();
                Double longitudeRestaurant = restaurantResponse.getGeometry().getLocation().getLng();
                Location restaurantLocation= new Location("restaurantLocation");
                restaurantLocation.setLatitude(latitudeRestaurant);
                restaurantLocation.setLongitude(longitudeRestaurant);

                Integer distance = (int) userLocation.distanceTo(restaurantLocation);

                Boolean isOpen;
                if(restaurantResponse.getOpeningHours()!=null && restaurantResponse.getOpeningHours().getOpenNow()!=null) {
                    isOpen=restaurantResponse.getOpeningHours().getOpenNow();
                }else {
                    isOpen=null;
                }

                restaurantSearchDomainList.add(new RestaurantSearchDomain(restaurantResponse.getPlaceId(), restaurantResponse.getName(), restaurantResponse.getVicinity(), restaurantResponse.getPhotos().get(0).getPhotoURL(), restaurantResponse.getRating(), latitudeRestaurant.toString(), longitudeRestaurant.toString(), distance, isOpen));

            }
        }
        return restaurantSearchDomainList;
    }

    public static LocationDomain locationToLocationDomain(Location Location) {
        return new LocationDomain(String.valueOf(Location.getLatitude()), String.valueOf(Location.getLongitude()));
    }
}
