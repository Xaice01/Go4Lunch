package com.xavier_carpentier.go4lunch.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Transformations;

import com.xavier_carpentier.go4lunch.domain.repository.PlaceRepository;
import com.xavier_carpentier.go4lunch.presentation.mapper.MapperDomainUi;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantDetail;

import java.util.List;

public class GetRestaurantByIdUseCase {
    private final PlaceRepository placeRepository;
    private final LikeRestaurantUseCase likeRestaurantUseCase;
    public final MediatorLiveData<RestaurantDetail> restaurantDetailWithLikeStatus = new MediatorLiveData<>();
    public GetRestaurantByIdUseCase(PlaceRepository placeRepository,LikeRestaurantUseCase likeRestaurantUseCase) {
        this.placeRepository = placeRepository;
        this.likeRestaurantUseCase=likeRestaurantUseCase;
    }

    public LiveData<RestaurantDetail> invoke(String restaurantUid){

        LiveData<List<String>> likedRestaurantLiveData = likeRestaurantUseCase.getListRestaurant();
        LiveData<RestaurantDetail> restaurantDetailLiveData = Transformations.map(placeRepository.getRestaurant(restaurantUid), MapperDomainUi::restaurantDomainToRestaurantDetail);

        restaurantDetailWithLikeStatus.addSource(likedRestaurantLiveData,
                favoris -> updateRestaurantLikeStatus(restaurantDetailLiveData.getValue(), favoris));

        restaurantDetailWithLikeStatus.addSource(restaurantDetailLiveData,
                restaurantDetail -> updateRestaurantLikeStatus(restaurantDetail, likedRestaurantLiveData.getValue()));

        return restaurantDetailWithLikeStatus;
    }
    public void updateRestaurantLikeStatus(RestaurantDetail restaurant, List<String> favoris) {
        if (restaurant != null && favoris != null) {
            boolean isLiked = favoris.contains(restaurant.getUid());
            restaurant.setLike(isLiked);
            restaurantDetailWithLikeStatus.setValue(restaurant);
        }
    }
}