package com.example.dell.myapplication.alert;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.example.dell.myapplication.adapter.AlertAdapter;
import com.example.dell.myapplication.api.RegisterAPI;
import com.example.dell.myapplication.model.AlertMessage;
import com.example.dell.myapplication.model.Value;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlertActivity extends AppCompatActivity {
    private static final String TAG = "AlertActivity";

    private AlertAdapter viewAlertAdapter;

    private NotificationManagerCompat notificationManager;

    private CheckBox CBearthquake, CBfire, CBflood, CBbomb, CBshooter;
    private Button buttonAlert;
    private int userID;

    ArrayList<String> selection = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_activity);


        notificationManager = NotificationManagerCompat.from(this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AlertActivity.this);
        userID = prefs.getInt("ID", 0);

        CBearthquake = (CheckBox) findViewById(R.id.cbEartquake);
        CBfire = (CheckBox) findViewById(R.id.cbFire);
        CBflood = (CheckBox) findViewById(R.id.cbFlood);
        CBbomb = (CheckBox) findViewById(R.id.cbBomb);
        CBshooter = (CheckBox)findViewById(R.id.cbShooter);
        buttonAlert = (Button) findViewById(R.id.btnAlert);
        CBearthquake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selection.add("1");
                }
                else{
                    selection.remove("1");
                }
            }
        });
        CBfire.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selection.add("2");
                }
                else{
                    selection.remove("2");
                }
            }
        });
        CBflood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selection.add("3");
                }
                else{
                    selection.remove("3");
                }
            }
        });
        CBbomb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selection.add("4");
                }
                else{
                    selection.remove("4");
                }
            }
        });
        CBshooter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selection.add("5");
                }
                else{
                    selection.remove("5");
                }
            }
        });


        buttonAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AlertActivity.this);
                alertDialogBuilder.setTitle("Warning");
                alertDialogBuilder
                        .setMessage("Do you wish to continue?")
                        .setCancelable(false)
                        .setPositiveButton("Alert", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Log.d(TAG, "onClick: " + selection);

                                for(int i=0; i<selection.size(); i++) {
                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(ApiClient.BASE_URL)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    RegisterAPI api = retrofit.create(RegisterAPI.class);
                                    Call<AlertMessage> call = api.alertUsers(userID, String.valueOf(selection.get(i)), 1);
                                    call.enqueue(new Callback<AlertMessage>() {
                                        @Override
                                        public void onResponse(Call<AlertMessage> call, Response<AlertMessage> response) {


                                            if (response.isSuccessful()){
                                                Toast.makeText(AlertActivity.this, "You have selected item/s.", Toast.LENGTH_SHORT).show();

                                            } else {
                                                Toast.makeText(AlertActivity.this, "Report failed to send.", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<AlertMessage> call, Throwable t) {
                                            t.printStackTrace();

                                            System.out.println("NADINE " +  t.getMessage());
                                            Toast.makeText(AlertActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                Intent i = new Intent(AlertActivity.this, AnotherAlertActivity.class);
                                startActivity(i);


//                                CBearthquake.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        if(CBearthquake.isChecked())
//                                        {
//                                            selection.add("1");
//                                        }else{
//                                            selection.remove("1");
//                                        }
//                                    }
//                                });
//                                CBfire.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        if(CBfire.isChecked())
//                                        {
//                                            selection.add("2");
//                                        }else{
//
//                                            selection.remove("2");
//                                        }
//                                    }
//                                });
//                                CBflood.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        if(CBflood.isChecked())
//                                        {
//                                            selection.add("3");
//                                        }else{
//
//                                            selection.remove("3");
//                                        }
//                                    }
//                                });
//                                CBbomb.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        if(CBbomb.isChecked())
//                                        {
//                                            selection.add("4");
//                                        }else{
//
//                                            selection.remove("4");
//                                        }
//                                    }
//                                });
//                                CBshooter.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        if(CBshooter.isChecked())
//                                        {
//                                            selection.add("5");
//                                        }else{
//
//                                            selection.remove("5");
//                                        }
//                                    }
//                                });

                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

    }

}