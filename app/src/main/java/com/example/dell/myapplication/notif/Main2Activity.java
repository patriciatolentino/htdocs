package com.example.dell.myapplication.notif;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dell.myapplication.R;
import com.example.dell.myapplication.alert.ViewAlertActivity;

public class Main2Activity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private EditText txtTitle, txtMessage;
    private Button btn1, btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        notificationManager = NotificationManagerCompat.from(this);

        txtTitle = (EditText) findViewById(R.id.edit_text_title);
        txtMessage = (EditText) findViewById(R.id.edit_text_message);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnChannel1();
            }
        });

    }

    public void sendOnChannel1() {
        String title = txtTitle.getText().toString();
        String message = txtMessage.getText().toString();

        long[] v = {500,1000};


        Intent activityIntent = new Intent(this, ViewAlertActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);

        Intent broadcastIntent = new Intent (this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0,
                broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        android.app.Notification notification =  new NotificationCompat.Builder(this, Notification.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_looks_one_black_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setVibrate(v)
                .addAction(R.mipmap.ic_launcher, "Toast",  actionIntent)
                .build();


        notificationManager.notify(1, notification);
    }
    public void sendOnChannel2() {

        String title = txtTitle.getText().toString();
        String message = txtMessage.getText().toString();

        android.app.Notification notification =  new NotificationCompat.Builder(this, Notification.CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_looks_one_black_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(2, notification);
    }
}
