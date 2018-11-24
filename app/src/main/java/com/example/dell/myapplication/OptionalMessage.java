package com.example.dell.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.myapplication.alert.AnotherAlertActivity;
import com.example.dell.myapplication.api.RegisterAPI;
import com.example.dell.myapplication.login.WelcomeFragment;
import com.example.dell.myapplication.model.Message;
import com.example.dell.myapplication.model.SafeExits;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OptionalMessage extends AppCompatActivity {

    private static final String TAG = "OptionalMessage" ;
    private EditText messageText;
    private Button  btnSend;
    private int senderID;

    Spinner benilde;
    Spinner solomon;
    Spinner duerr;
    Spinner mfc;
    Spinner mutien;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optional_message);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(OptionalMessage.this);
        senderID = prefs.getInt("ID", 0);

        final Spinner benilde = (Spinner) findViewById(R.id.benilde);
        benilde.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String loc1=benilde.getSelectedItem().toString();
                messageText.setText(loc1);
                Log.d("Selected item1: " ,loc1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        final Spinner duerr = (Spinner) findViewById(R.id.duerr);
        duerr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String loc2=duerr.getSelectedItem().toString();
                messageText.setText(loc2);
                Log.d("Selected item2: " ,loc2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        final Spinner solomon = (Spinner) findViewById(R.id.solomon);
        solomon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String loc3=solomon.getSelectedItem().toString();
                messageText.setText(loc3);
                Log.d("Selected item3: " ,loc3);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        final Spinner mutien = (Spinner) findViewById(R.id.mutien);
        mutien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String loc4=mutien.getSelectedItem().toString();
                messageText.setText(loc4);
                Log.d("Selected item4: " ,loc4);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        final Spinner mfc = (Spinner) findViewById(R.id.mfc);
        mfc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String loc5=mfc.getSelectedItem().toString();
                messageText.setText(loc5);

                Log.d("Selected item5: " ,loc5);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


       // location = (Spinner)findViewById(R.id.spinner);
        messageText = findViewById(R.id.txtMessage);
        btnSend = findViewById(R.id.btnSendLoc);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessage();

//                String loc = location.getSelectedItem().toString();
//                try {
//                    setLocation(1, loc);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
              //  getLocation();

            }
        });
    }
    private void SendMessage(){
        SharedPreferences sharedPreferences = getSharedPreferences("your_prefs", MODE_PRIVATE);
        senderID = sharedPreferences.getInt("ID", 0);
        Log.d(TAG, "SendMessage: " + senderID);

        String message = messageText.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Message patMessage = new Message(0, senderID, message);
        Call<Message> call = api.sendMessage(0, senderID, message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Log.d(TAG, "onResponse: " + response.body());

                if (response.isSuccessful()){

                    System.out.println(" testing " + response.body().getSenderID());

                    System.out.println("message  " + response.body().getMessage());
                    Toast.makeText(OptionalMessage.this, "Message Sent.", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(OptionalMessage.this, "Try Again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                t.printStackTrace();

                System.out.println("NADINE " +  t.getMessage());
                Toast.makeText(OptionalMessage.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setLocation(int status, String location)throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<String> call = api.sendLocation(status, location, senderID);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Toast.makeText(OptionalMessage.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(OptionalMessage.this, "Error" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


//        public void getLocation() {
//
//            String loc = location.getSelectedItem().toString();
//
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(ApiClient.BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            RegisterAPI api = retrofit.create(RegisterAPI.class);
//
//            Call<List<Message>> call = api.getLocation();
//            call.enqueue(new Callback<List<Message>>() {
//                @Override
//                public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
//                    final List<Message> ins = response.body();
//                    /*for (final Message loc : ins) {
//                        if (loc.getLocation()) {
//                            location.getSelectedItem(loc.getLocation());
//                        }
//                        }
//                        */
//                }
//
//                @Override
//                public void onFailure(Call<List<Message>> call, Throwable t) {
//
//                }
//            });


   // }


}

