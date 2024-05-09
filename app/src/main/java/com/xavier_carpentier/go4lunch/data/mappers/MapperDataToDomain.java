package com.xavier_carpentier.go4lunch.data.mappers;

import com.google.firebase.auth.FirebaseUser;
import com.xavier_carpentier.go4lunch.data.entity.detail_restaurant_response.RestaurantDetailResponse;
import com.xavier_carpentier.go4lunch.datasource.utils.AuthProviderType;
import com.xavier_carpentier.go4lunch.domain.model.AuthProviderTypeDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantDomain;
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
            photoReferenceUrl = restaurantDetailResponse.getResult().getPhotos().get(0).getPhotoReference();
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
}
