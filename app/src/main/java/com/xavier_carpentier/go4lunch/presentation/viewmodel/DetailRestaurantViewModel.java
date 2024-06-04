package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xavier_carpentier.go4lunch.data.RetrofitService;
import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;
import com.xavier_carpentier.go4lunch.data.repository.FavorisRestaurantRepositoryFirestore;
import com.xavier_carpentier.go4lunch.data.repository.PlaceRepositoryRetrofit;
import com.xavier_carpentier.go4lunch.data.repository.UserRepositoryFirestore;
import com.xavier_carpentier.go4lunch.domain.usecase.AddRestaurantChoiceToDayUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.DeleteRestaurantChoiceToDayUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetIfWorkmateEatInThisRestaurantUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetListWorkmateToEatByIdRestaurantUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetRestaurantByIdUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.LikeRestaurantUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantDetail;
import com.xavier_carpentier.go4lunch.presentation.model.Workmate;

import java.util.List;
import java.util.Objects;

public class DetailRestaurantViewModel extends ViewModel {
    //------------------------------------
    // DATA
    //------------------------------------
    private final AuthRepositoryFirebase authRepositoryFirebase = AuthRepositoryFirebase.getInstance();
    private final PlaceRepositoryRetrofit placeRepositoryRetrofit = new PlaceRepositoryRetrofit(RetrofitService.getPlaceApi());
    private final UserRepositoryFirestore userRepositoryFirestore = UserRepositoryFirestore.getInstance();
    private final FavorisRestaurantRepositoryFirestore favorisRestaurantRepositoryFirestore = FavorisRestaurantRepositoryFirestore.getInstance();
    private LiveData<Boolean> eatHere;
    private LiveData<RestaurantDetail> restaurantDetail;
    private LiveData<List<Workmate>> listWorkmates;
    private LiveData<Boolean> isLike = new MutableLiveData<>();
    private String idrestaurant;
    //----------------------------------------------------
    //UseCase
    //----------------------------------------------------
    private final AddRestaurantChoiceToDayUseCase addRestaurantChoiceToDayUseCase = new AddRestaurantChoiceToDayUseCase(userRepositoryFirestore, authRepositoryFirebase);
    private final DeleteRestaurantChoiceToDayUseCase deleteRestaurantChoiceToDayUseCase = new DeleteRestaurantChoiceToDayUseCase(userRepositoryFirestore, authRepositoryFirebase);
    private final GetIfWorkmateEatInThisRestaurantUseCase getIfWorkmateEatInThisRestaurantUseCase = new GetIfWorkmateEatInThisRestaurantUseCase(userRepositoryFirestore,authRepositoryFirebase);
    private final GetListWorkmateToEatByIdRestaurantUseCase getListWorkmateByIdRestaurantUseCase = new GetListWorkmateToEatByIdRestaurantUseCase(userRepositoryFirestore);
    private final LikeRestaurantUseCase likeRestaurantUseCase = new LikeRestaurantUseCase(favorisRestaurantRepositoryFirestore,authRepositoryFirebase);
    private final GetRestaurantByIdUseCase getRestaurantByIdUseCase = new GetRestaurantByIdUseCase(placeRepositoryRetrofit,likeRestaurantUseCase);



    private void initLiveDataWorkmatesToEat(){
        listWorkmates=getListWorkmateByIdRestaurantUseCase.invoke(idrestaurant);
    }

    private void initRestaurantDetail(String uidRestaurant){
        idrestaurant = uidRestaurant;
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
        for(int i = 0; i< Objects.requireNonNull(restaurantDetail.getValue()).getNote()&&i<4; i++){
            if(i!=1 && i!=3) {
                noteToWrite.append("⭐");
            }
        }
        return noteToWrite;
    }

    public String getTypeAndAddress(){
        //String typeAndAddress = String.format(String.valueOf(R.string.type_and_adress),restaurantDetail.getTypeRestaurant(),restaurantDetail.getAddress());
        return " restaurant - "+ Objects.requireNonNull(restaurantDetail.getValue()).getAddress();
    }

    public String getPhoneNumber() {
        return Objects.requireNonNull(restaurantDetail.getValue()).getPhone_number();
    }

    public String getWebsiteUrl() {
        return Objects.requireNonNull(restaurantDetail.getValue()).getWebSite();
    }

    public void OnLikeCLick(){
        if(Objects.requireNonNull(restaurantDetail.getValue()).isLike()){
            likeRestaurantUseCase.remove(idrestaurant);
            //boolean isremove = likeRestaurantUseCase.remove(idrestaurant).getValue();
            //isLike.setValue(!isremove);
        }else{
            likeRestaurantUseCase.add(idrestaurant);
            //isLike.setValue(likeRestaurantUseCase.add(idrestaurant).getValue());
        }
    }

    public LiveData<Boolean> choiceToEatHere(String restaurantID) {
        eatHere = getIfWorkmateEatInThisRestaurantUseCase.invoke(restaurantID);
        return eatHere;
    }

    public void onFavorisClick() {
        if(Boolean.TRUE.equals(eatHere.getValue())){
            deleteRestaurantChoiceToDayUseCase.invoke();
        }else{
            addRestaurantChoiceToDayUseCase.invoke(idrestaurant, Objects.requireNonNull(restaurantDetail.getValue()).getName(),restaurantDetail.getValue().getAddress());
        }

    }
}
