package com.example.dell.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.myapplication.adapter.MessageDetailsAdapter;
import com.example.dell.myapplication.alert.ViewAnotherAlertActivity;
import com.example.dell.myapplication.api.RegisterAPI;
import com.example.dell.myapplication.model.Message;
import com.example.dell.myapplication.model.MessageDetails;
import com.example.dell.myapplication.model.SafeExits;
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
    private List<MessageDetails> crud = new ArrayList<>();
    private int receiverID;
    private Button buttonUpdate;
   TextView location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

      //fetchData();
        loadDataCrud();

      // location = (TextView) findViewById(R.id.txtLocation);

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
                        System.out.println("ids " + MessageDetailsAdapter.listItems.get(i).getId());
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(ApiClient.BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        RegisterAPI api = retrofit.create(RegisterAPI.class);
                        Call<Message> call = api.updateMessage(MessageDetailsAdapter.listItems.get(i).getId(),1);
                        call.enqueue(new Callback<Message>() {
                            @Override
                            public void onResponse(Call<Message> call, Response<Message> response) {

                                if (response.isSuccessful()){
                                    System.out.println("id " + response.body().getMessage());
                                    loadDataCrud();
                                    Toast.makeText(MessageDetailActivity.this, "Updatedd.", Toast.LENGTH_SHORT).show();

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

   /* private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<List<Message>> call = api.getLocation();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> adslist = response.body();

                String loc = adslist.get(0).getLocation();
                location.setText(loc);

            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

                Toast.makeText(MessageDetailActivity.this, ""+t.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }*/
}