package com.xavier_carpentier.go4lunch.data.notification;

import static com.xavier_carpentier.go4lunch.presentation.ui.detail_restaurant.DetailRestaurantActivity.KEY_RESTAURANT;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.xavier_carpentier.go4lunch.R;
import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;
import com.xavier_carpentier.go4lunch.data.repository.UserRepositoryFirestore;
import com.xavier_carpentier.go4lunch.domain.notification.GetNotificationUseCase;
import com.xavier_carpentier.go4lunch.domain.notification.NotificationDomain;
import com.xavier_carpentier.go4lunch.presentation.ui.detail_restaurant.DetailRestaurantActivity;

import java.util.List;

public class NotificationWorker extends Worker {

    public static final String WORK_TAG = "dailyNotificationWork";

    //suppressWarning for more visibility
    @SuppressWarnings("FieldCanBeLocal")
    private final int NOTIFICATION_ID = 7;
    @SuppressWarnings("FieldCanBeLocal")
    private final String NOTIFICATION_TAG = "FIREBASEGO4LUNCH";
    @SuppressWarnings("FieldCanBeLocal")
    private final String CHANNEL_ID = "to_eat_channel";
    private final AuthRepositoryFirebase authRepositoryFirebase = AuthRepositoryFirebase.getInstance();
    private final UserRepositoryFirestore userRepositoryFirestore = UserRepositoryFirestore.getInstance();

    GetNotificationUseCase getNotificationUseCase = new GetNotificationUseCase(authRepositoryFirebase,userRepositoryFirestore);
    private final Context context;

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {

        NotificationDomain notification = getNotificationUseCase.invoke();
        if (notification != null) {
            sendVisualNotification(notification);
        }

        return Result.success();

    }

    private void sendVisualNotification(NotificationDomain notification) {

        StringBuilder textBuilder = new StringBuilder();

        List<String> workmates = notification.getWorkmateToEatInThisRestaurant();
        String restaurantName = notification.getRestaurantName();
        String restaurantVicinity = notification.getRestaurantVicinity();
        String currentUser = notification.getNameWorkmate();

        if(restaurantName!=null && restaurantVicinity != null) {

            textBuilder.append(context.getString(R.string.notification,currentUser,restaurantName,restaurantVicinity));
            if(!workmates.isEmpty()){
                textBuilder.append(context.getString(R.string.with));
                if(workmates.size()==1){
                    textBuilder.append(workmates.get(0));
                }else {
                    for (int i = 0; workmates.size()>i; i++) {
                        if ((workmates.size()-1) == i) {
                            textBuilder.append(context.getString(R.string.and)).append(workmates.get(i));
                        }
                        if(i== 0){
                            textBuilder.append(workmates.get(i));
                        }else{
                            textBuilder.append(", ").append(workmates.get(i));
                        }

                    }
                }
            }

            Log.d("NOTIFICATION", textBuilder.toString());
            // Create an Intent that will be shown when user will click on the Notification

            Intent intent = new Intent(context, DetailRestaurantActivity.class);
            intent.putExtra(KEY_RESTAURANT, notification.getIdRestaurant());
            PendingIntent pendingIntent;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Log.d("NOTIFICATION", "PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE");
                pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            } else {
                Log.d("NOTIFICATION", "PendingIntent.FLAG_UPDATE_CURRENT");
                pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            }


            // Build a Notification object
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(context, CHANNEL_ID)
                            .setSmallIcon(R.mipmap.ic_go4lunch)
                            .setContentTitle(context.getString(R.string.notification_title))
                            .setContentText(textBuilder)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

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
