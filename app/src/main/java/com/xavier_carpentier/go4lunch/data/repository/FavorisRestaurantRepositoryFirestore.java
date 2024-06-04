package com.xavier_carpentier.go4lunch.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.xavier_carpentier.go4lunch.domain.repository.FavorisRestaurantRepository;

import java.util.ArrayList;
import java.util.List;

public class FavorisRestaurantRepositoryFirestore implements FavorisRestaurantRepository {
    private static final String COLLECTION_NAME = "users";
    private static final String UID_RESTAURANT_FAVORIS_FIELD = "uidRestaurantFavoris";
    MutableLiveData<List<String>> listFavorisMutableLiveData = new MutableLiveData<>();

    private static volatile FavorisRestaurantRepositoryFirestore instance;

    //AuthRepositoryFirebase is a Singleton
    public static FavorisRestaurantRepositoryFirestore getInstance() {
        FavorisRestaurantRepositoryFirestore result = instance;
        if (result != null) {
            return result;
        }
        synchronized(AuthRepositoryFirebase.class) {
            if (instance == null) {
                instance = new FavorisRestaurantRepositoryFirestore();
            }
            return instance;
        }
    }

    // Get the Collection Reference
    private CollectionReference getUsersCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }
    @Override
    public LiveData<List<String>> getRestaurantFavorisByIdUser(String idUser){
        List<String> listRestaurantId = new ArrayList<>();
        getUsersCollection().document(idUser).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                List<String> favoris =(List<String>) document.get(UID_RESTAURANT_FAVORIS_FIELD);
                listFavorisMutableLiveData.setValue(favoris);
            }else {
                Log.d("Error", "Error getting documents (RestaurantFavorisByIdUser) : ", task.getException());
            }

        }).addOnFailureListener(e -> listFavorisMutableLiveData.setValue(null));
        return listFavorisMutableLiveData;
    }

   @Override
   public LiveData<Boolean> addRestaurantFavoris(String idUser, String idRestaurant){
       MutableLiveData<Boolean> result = new MutableLiveData<>();
       getUsersCollection().document(idUser)
               .update(UID_RESTAURANT_FAVORIS_FIELD, FieldValue.arrayUnion(idRestaurant))
               .addOnCompleteListener(task ->{
                   if(task.isSuccessful()){
                       result.setValue(true);
                   }else {
                       result.setValue(false);
                   }
               });
       return result;
   }

    @Override
    public LiveData<Boolean> deleteRestaurantFavoris(String idUser, String idRestaurant){
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        getUsersCollection().document(idUser)
                .update(UID_RESTAURANT_FAVORIS_FIELD, FieldValue.arrayRemove(idRestaurant))
                .addOnCompleteListener(task ->{
                    if(task.isSuccessful()){
                        result.setValue(true);
                    }else {
                        result.setValue(false);
                    }
                });
        return result;
    }
}
