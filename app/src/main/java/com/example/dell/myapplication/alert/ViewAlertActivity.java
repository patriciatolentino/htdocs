package com.example.dell.myapplication.alert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.dell.myapplication.ApiClient;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.adapter.AlertAdapter;
import com.example.dell.myapplication.api.RegisterAPI;
import com.example.dell.myapplication.model.Crud;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewAlertActivity extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private AlertAdapter adapter;

    private CheckBox CBearthquake, CBfire, CBflood, CBbomb, CBshooter;
    private Button buttonOK;
    private ImageView image;

    private List<Crud> crud = new ArrayList<>();
    ArrayList<String> selection = new ArrayList<String>();
    public static List<Crud> alertitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        image = (ImageView)findViewById(R.id.imgCalamity);
        CBearthquake = (CheckBox) findViewById(R.id.cbEartquake);
        CBfire = (CheckBox) findViewById(R.id.cbFire);
        CBflood = (CheckBox) findViewById(R.id.cbFlood);
        CBbomb = (CheckBox) findViewById(R.id.cbBomb);
        CBshooter = (CheckBox)findViewById(R.id.cbShooter);


        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        loadDataCrud();

        buttonOK = (Button) findViewById(R.id.btnOk);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewAlertActivity.this, ViewAnotherAlertActivity.class);
                startActivity(i);
            }
        });
    }
    private void loadDataCrud() {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiClient.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RegisterAPI api = retrofit.create(RegisterAPI.class);

            Call<Crud> call = api.sendAlert();
            call.enqueue(new Callback<Crud>() {
                @Override
                public void onResponse(Call<Crud> call, Response<Crud> response) {

                    if (response.isSuccessful()) {
                        crud = response.body().getResult();
                        adapter = new AlertAdapter(crud, ViewAlertActivity.this);
                        recyclerView.setAdapter(adapter);

                        if (response.body().getId() == ("1")) {
                            image.setImageResource(R.drawable.earthquake_img);
                            System.out.println("CALAMITY  " + response.body().getId());
                        }


                    }
                    }

                @Override
                public void onFailure(Call<Crud> call, Throwable t) {

                }
            });
    }
}
