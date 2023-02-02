package com.example.genericdawawalauser.firebase;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.activities.HomeActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;
import java.util.Random;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMessagingServ";
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private final static String default_notification_channel_id = "default";
    private final int NOTIFICATION_ID = 10;
    private NotificationChannel channel = null;
    private Uri defaultSound;
    private Notification notification;
    private NotificationChannel mChannel;
    private Intent notificationIntent;
    private Intent voiceCutIntent;
    private String checkType = "chatNotification";
    String bigContent;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            setBookingOreoNotification(remoteMessage.getData().get(URLBuilder.Parameter.title.toString()),
                    remoteMessage.getData().get(URLBuilder.Parameter.message.toString()),
                    Objects.requireNonNull(remoteMessage.getData().get(URLBuilder.Parameter.type.toString())), "", bigContent);

        } else {

            showNotification(remoteMessage.getData().get(URLBuilder.Parameter.title.toString()),
                    remoteMessage.getData().get(URLBuilder.Parameter.message.toString()), "",
                    Objects.requireNonNull(remoteMessage.getData().get(URLBuilder.Parameter.type.toString())), "", bigContent);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    private void showNotification(String title, String message, String sx, String type, String image, String bigContent) {
        Intent intent = null;
        if (!type.equalsIgnoreCase("")) {
            intent = new Intent(this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(HomeActivity.data_key, "1");
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 101, intent,
                PendingIntent.FLAG_IMMUTABLE);
        defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        final NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Random random = new Random();
        final int m = random.nextInt(9999 - 1000) + 1000;
        if (!type.equalsIgnoreCase("")) {

            if (type.equalsIgnoreCase(URLBuilder.Type.patientAdmitted.toString())) {
                final Notification.Builder builder = new Notification.Builder(getApplicationContext());
                Notification.BigTextStyle textStyle = new Notification.BigTextStyle(builder);
                if (image.equalsIgnoreCase("")) {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .bigText(sx)
                                    .setBigContentTitle("Chat Notification")
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setSmallIcon(R.drawable.ic_notification);

                    notificationManager.notify(m, builder.build());
                } else {
                    Notification.BigPictureStyle pictureStyle = new Notification.BigPictureStyle(builder);
                    builder.setStyle(pictureStyle
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
                            .setSmallIcon(R.drawable.ic_notification);


                }
            } else {
                final Notification.Builder builder = new Notification.Builder(getApplicationContext());
                Notification.BigTextStyle textStyle = new Notification.BigTextStyle(builder);
                if (image.equalsIgnoreCase("")) {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .bigText(sx)
                                    .setBigContentTitle("Chat Notification")
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
//                             .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setSmallIcon(R.drawable.ic_notification);

                    notificationManager.notify(m, builder.build());
                } else {
                    Notification.BigPictureStyle pictureStyle = new Notification.BigPictureStyle(builder);
                    builder.setStyle(pictureStyle
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setAutoCancel(true)
//                             .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
                            .setSmallIcon(R.drawable.ic_notification);


                }
            }

        }

        if (!type.equalsIgnoreCase("")) {

            if (type.equalsIgnoreCase(URLBuilder.Type.NewAdmissionCase.toString())) {
                final Notification.Builder builder = new Notification.Builder(getApplicationContext());
                Notification.BigTextStyle textStyle = new Notification.BigTextStyle(builder);
                if (image.equalsIgnoreCase("")) {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .bigText(sx)
                                    .setBigContentTitle("Chat Notification")
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setSmallIcon(R.drawable.ic_notification);

                    notificationManager.notify(m, builder.build());
                } else {
                    Notification.BigPictureStyle pictureStyle = new Notification.BigPictureStyle(builder);
                    builder.setStyle(pictureStyle
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
                            .setSmallIcon(R.drawable.ic_notification);


                }
            } else {
                final Notification.Builder builder = new Notification.Builder(getApplicationContext());
                Notification.BigTextStyle textStyle = new Notification.BigTextStyle(builder);
                if (image.equalsIgnoreCase("")) {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .bigText(sx)
                                    .setBigContentTitle("Chat Notification")
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
//                             .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setSmallIcon(R.drawable.ic_notification);

                    notificationManager.notify(m, builder.build());
                } else {
                    Notification.BigPictureStyle pictureStyle = new Notification.BigPictureStyle(builder);
                    builder.setStyle(pictureStyle
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setAutoCancel(true)
//                             .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
                            .setSmallIcon(R.drawable.ic_notification);


                }
            }

        } else {
            notification = new NotificationCompat.Builder(this)
                    .setGroup(type)
                    .setContentText(message)
                    .setContentTitle(title)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_notification)
                    .setAutoCancel(true)
                    .setVibrate(new long[]{200, 200, 200, 200})
                    .setSound(defaultSound)
                    .build();
            notificationManager.notify(m, notification);
        }
    }

    public void setNotification(String title, String body) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "2").setSmallIcon(R.drawable.ic_notification).setContentTitle(title).setContentText(body).setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(101, builder.build());
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    private void setBookingOreoNotification(String title, String message, String type, String image, String bigContent) {
        Intent intent = null;
        if (!type.equalsIgnoreCase("")) {

            intent = new Intent(this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(HomeActivity.data_key, "1");
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 101,
                intent, PendingIntent.FLAG_ONE_SHOT);


// Sets an ID for the notification, so it can be updated.
        String CHANNEL_ID = "my_channel_01";// The id of the channel.
        CharSequence name = "@MyPocketHealth ";// The user-visible name of the channel.

        int importance = NotificationManager.IMPORTANCE_HIGH;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
        }
        final Notification.Builder builder = new Notification.Builder(getApplicationContext());
// Create a notification and set the notification channel.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setGroup(type)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setChannelId(CHANNEL_ID)
                    .build();

            if (!type.equalsIgnoreCase("")) {

                if (type.equalsIgnoreCase(URLBuilder.Type.patientAdmitted.toString())) {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setChannelId(CHANNEL_ID)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setSmallIcon(R.drawable.ic_notification);
                } else {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setChannelId(CHANNEL_ID)
//                        .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setSmallIcon(R.drawable.ic_notification);
                }

                if (type.equalsIgnoreCase(URLBuilder.Type.NewAdmissionCase.toString())) {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setChannelId(CHANNEL_ID)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setSmallIcon(R.drawable.ic_notification);
                } else {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setChannelId(CHANNEL_ID)
//                        .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setSmallIcon(R.drawable.ic_notification);
                }


            }

        }
        final NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotificationManager.createNotificationChannel(mChannel);
        }

// Issue the notification.
        Random random = new Random();
        final int m = random.nextInt(9999 - 1000) + 1000;

        if (!type.equalsIgnoreCase("")) {

            if (type.equalsIgnoreCase(URLBuilder.Type.patientAdmitted.toString())) {
                if (image.equalsIgnoreCase("")) {
                    mNotificationManager.notify(m, builder.build());
                } else {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(message)
                                    .setSummaryText("Notification"))
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSmallIcon(R.drawable.ic_notification);
                }
            } else {
                if (image.equalsIgnoreCase("")) {
                    mNotificationManager.notify(m, builder.build());
                } else {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(message)
                                    .setSummaryText("Notification"))
                            .setAutoCancel(true)
//                        .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSmallIcon(R.drawable.ic_notification);

                }
            }


            if (type.equalsIgnoreCase(URLBuilder.Type.NewAdmissionCase.toString())) {
                if (image.equalsIgnoreCase("")) {
                    mNotificationManager.notify(m, builder.build());
                } else {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(message)
                                    .setSummaryText("Notification"))
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSmallIcon(R.drawable.ic_notification);
                }
            } else {
                if (image.equalsIgnoreCase("")) {
                    mNotificationManager.notify(m, builder.build());
                } else {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(message)
                                    .setSummaryText("Notification"))
                            .setAutoCancel(true)
//                        .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSmallIcon(R.drawable.ic_notification);

                }
            }

        } else {

            mNotificationManager.notify(m, notification);

        }
    }

    public class LatestFirebaseMessagingService extends FirebaseMessagingService {

        @Override
        public void onNewToken(String mToken) {
            super.onNewToken(mToken);

        }

        @Override
        public void onMessageReceived(RemoteMessage remoteMessage) {
            super.onMessageReceived(remoteMessage);
        }
    }
}


