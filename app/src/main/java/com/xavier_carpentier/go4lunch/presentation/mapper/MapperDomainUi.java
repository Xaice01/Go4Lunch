package com.xavier_carpentier.go4lunch.presentation.mapper;

import com.xavier_carpentier.go4lunch.data.entity.detail_restaurant_response.RestaurantDetailResponse;
import com.xavier_carpentier.go4lunch.domain.model.AuthProviderTypeDomain;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.presentation.model.AuthProviderTypeUi;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantDetail;
import com.xavier_carpentier.go4lunch.presentation.model.User;

import java.util.ArrayList;
import java.util.List;

public class MapperDomainUi {
    public static User userDomainToUserUi(UserDomain userDomain){
        return new User(userDomain.getUid(),userDomain.getUsername(),userDomain.getUrlPicture());
    }

    public static UserDomain UserUiToUserDomain(User user){
        return new UserDomain(user.getUid(),user.getUsername(),user.getUrlPicture());
    }

    public static List<AuthProviderTypeUi> ListAuthProviderTypeDomainToListAuthProviderTypeUi(List<AuthProviderTypeDomain> authProviderTypeDomains){

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
        //TODO type or address to change
        return new RestaurantDetail(uidRestaurant,photoReferenceUrl,restaurantName,vicinity,vicinity,rating,phoneNumber,false,websiteUrl);



    }
}
