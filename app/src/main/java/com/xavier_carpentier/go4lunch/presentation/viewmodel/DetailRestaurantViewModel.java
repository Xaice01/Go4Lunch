package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xavier_carpentier.go4lunch.data.RetrofitService;
import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;
import com.xavier_carpentier.go4lunch.data.repository.PlaceRepositoryRetrofit;
import com.xavier_carpentier.go4lunch.domain.usecase.GetListWorkmateToEatByIdRestaurantUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetRestaurantByIdUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantDetail;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;

import java.util.List;

public class DetailRestaurantViewModel extends ViewModel {
    //------------------------------------
    // DATA
    //------------------------------------
    private final AuthRepositoryFirebase authRepositoryFirebase = AuthRepositoryFirebase.getInstance();
    private final PlaceRepositoryRetrofit placeRepositoryRetrofit = new PlaceRepositoryRetrofit(RetrofitService.getPlaceApi());
    private LiveData<RestaurantDetail> restaurantDetail;
    private LiveData<List<Workmate>> listWorkmates;
    //----------------------------------------------------
    //UseCase
    //----------------------------------------------------
    private final GetRestaurantByIdUseCase getRestaurantByIdUseCase = new GetRestaurantByIdUseCase(placeRepositoryRetrofit);
    private final GetListWorkmateToEatByIdRestaurantUseCase getListWorkmateByIdRestaurantUseCase = new GetListWorkmateToEatByIdRestaurantUseCase(authRepositoryFirebase);

    private void initLiveDataWorkmatesToEat(){
        listWorkmates=getListWorkmateByIdRestaurantUseCase.invoke();
    }

    private void initRestaurantDetail(String uidRestaurant){
        //restaurantDetail = new MutableLiveData<RestaurantDetailUiState>(new RestaurantDetailUiState.Loading());
        restaurantDetail= getRestaurantByIdUseCase.invoke(uidRestaurant);
    }

    public LiveData<List<Workmate>> getWorkmateToEat(){
        initLiveDataWorkmatesToEat();
        return listWorkmates;
    }

    public void initRestaurant(String uidRestaurant){
        initRestaurantDetail(uidRestaurant);
    }
    public LiveData<RestaurantDetail> getRestaurantDetail(){
        return restaurantDetail;
    }

    public StringBuilder getRatingRestaurantInStingBuilder(){
        StringBuilder noteToWrite= new StringBuilder();
        for(int i=0;i<restaurantDetail.getValue().getNote()&&i<3;i++){
            noteToWrite.append("⭐");
        }
        return noteToWrite;
    }

    public String getTypeAndAddress(){
        String typeAndAddress = restaurantDetail.getValue().getTypeRestaurant()+" restaurant - "+ restaurantDetail.getValue().getAddress();
        //String typeAndAddress = String.format(String.valueOf(R.string.type_and_adress),restaurantDetail.getTypeRestaurant(),restaurantDetail.getAddress());
        return typeAndAddress;
    }
}
