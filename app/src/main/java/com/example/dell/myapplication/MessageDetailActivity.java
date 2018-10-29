package com.example.dell.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dell.myapplication.adapter.MessageDetailsAdapter;
import com.example.dell.myapplication.api.RegisterAPI;
import com.example.dell.myapplication.model.Message;
import com.example.dell.myapplication.model.MessageDetails;
import com.example.dell.myapplication.model.Value;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageDetailActivity extends AppCompatActivity {
    private MessageDetailsAdapter viewAdapter;
    private RecyclerView recyclerView;
    private Button buttonUpdate;
    private List<MessageDetails> crud = new ArrayList<>();
    private int receiverID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MessageDetailActivity.this);

        receiverID = prefs.getInt("ID", 0);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        buttonUpdate = (Button) findViewById(R.id.btnUpdate);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i =0; i<MessageDetailsAdapter.listItems.size(); i++){
                    if(MessageDetailsAdapter.listItems.get(i).getSelected()){

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(ApiClient.BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        RegisterAPI api = retrofit.create(RegisterAPI.class);
                        Call<Message> call = api.updateMessage( MessageDetailsAdapter.listItems.get(i).getId(), receiverID);
                        call.enqueue(new Callback<Message>() {
                            @Override
                            public void onResponse(Call<Message> call, Response<Message> response) {


                                if (response.isSuccessful()){
                                    Toast.makeText(MessageDetailActivity.this, "Message Sent.", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(MessageDetailActivity.this, "Try Again.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Message> call, Throwable t) {
                                t.printStackTrace();

                                Toast.makeText(MessageDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            }
        });
        loadDataCrud();
    }

    private void loadDataCrud() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.getPendingMessage();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if (value.equals("1")) {

                    crud = response.body().getMessageDetails();
                    System.out.println("nadine" + response.body().getMessageDetails());
                    viewAdapter = new MessageDetailsAdapter(crud, MessageDetailActivity.this);
                    recyclerView.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }
}
