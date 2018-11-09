package com.example.dell.myapplication.crud;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dell.myapplication.ApiClient;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.api.RegisterAPI;
import com.example.dell.myapplication.model.Value;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends AppCompatActivity {


    private ProgressDialog progress;

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private Button buttonUpdate;
    private EditText txtID;
    private EditText txtCalamityName;
    private EditText txtDescription;
    private EditText txtSomething;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        txtID = (EditText) findViewById(R.id.txtID);
        txtCalamityName = (EditText) findViewById(R.id.txtCalamityName);
        txtDescription = (EditText) findViewById(R.id.txtDescription);
        buttonUpdate = (Button) findViewById(R.id.btnUpdate);
        radioButtonFemale = (RadioButton) findViewById(R.id.rbFemale);
        radioButtonMale = (RadioButton) findViewById(R.id.rbMale);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Change data");

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String calamityName = intent.getStringExtra("calamityName");
        String description = intent.getStringExtra("description");
        String something = intent.getStringExtra("something");

        txtID.setText(id);
        System.out.print("systout mo sis" + intent.getStringExtra(id));
        txtCalamityName.setText(calamityName);
        txtDescription.setText(description);
        if (something.equals("Male")) {
            radioButtonMale.setChecked(true);
        } else {
            radioButtonFemale.setChecked(true);
        }
    }

    public void update() {
        txtID = (EditText) findViewById(R.id.txtID);
        txtCalamityName = (EditText) findViewById(R.id.txtCalamityName);
        txtDescription = (EditText) findViewById(R.id.txtDescription);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();


        String id = txtID.getText().toString();
        String calamityName = txtCalamityName.getText().toString();
        String description = txtDescription.getText().toString();


        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        String something = radioButton.getText().toString();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.update(id, calamityName, description, something);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (response.isSuccessful()) {

                    Toast.makeText(UpdateActivity.this, "Updated!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(UpdateActivity.this, "Failed to update.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }
        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    break;
                case R.id.action_delete:
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    alertDialogBuilder.setTitle("Warning");
                    alertDialogBuilder
                            .setMessage("Are you sure you want to delete this data?")
                            .setCancelable(false)
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id2) {

                                    String id = txtID.getText().toString();
                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(ApiClient.BASE_URL)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    RegisterAPI api = retrofit.create(RegisterAPI.class);
                                    Call<Value> call = api.delete(id);
                                    call.enqueue(new Callback<Value>() {
                                        @Override
                                        public void onResponse(Call<Value> call, Response<Value> response) {
                                            String value = response.body().getValue();
                                            String message = response.body().getMessage();
                                            if (value.equals("1")) {
                                                Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_SHORT).show();
                                                finish();
                                            } else {
                                                Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Value> call, Throwable t) {
                                            t.printStackTrace();
                                           // Toast.makeText(UpdateActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    break;
            }
            return super.onOptionsItemSelected(item);
        }
    }
