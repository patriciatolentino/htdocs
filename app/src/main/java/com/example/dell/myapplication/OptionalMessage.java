package com.example.dell.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.myapplication.api.RegisterAPI;
import com.example.dell.myapplication.login.WelcomeFragment;
import com.example.dell.myapplication.model.Message;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OptionalMessage extends UserActivity {

    private EditText messageText;
    private Button  btnSend;
    private int senderID;

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    WelcomeFragment.OnLogoutListener logoutListener;
    public interface  OnLogoutListener {
        public void logoutPerformed();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optional_message);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(OptionalMessage.this);

        senderID = prefs.getInt("ID", 0);

        messageText = findViewById(R.id.txtMessage);
        btnSend = findViewById(R.id.btnSendLoc);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessage();
            }
        });

        dl = (DrawerLayout)findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this,dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view=(NavigationView)findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id =  item.getItemId();

                if(id == R.id.home) {
                    Intent view = new Intent (OptionalMessage.this, UserActivity.class);
                    startActivity(view);

                } else if (id == R.id.optmsg) {

                    Toast.makeText(OptionalMessage.this,"Optional Message",Toast.LENGTH_SHORT).show();
                }  else if (id == R.id.logout) {
                    logoutListener.logoutPerformed();

                }
                return true;
            }
        });
    }


    private void SendMessage(){

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


                if (response.isSuccessful()){
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        logoutListener = (WelcomeFragment.OnLogoutListener) activity;
    }
    }

