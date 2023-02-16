package com.example.genericdawawalauser.firebase;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.activities.HomeActivity;
import com.example.genericdawawalauser.activities.MainActivity;
import com.example.genericdawawalauser.activities.agora.AudioCallActivity;
import com.example.genericdawawalauser.activities.agora.VideoCallActivity;
import com.example.genericdawawalauser.utils.App;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;
import java.util.Random;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMessagingServ";
    private final static String default_notification_channel_id = "default";
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
                    Objects.requireNonNull(remoteMessage.getData().get(URLBuilder.Parameter.type.toString())), "", bigContent,
                    remoteMessage.getData().get(URLBuilder.Parameter.token.toString()),
                    remoteMessage.getData().get(URLBuilder.Parameter.docImage.toString()),
                    remoteMessage.getData().get(URLBuilder.Parameter.doctorName.toString()),
                    remoteMessage.getData().get(URLBuilder.Parameter.docId.toString()),
                    remoteMessage.getData().get(URLBuilder.Parameter.appId.toString()));

        } else {

            showNotification(remoteMessage.getData().get(URLBuilder.Parameter.title.toString()),
                    remoteMessage.getData().get(URLBuilder.Parameter.message.toString()), "",
                    Objects.requireNonNull(remoteMessage.getData().get(URLBuilder.Parameter.type.toString())), "", bigContent);
            Log.d("LOGCAT", "" + remoteMessage.getData().get(URLBuilder.Parameter.token.toString()));

        }


    }

    private void showNotification(String title, String message, String sx, String type, String image, String bigContent) {
        Intent intent = null;
        if (!type.equalsIgnoreCase("")) {
            intent = new Intent(this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(HomeActivity.data_key, "1");
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 101, intent,
                PendingIntent.FLAG_IMMUTABLE);
        // defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        final NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Random random = new Random();
        final int m = random.nextInt(9999 - 1000) + 1000;


        if (!type.equalsIgnoreCase("")) {

            if (type.equalsIgnoreCase(URLBuilder.Type.audio.toString())) {
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

            if (type.equalsIgnoreCase(URLBuilder.Type.video.toString())) {
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        notificationManagerCompat.notify(101, builder.build());
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    private void setBookingOreoNotification(String title, String message, String type, String image,
                                            String bigContent, String token, String docName,
                                            String docImage, String docID, String appId) {
        PendingIntent pendingIntent = null;
        Intent intent = null;
        if (!type.equalsIgnoreCase("")) {
            if (type.equalsIgnoreCase(URLBuilder.Type.audio.toString())) {
                intent = new Intent(this, AudioCallActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(HomeActivity.data_key, "1");

                App.getSingleton().setToken(token);
                App.getSingleton().setAppId(appId);
                App.getSingleton().setDoctorName(docName);
                App.getSingleton().setProfileImage(docImage);

                intent.putExtra("docName", docName);
                intent.putExtra("token", token);
                intent.putExtra("docImage", docImage);
                intent.putExtra("docID", docID);


                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.ring);
                mp.start();
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, default_notification_channel_id)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Test")
                        .setContentText("Hello! This is my first push notification");
                NotificationManager mNotificationManager = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify((int) System.currentTimeMillis(),
                        mBuilder.build());

                Log.d("CALLINGToken", "" + token);
                Log.d("CALLINGToken", "" + docName);

                pendingIntent = PendingIntent.getActivity(this, 101,
                        intent, PendingIntent.FLAG_IMMUTABLE);


            } else if (type.equalsIgnoreCase(URLBuilder.Type.video.toString())) {

                Bundle bundle = new Bundle();
                bundle.putString("data_key", "1");

                intent = new Intent(this, VideoCallActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(HomeActivity.data_key, "1");
                pendingIntent = PendingIntent.getActivity(this, 101,
                        intent, PendingIntent.FLAG_IMMUTABLE);


                App.getSingleton().setToken(token);
                App.getSingleton().setAppId(appId);
                App.getSingleton().setDoctorName(docName);
                App.getSingleton().setProfileImage(docImage);

                intent.putExtra("docName", docName);
                intent.putExtra("token", token);
                intent.putExtra("docImage", docImage);
                intent.putExtra("docID", docID);


                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.ring);
                mp.start();
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, default_notification_channel_id)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Test")
                        .setContentText("Hello! This is my first push notification");
                NotificationManager mNotificationManager = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify((int) System.currentTimeMillis(),
                        mBuilder.build());


            }

//            else if (type.equalsIgnoreCase(URLBuilder.Type.userLive.toString())) {
//
//
//                intent = new Intent(this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra(HomeActivity.data_key, "1");
//
//
//                App.getSingletone().setLiveType("");
//
//                intent.putExtra("channelName", channelName);
//
//                intent.putExtra("liveHostIds", userId);
//                intent.putExtra("liveType", "multiLive");
//                intent.putExtra("liveStatus", "host");
//                intent.putExtra("token", token);
////
////                if(liveHostId.equalsIgnoreCase(AppConstants.USER_ID)){
////
////                    intent.putExtra("name",name1);
////                    intent.putExtra("userId", userId);
////                }else{
////                    intent.putExtra("name",foller_username);
////                    intent.putExtra("userId", followerId);
////                }
////
////                intent.putExtra("liveHostId", userId);
////
////                if (broadTitle != null && !broadTitle.isEmpty()) {
////                    intent.putExtra("broadTitle", broadTitle);
////                } else {
////                    if(liveHostId.equalsIgnoreCase(AppConstants.USER_ID)){
////                        intent.putExtra("broadTitle", name1);
////                    }else{
////                        intent.putExtra("broadTitle", foller_username);
////                    }
////
////                }
////
////                if (liveimage.isEmpty()) {
////
////                    intent.putExtra("image", userProfileImage);
////                } else {
////                    intent.putExtra("image",liveimage);
////                }
////                intent.putExtra("status", "1");
////
////                intent.putExtra("dob", CommonUtils.ageCalcualte(dob));
////                intent.putExtra("gender",gender);
////
////                intent.putExtra("userDob", CommonUtils.ageCalcualte(userDob));
////                intent.putExtra("UserGender", UserGender);
//
//
//                pendingIntent = PendingIntent.getActivity(this, 101,
//                        intent, PendingIntent.FLAG_IMMUTABLE);
//
//            } else if (type.equalsIgnoreCase(URLBuilder.Type.chatNotification.toString())) {
//
//                Log.d("MYFIREBASE","name "+notifyBy +" id "+notifyById +" notifyByImage "+notifyByImage);
//
//                Bundle bundle = new Bundle();
//                bundle.putString("data_key", "1");
//                bundle.putString("otherUserId",notifyById);
//                bundle.putString("otherUserImg", notifyByImage);
//                bundle.putString("otherUserName", notifyBy);
//                bundle.putString("notification", "1");
//
//
//                pendingIntent = new NavDeepLinkBuilder(this)
//                        .setComponentName(HomeActivity.class)
//                        .setGraph(R.navigation.home_graph)
//                        .setDestination(R.id.messagesFragment)
//                        .setArguments(bundle)
//                        .createPendingIntent();
//
//            }

        } else {
            Log.d("MYFIREBASESERIVE", "Type null");
        }


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

                if (type.equalsIgnoreCase(URLBuilder.Type.audio.toString())) {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setChannelId(CHANNEL_ID)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.ic_notification);

                } else if (type.equalsIgnoreCase(URLBuilder.Type.video.toString())) {

                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setChannelId(CHANNEL_ID)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.ic_notification);
                }

//
//                else if (type.equalsIgnoreCase(URLBuilder.Type.userLive.toString())) {
//
//                    builder.setStyle(new Notification.BigTextStyle(builder)
//                                    .setBigContentTitle(bigContent)
//                                    .setSummaryText("Notification"))
//                            .setContentTitle(title)
//                            .setContentText(message)
//                            .setVibrate(new long[]{200, 200, 200, 200})
//                            .setChannelId(CHANNEL_ID)
//                            .setContentIntent(pendingIntent)
//                            .setGroup(type)
//                            .setAutoCancel(true)
//                            .setSmallIcon(R.drawable.ic_notification);
//                }
//
//                else if (type.equalsIgnoreCase(URLBuilder.Type.chatNotification.toString())) {
//
//                    builder.setStyle(new Notification.BigTextStyle(builder)
//                                    .setBigContentTitle(bigContent)
//                                    .setSummaryText("Notification"))
//                            .setContentTitle(title)
//                            .setContentText(message)
//                            .setVibrate(new long[]{200, 200, 200, 200})
//                            .setChannelId(CHANNEL_ID)
//                            .setContentIntent(pendingIntent)
//                            .setGroup(type)
//                            .setAutoCancel(true)
//                            .setSmallIcon(R.drawable.ic_notification);
//                } else {
//                    builder.setStyle(new Notification.BigTextStyle(builder)
//                                    .setBigContentTitle(bigContent)
//                                    .setSummaryText("Notification"))
//                            .setContentTitle(title)
//                            .setContentText(message)
//                            .setVibrate(new long[]{200, 200, 200, 200})
//                            .setChannelId(CHANNEL_ID)
////                        .setContentIntent(pendingIntent)
//                            .setGroup(type)
//                            .setAutoCancel(true)
//                            .setSmallIcon(R.drawable.ic_notification);
//                }
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

            if (type.equalsIgnoreCase(URLBuilder.Type.audio.toString())) {
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
            } else if (type.equalsIgnoreCase(URLBuilder.Type.video.toString())) {

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


