package com.xavier_carpentier.go4lunch.presentation.notification;

import static com.xavier_carpentier.go4lunch.presentation.ui.detail_restaurant.DetailRestaurantActivity.KEY_RESTAURANT;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.xavier_carpentier.go4lunch.R;
import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;
import com.xavier_carpentier.go4lunch.data.repository.UserRepositoryFirestore;
import com.xavier_carpentier.go4lunch.domain.notification.GetNotificationUseCase;
import com.xavier_carpentier.go4lunch.presentation.ui.detail_restaurant.DetailRestaurantActivity;

import java.util.List;


@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class NotificationService extends FirebaseMessagingService{

    private final int NOTIFICATION_ID = 7;
    private final String NOTIFICATION_TAG = "FIREBASEGO4LUNCH";
    private final String CHANNEL_ID = "to_eat_channel";
    private final AuthRepositoryFirebase authRepositoryFirebase = AuthRepositoryFirebase.getInstance();
    private final UserRepositoryFirestore userRepositoryFirestore = UserRepositoryFirestore.getInstance();

    GetNotificationUseCase getNotificationUseCase = new GetNotificationUseCase(authRepositoryFirebase,userRepositoryFirestore);

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() != null) {

            NotificationUi notification = getNotificationUseCase.invoke();
            sendVisualNotification(notification);
        }
    }
    private void sendVisualNotification(NotificationUi notification) {

        StringBuilder textBuilder = new StringBuilder();

        List<String> workmates = notification.getWorkmateToEatInThisRestaurant();
        String restaurantName = notification.getRestaurantName();
        String restaurantVicinity = notification.getRestaurantVicinity();
        String currentUser = notification.getNameWorkmate();

        if(restaurantName!=null && restaurantVicinity != null) {

            textBuilder.append(String.format(String.valueOf(R.string.notification),currentUser,restaurantName,restaurantVicinity));
            if(!workmates.isEmpty()){
                textBuilder.append(R.string.with);
                if(workmates.size()==1){
                    textBuilder.append(workmates.get(0));
                }else {
                    for (int i = 0; workmates.size()>i; i++) {
                        if ((workmates.size()-1) == i) {
                            textBuilder.append(R.string.with).append(workmates.get(i));
                        }
                        if(i== 0){
                            textBuilder.append(workmates.get(i));
                        }else{
                            textBuilder.append(", ").append(workmates.get(i));
                        }

                    }
                }
            }


            // Create an Intent that will be shown when user will click on the Notification

            Intent intent = new Intent(this, DetailRestaurantActivity.class);
            intent.putExtra(KEY_RESTAURANT, notification.getIdRestaurant());
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);


            // Build a Notification object
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this, CHANNEL_ID)
                            .setSmallIcon(R.mipmap.ic_go4lunch)
                            .setContentTitle(getString(R.string.notification_title))
                            .setContentText(textBuilder)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            // Support Version >= Android 8
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence channelName = "Go4Lunch Messages";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, channelName, importance);
                notificationManager.createNotificationChannel(mChannel);
            }

            // Show notification
            notificationManager.notify(NOTIFICATION_TAG, NOTIFICATION_ID, notificationBuilder.build());
        }
    }
}
