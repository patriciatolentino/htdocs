package com.example.dell.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
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

public class OptionalMessage extends AppCompatActivity {

    private EditText messageText;
    private Button  btnSend;
    private int senderID;



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

    }

