package com.xavier_carpentier.go4lunch.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.xavier_carpentier.go4lunch.domain.model.RestaurantChoiceDomain;
import com.xavier_carpentier.go4lunch.domain.model.UserDomain;
import com.xavier_carpentier.go4lunch.domain.repository.UsersRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepositoryFirestore implements UsersRepository {

    private static final String COLLECTION_USER = "users";
    private static final String COLLECTION_RESTAURANT_CHOICE = "restaurantChoice";
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
        return FirebaseFirestore.getInstance().collection(COLLECTION_USER);
    }

    private CollectionReference getRestaurantChoiceCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_RESTAURANT_CHOICE);
    }


    public LiveData<List<UserDomain>> getAllUsers(){
        MutableLiveData<List<UserDomain>> mutableLiveDataUserDomain = new MutableLiveData<>();

        List<UserDomain> userDomainList = new ArrayList<>();

        getUsersCollection().get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                for (QueryDocumentSnapshot document : task.getResult()) {
                    userDomainList.add(document.toObject(UserDomain.class));
                }
                mutableLiveDataUserDomain.setValue(userDomainList);
            }else {
                Log.d("Error", "Error getting documents (allUsers) : ", task.getException());
            }
        }).addOnFailureListener(e -> mutableLiveDataUserDomain.setValue(null));

        return mutableLiveDataUserDomain;
    }



    /*
     * choice between 14h yesterday and now if current time is before 14h today between 14h today and now
     */
    public LiveData<List<RestaurantChoiceDomain>> getAllRestaurantChoiceToDay(){
        MutableLiveData<List<RestaurantChoiceDomain>> liveData = new MutableLiveData<>();

        // Get current time
        Timestamp currentTimestamp = Timestamp.now();
        Date currentDate = currentTimestamp.toDate();

        // Calculate start and end time
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        // Set end time to now
        Timestamp endTime = currentTimestamp;

        // Set start time to 14h yesterday if current time is before 14h today
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (currentDate.before(calendar.getTime())) {
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }


        Date startTimeDate = calendar.getTime();
        Timestamp startTime = new Timestamp(startTimeDate);

        getRestaurantChoiceCollection()
                .whereGreaterThanOrEqualTo("timestamp", startTime)
                .whereLessThanOrEqualTo("timestamp", endTime)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<RestaurantChoiceDomain> choices = new ArrayList<>();
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null) {
                            for (QueryDocumentSnapshot document : querySnapshot) {
                                RestaurantChoiceDomain choice = document.toObject(RestaurantChoiceDomain.class);
                                choices.add(choice);
                            }
                        }
                        liveData.setValue(choices);
                    } else {
                        liveData.setValue(new ArrayList<>()); // or handle the error as needed
                    }
                });

        return liveData;
    }


    public LiveData<Boolean> addRestaurantChoiceToDay(String idUser, String nameUser, String urlUserPicture, String idRestaurant, String nameRestaurant, String vicinity){
        MutableLiveData<Boolean> isSuccess= new MutableLiveData<>();
        if(idUser!= null && idRestaurant!=null){
            Timestamp timestamp = Timestamp.now();

            RestaurantChoiceDomain restaurantChoiceToCreate = new RestaurantChoiceDomain(timestamp,idUser,nameUser,urlUserPicture,idRestaurant,nameRestaurant,vicinity);

            //replaces restaurantChoice existing to restaurantChoiceToCreate
            getRestaurantChoiceCollection().document(idUser).set(restaurantChoiceToCreate).addOnCompleteListener(task->{
                if(task.isSuccessful()){
                    isSuccess.setValue(true);
                }else{
                    isSuccess.setValue(false);
                }
            });
        }
        return isSuccess;

    }

    public LiveData<Boolean> deleteRestaurantChoiceToDay(String idUser){
        MutableLiveData<Boolean> isSuccess= new MutableLiveData<>();
        if(idUser!= null){
            //delete user
            getRestaurantChoiceCollection().document(idUser).delete().addOnCompleteListener(task->{
                if(task.isSuccessful()){
                    isSuccess.setValue(true);
                }else{
                    isSuccess.setValue(false);
                }
            });
        }
        return isSuccess;
    }

    public LiveData<List<RestaurantChoiceDomain>> getListWorkmateToChoiceARestaurant(String idRestaurant){
        MutableLiveData<List<RestaurantChoiceDomain>> liveData = new MutableLiveData<>();

        // Get the current time
        Timestamp now = Timestamp.now();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now.toDate());

        // Set start time to 14h today
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // Determine the start time based on the current time
        Date startTimeDate;
        if (now.toDate().before(calendar.getTime())) {
            // If now is before 14h today, set start time to 14h yesterday
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }

        startTimeDate = calendar.getTime();
        Timestamp startTime = new Timestamp(startTimeDate);

        // Query Firestore for RestaurantChoiceDomain within the time range for the specific restaurant
        getRestaurantChoiceCollection()
                .whereEqualTo("idRestaurant", idRestaurant)
                .whereGreaterThanOrEqualTo("timestamp", startTime)
                .whereLessThanOrEqualTo("timestamp", now)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<RestaurantChoiceDomain> restaurantChoices = queryDocumentSnapshots.toObjects(RestaurantChoiceDomain.class);
                    liveData.setValue(restaurantChoices);
                })
                .addOnFailureListener(e -> {
                    // Handle the error
                    liveData.setValue(null);
                });

        return liveData;

    }



    public List<RestaurantChoiceDomain> getAllRestaurantChoiceToDayAsync(){
        TaskCompletionSource<List<RestaurantChoiceDomain>> taskCompletionSource = new TaskCompletionSource<>();

        // Get current time
        Timestamp currentTimestamp = Timestamp.now();
        Date currentDate = currentTimestamp.toDate();

        // Calculate start and end time
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);


        // Set start time to 14h yesterday if current time is before 14h today
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (currentDate.before(calendar.getTime())) {
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }


        Date startTimeDate = calendar.getTime();
        Timestamp startTime = new Timestamp(startTimeDate);

        getRestaurantChoiceCollection()
                .whereGreaterThanOrEqualTo("timestamp", startTime)
                .whereLessThanOrEqualTo("timestamp", currentTimestamp)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<RestaurantChoiceDomain> choices = new ArrayList<>();
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null) {
                            for (QueryDocumentSnapshot document : querySnapshot) {
                                RestaurantChoiceDomain choice = document.toObject(RestaurantChoiceDomain.class);
                                choices.add(choice);
                            }
                        }
                        if (choices.isEmpty()) {
                            taskCompletionSource.setResult(null);
                        } else {
                            taskCompletionSource.setResult(choices);
                        }

                    } else {
                        if (task.getException() != null)
                            taskCompletionSource.setException(task.getException());
                    }
                });

        try {
            return Tasks.await(taskCompletionSource.getTask());
        } catch (ExecutionException | InterruptedException e) {
            Log.e("UserRepositoryFirestore", "error in getAllRestaurantChoiceToDayAsync : "+ e);
            return null;
        }

    }

}
