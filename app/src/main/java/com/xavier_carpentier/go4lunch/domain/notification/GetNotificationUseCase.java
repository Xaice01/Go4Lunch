package com.xavier_carpentier.go4lunch.domain.notification;

import com.xavier_carpentier.go4lunch.domain.model.RestaurantChoiceDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.domain.repository.AuthUserRepository;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;
import com.xavier_carpentier.go4lunch.presentation.notification.MapperNotificationDomainNotificationUi;
import com.xavier_carpentier.go4lunch.presentation.notification.NotificationUi;

import java.util.ArrayList;
import java.util.List;

public class GetNotificationUseCase {
    private final AuthUserRepository authUserRepository;
    private final UsersRepository userRepository;

    public GetNotificationUseCase(AuthUserRepository authUserRepository, UsersRepository userRepository) {
        this.authUserRepository = authUserRepository;
        this.userRepository = userRepository;
    }

    public NotificationUi invoke(){

        List<RestaurantChoiceDomain> listRestaurantChoiceDomain = userRepository.getAllRestaurantChoiceToDayAsync();
        UserDomain userDomain = authUserRepository.getUser();

        String idUser=null;
        String userName=null;
        List<String> workmateToEatInThisRestaurant = new ArrayList<>();
        String idRestaurant=null;
        String restaurantName=null;
        String vicinity=null;

        for(RestaurantChoiceDomain restaurantChoice : listRestaurantChoiceDomain){
            if(restaurantChoice.getIdUser().equals(userDomain.getUid())){
                idUser=restaurantChoice.getIdUser();
                userName=userDomain.getUsername();
                idRestaurant=restaurantChoice.getIdRestaurant();
                restaurantName=restaurantChoice.getRestaurantName();
                vicinity=restaurantChoice.getVicinity();
            }
        }
        if(idUser!=null){
            for(RestaurantChoiceDomain restaurantChoice : listRestaurantChoiceDomain){
                if(restaurantChoice.getIdRestaurant().equals(idRestaurant) && !restaurantChoice.getIdUser().equals(idUser)){
                    workmateToEatInThisRestaurant.add(restaurantChoice.getUserName());
                }
            }
        }
        return MapperNotificationDomainNotificationUi.notificationDomainToNotificationUi(new NotificationDomain(userName,workmateToEatInThisRestaurant,idRestaurant,restaurantName,vicinity));
    }
}

