package com.example.dell.myapplication.alert;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.myapplication.ApiClient;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.ViewActivity;
import com.example.dell.myapplication.api.RegisterAPI;
import com.example.dell.myapplication.login.MainActivity;
import com.example.dell.myapplication.login.WelcomeFragment;
import com.example.dell.myapplication.model.Instruction;
import com.example.dell.myapplication.model.Notif;
import com.example.dell.myapplication.model.PushNotif;
import com.example.dell.myapplication.model.SafeExits;

import java.io.IOException;
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
    private int userID;
    EditText instruction;

    CheckBox MFCExit, backgateExit, mainExit, mainGateExit, LRTExit;
    Button btnSendToUsers, btnUncheck;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_blueprint);

       /* SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AnotherAlertActivity.this);
        userID = prefs.getInt("ID", 0);
        */

        notificationManager = NotificationManagerCompat.from(this);
        setTitle("Safe Exits - Blueprint");

        MFCExit = (CheckBox) findViewById(R.id.MFCExit);
        backgateExit = (CheckBox) findViewById(R.id.backgateExit);
        mainExit = (CheckBox) findViewById(R.id.mainExit);
        mainGateExit = (CheckBox) findViewById(R.id.mainGateExit);
        LRTExit = (CheckBox) findViewById(R.id.LRTExit);

        instruction = (EditText) findViewById(R.id.instruc1);

        btnUncheck = (Button) findViewById(R.id.btnUncheck);
        btnUncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnUncheck();
            }
        });

        btnSendToUsers = (Button) findViewById(R.id.btnSendToUsers);
        btnSendToUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AnotherAlertActivity.this);
                alertDialogBuilder.setTitle("Warning");
                alertDialogBuilder
                        .setMessage("Do you wish to continue?")
                        .setCancelable(false)
                        .setPositiveButton("Alert", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String ins = instruction.getText().toString();
                                try {
                                    setInstruction(1, ins);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                Toast.makeText(AnotherAlertActivity.this, "Alert sent!", Toast.LENGTH_SHORT).show();
                                sendPushNotifs();
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

        instruct();
        getExits();

    }

    public void btnUncheck() {
        MFCExit.setChecked(false);
        backgateExit.setChecked(false);
        mainExit.setChecked(false);
        mainGateExit.setChecked(false);
        LRTExit.setChecked(false);

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

    public void setInstruction(int exitID, String instruction) throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<String> call = api.sendMessage(exitID, instruction);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Toast.makeText(AnotherAlertActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(AnotherAlertActivity.this, "Error" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void instruct() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<List<Instruction>> call = api.getMessage();
        call.enqueue(new Callback<List<Instruction>>() {
            @Override
            public void onResponse(Call<List<Instruction>> call, Response<List<Instruction>> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: " + response.body());
                    final List<Instruction> ins = response.body();
                    for (final Instruction value : ins) {
                        if (value.getExitID().equals("1")) {
                            instruction.setText(value.getInstruction());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Instruction>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    public void sendPushNotifs(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.FIREBASE_PUSH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Notif notif = new Notif();
        notif.setTitle("ALERT");
        notif.setText("See Calamity");

        PushNotif pushNotif = new PushNotif();
        pushNotif.setTo("/topics/alert");
        pushNotif.setNotif(notif);

        Call<ResponseBody> call = api.pushNotif(pushNotif);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: " + response.body().toString());
                    Toast.makeText(getApplicationContext(), "NEW ALERT NOTIFICATION", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}