package com.example.dell.myapplication.alert;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.myapplication.ApiClient;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.adapter.AlertAdapter;
import com.example.dell.myapplication.api.RegisterAPI;
import com.example.dell.myapplication.model.Crud;
import com.example.dell.myapplication.model.Value;
import com.example.dell.myapplication.notif.Notification;
import com.example.dell.myapplication.notif.NotificationReceiver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlertActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private AlertAdapter viewAlertAdapter;

    private CheckBox CBearthquake, CBfire, CBflood, CBbomb, CBshooter;
    private Button buttonAlert;
    private int userID;
    private ImageView image;

    public static List<Crud> alertitems;


    ArrayList<String> selection = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_activity);

        notificationManager = NotificationManagerCompat.from(this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AlertActivity.this);
        userID = prefs.getInt("ID", 0);

        image = (ImageView)findViewById(R.id.imgCalamity);
        CBearthquake = (CheckBox) findViewById(R.id.cbEartquake);
        CBfire = (CheckBox) findViewById(R.id.cbFire);
        CBflood = (CheckBox) findViewById(R.id.cbFlood);
        CBbomb = (CheckBox) findViewById(R.id.cbBomb);
        CBshooter = (CheckBox)findViewById(R.id.cbShooter);
        buttonAlert = (Button) findViewById(R.id.btnAlert);
        buttonAlert.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               for(int i=0; i<selection.size(); i++) {
                   Retrofit retrofit = new Retrofit.Builder()
                           .baseUrl(ApiClient.BASE_URL)
                           .addConverterFactory(GsonConverterFactory.create())
                           .build();

                   RegisterAPI api = retrofit.create(RegisterAPI.class);

                   Call<Value> call = api.alertUsers(Integer.parseInt(selection.get(i)), userID);
                   call.enqueue(new Callback<Value>() {
                       @Override
                       public void onResponse(Call<Value> call, Response<Value> response) {

                           if (response.isSuccessful()){
                               Toast.makeText(AlertActivity.this, "Report sent.", Toast.LENGTH_SHORT).show();

                           } else {
                               Toast.makeText(AlertActivity.this, "Report failed to send.", Toast.LENGTH_SHORT).show();
                           }
                       }

                       @Override
                       public void onFailure(Call<Value> call, Throwable t) {
                           t.printStackTrace();

                           System.out.println("NADINE " +  t.getMessage());
                           Toast.makeText(AlertActivity.this, "Error", Toast.LENGTH_SHORT).show();
                       }
                   });
               }
               sendNotif();
              Intent i = new Intent(AlertActivity.this, AnotherAlertActivity.class);
              startActivity(i);

           }
       });

       CBearthquake.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(CBearthquake.isChecked())
               {
                   selection.add("1");

               }else{
                   selection.remove("1");
               }
           }
       });

        CBfire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CBfire.isChecked())
                {
                    selection.add("2");
                }else{

                    selection.remove("2");
                }
            }
        });
        CBflood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CBflood.isChecked())
                {
                    selection.add("3");
                }else{

                    selection.remove("3");
                }
            }
        });
        CBbomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CBbomb.isChecked())
                {
                    selection.add("4");
                }else{

                    selection.remove("4");
                }
            }
        });
       CBshooter.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(CBshooter.isChecked())
               {
                   selection.add("5");
               }else{

                   selection.remove("5");
               }
           }
       });
    }

    public void sendNotif() {
        long[] v = {500,1000};

        Intent activityIntent = new Intent(this, ViewAlertActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);

        Intent broadcastIntent = new Intent (this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", "There is a calamity happening");
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0,
                broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        android.app.Notification notification =  new NotificationCompat.Builder(this, Notification.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_looks_one_black_24dp)
                .setContentTitle("Alert")
                .setContentText("There is a calamity happening!")
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


}

