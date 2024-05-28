package com.xavier_carpentier.go4lunch.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.xavier_carpentier.go4lunch.domain.repository.FavorisRestaurantRepository;

import java.util.ArrayList;
import java.util.List;

public class FavorisRestaurantRepositoryFirestore implements FavorisRestaurantRepository {
    private static final String COLLECTION_NAME = "users";
    private static final String UID_RESTAURANT_FAVORIS_FIELD = "uidRestaurantFavoris";
    MutableLiveData<List<String>> listFavorisMutableLiveData = new MutableLiveData<>();

    // Get the Collection Reference
    private CollectionReference getUsersCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }
    @Override
    public LiveData<List<String>> getRestaurantFavorisByIdUser(String idUser){
        List<String> listRestaurantId = new ArrayList<>();
        getUsersCollection().document(idUser).collection(UID_RESTAURANT_FAVORIS_FIELD).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    listRestaurantId.add(document.toObject(String.class));
                }
                listFavorisMutableLiveData.setValue(listRestaurantId);
            }else {
                Log.d("Error", "Error getting documents (RestaurantFavorisByIdUser) : ", task.getException());
            }

        }).addOnFailureListener(e -> listFavorisMutableLiveData.setValue(null));
        return listFavorisMutableLiveData;
    }

    @Override
    public void addRestaurantFavoris(String idUser, String idRestaurant){
        getUsersCollection().document(idUser)
                .update(UID_RESTAURANT_FAVORIS_FIELD, FieldValue.arrayUnion(idRestaurant));


    }

    @Override
    public void deleteRestaurantFavoris(String idUser, String idRestaurant){
        getUsersCollection().document(idUser)
                .update(UID_RESTAURANT_FAVORIS_FIELD, FieldValue.arrayRemove(idRestaurant));
    }
}
