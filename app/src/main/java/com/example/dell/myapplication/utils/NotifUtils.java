//package com.example.dell.myapplication.utils;
//
//import android.app.Activity;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Intent;
//import android.support.v4.app.NotificationCompat;
//import android.support.v4.app.NotificationManagerCompat;
//
//import com.example.dell.myapplication.R;
//import com.example.dell.myapplication.UsersActivity;
//import com.example.dell.myapplication.alert.ViewAlertActivity;
//import com.example.dell.myapplication.notif.Notification;
//
//public class NotifUtils {
//    public static void sendNotif(Activity activity, NotificationManagerCompat notificationManager) {
//
//        long[] v = {500,1000};
//
//        Intent activityIntent = new Intent(activity, ViewAlertActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(activity,
//                0, activityIntent, 0);
//
//        Intent broadcastIntent = new Intent (activity, UsersActivity.class);
//
//        broadcastIntent.putExtra("BEAP", "There is a calamity happening");
//        PendingIntent actionIntent = PendingIntent.getBroadcast (activity, 0,
//                broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        android.app.Notification notification =  new NotificationCompat.Builder(activity, Notification.CHANNEL_1_ID)
//                .setSmallIcon(R.drawable.ic_looks_one_black_24dp)
//                .setContentTitle("Alert")
//                .setContentText("There is a calamity happening!")
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
//                .setContentIntent(contentIntent)
//                .setAutoCancel(true)
//                .setOnlyAlertOnce(true)
//                .setVibrate(v)
//                .setLights(0xff00ff00, 300, 100)
//                .addAction(R.mipmap.ic_launcher, "Toast",  actionIntent)
//                .build();
//
//        notificationManager.notify(2, notification);
//    }
//}
