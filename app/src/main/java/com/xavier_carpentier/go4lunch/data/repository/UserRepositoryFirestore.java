package com.xavier_carpentier.go4lunch.data.repository;

import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;

import java.util.List;

public class UserRepositoryFirestore implements UsersRepository {

    private static final String COLLECTION_NAME = "users";
    private static final String UID_RESTAURANT_FAVORIS_FIELD = "uidRestaurantFavoris";

    private static volatile UserRepositoryFirestore instance;

    //AuthRepositoryFirebase is a Singleton
    public static UserRepositoryFirestore getInstance() {
        UserRepositoryFirestore result = instance;
        if (result != null) {
            return result;
        }
        synchronized(UserRepositoryFirestore.class) {
            if (instance == null) {
                instance = new UserRepositoryFirestore();
            }
            return instance;
        }
    }

    @Override
    public void createUserInDataBase(UserDomain user) {
        if(user != null){
            String urlPicture = (user.getUrlPicture() != null) ? user.getUrlPicture() : null;
            String username = user.getUsername();
            String uid = user.getUid();


            UserDomain userToCreate = new UserDomain(uid, username, urlPicture);

            Task<DocumentSnapshot> userData = getUsersCollection().document(uid).get();
            // If the user already exist in Firestore, we get his data (uidRestaurantFavoris)
            userData.addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.contains(UID_RESTAURANT_FAVORIS_FIELD)){
                    userToCreate.setUidRestaurantFavoris((List<String>) documentSnapshot.get(UID_RESTAURANT_FAVORIS_FIELD));
                }
                this.getUsersCollection().document(uid).set(userToCreate);
            });
        }
    }

    // Get the Collection Reference
    private CollectionReference getUsersCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    //TODO
   // public LiveData<List<UserDomain>> getAllUsers(){
   //     return FirebaseFirestore.getInstance().collection("users").get();
   // }
}
