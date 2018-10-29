package com.example.dell.myapplication.alert;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.dell.myapplication.ApiClient;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.api.RegisterAPI;
import com.example.dell.myapplication.model.SafeExits;
import com.example.dell.myapplication.notif.Notification;
import com.example.dell.myapplication.notif.NotificationReceiver;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnotherAlertActivity extends AppCompatActivity {


    private NotificationManagerCompat notificationManager;
    private static final String TAG = "AdminBlueprint";
    String check;
    CheckBox MFCExit, backgateExit, mainExit, mainGateExit, LRTExit;
    Button btnSendToUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_blueprint);

        notificationManager = NotificationManagerCompat.from(this);
        setTitle("Safe Exits - Blueprint");

        MFCExit = (CheckBox) findViewById(R.id.MFCExit);
        backgateExit = (CheckBox) findViewById(R.id.backgateExit);
        mainExit = (CheckBox) findViewById(R.id.mainExit);
        mainGateExit = (CheckBox) findViewById(R.id.mainGateExit);
        LRTExit = (CheckBox) findViewById(R.id.LRTExit);

        btnSendToUsers = (Button) findViewById(R.id.btnSendToUsers);
        btnSendToUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent i = new Intent(AnotherAlertActivity.this, ViewAnotherAlertActivity.class);
               startActivity(i);

                sendNotif();
                //JUST TO CHECK KUNG NAPAPASA
                //PUSH NOTIF HERE
            }

        });

        getExits();


    }

    public void getExits() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<List<SafeExits>> call = api.getExit();
        call.enqueue(new Callback<List<SafeExits>>() {
            @Override
            public void onResponse(Call<List<SafeExits>> call, Response<List<SafeExits>> response) {
                final List<SafeExits> sl = response.body();

                for (final SafeExits value : sl) {
                    CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            Log.d(TAG, "onCheckedChanged: " + compoundButton.isChecked());
                            if (compoundButton.isChecked()) {
                                setExits(value.getExitID(), 1);
                            } else {
                                setExits(value.getExitID(), 0);
                            }
                        }
                    };

                    if (value.getExitID() == 1) {
                        if (value.getiStatus() == 1) {
                            MFCExit.setChecked(true);
                        }
                        MFCExit.setOnCheckedChangeListener(checkListener);

                    } else if (value.getExitID() == 2) {
                        if (value.getiStatus() == 1) {
                            backgateExit.setChecked(true);
                        }
                        backgateExit.setOnCheckedChangeListener(checkListener);

                    } else if (value.getExitID() == 3) {
                        if (value.getiStatus() == 1) {
                            mainExit.setChecked(true);
                        }
                        mainExit.setOnCheckedChangeListener(checkListener);

                    } else if (value.getExitID() == 4) {
                        if (value.getiStatus() == 1) {
                            mainGateExit.setChecked(true);
                        }
                        mainGateExit.setOnCheckedChangeListener(checkListener);

                    } else if (value.getExitID() == 5) {
                        if (value.getiStatus() == 1) {
                            LRTExit.setChecked(true);
                        }
                        LRTExit.setOnCheckedChangeListener(checkListener);

                    }

                    Log.d("responsebody ", String.valueOf(value.getiStatus()));
                }
            }

            @Override
            public void onFailure(Call<List<SafeExits>> call, Throwable t) {
                Log.d("responsebody", "onFailure: " + t.getMessage());
            }
        });
    }

    public void setExits(int exitID, int iStatus) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<ResponseBody> call = api.updateExit(exitID, iStatus);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body());
                } else {
                    Toast.makeText(AnotherAlertActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(AnotherAlertActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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