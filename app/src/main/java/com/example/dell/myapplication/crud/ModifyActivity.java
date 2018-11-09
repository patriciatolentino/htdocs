package com.example.dell.myapplication.crud;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dell.myapplication.ApiClient;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.api.RegisterAPI;
import com.example.dell.myapplication.model.Crud;
import com.example.dell.myapplication.model.Value;
import com.google.firebase.database.DatabaseReference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModifyActivity extends AppCompatActivity implements View.OnClickListener {


    private RadioButton radioButton;
    private ProgressDialog progress;
    private RadioGroup radioGroup;
    private EditText txtID;
    private EditText txtCalamityName;
    private EditText txtDescription;
    private Button buttonAdd;
    private Button buttonView;


    private Button buttonChoose, buttonUpload;
    private ImageView img;
    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;

    DatabaseReference databaseInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_activity);

        buttonChoose = (Button)findViewById(R.id.btnChoose);
        buttonUpload = (Button)findViewById(R.id.btnUpload);
        img = (ImageView) findViewById(R.id.imageView);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);

        buttonAdd =  findViewById(R.id.btnAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
        buttonView = findViewById(R.id.btnView);
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewdata = new Intent(ModifyActivity.this, ViewActivity.class);
                startActivity(viewdata);

            }
        });
    }

    public void add(){
        txtID = findViewById(R.id.txtID);
        txtCalamityName = findViewById(R.id.txtCalamityName);
        txtDescription= findViewById(R.id.txtDescription);
        radioGroup= findViewById(R.id.radiogroup);

        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading...");
        progress.show();

        String id = txtID.getText().toString();
        String calamityName = txtCalamityName.getText().toString();
        String description = txtDescription.getText().toString();

        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton)findViewById(selectedId);
        String something = radioButton.getText().toString();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Crud> call = api.add(id,calamityName,description,something);
        call.enqueue(new Callback<Crud>() {
            @Override
            public void onResponse(Call<Crud> call, Response<Crud> response) {
                progress.dismiss();

                if (response.isSuccessful()){
                    Toast.makeText(ModifyActivity.this, "Added.", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ModifyActivity.this, "Try Again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Crud> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(ModifyActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.btnChoose:
               selectImage();
               break;
           case R.id.btnUpload:
               uploadImage();
               break;

       }
    }

    private void uploadImage() {

        txtCalamityName = (EditText) findViewById(R.id.txtCalamityName);

        String image =  imageToString();
        String calamityName = txtCalamityName.getText().toString();
        RegisterAPI registerapi = ApiClient.getApiClient().create(RegisterAPI.class);

        Call<Value> call = registerapi.uploadImage(calamityName, image);

        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                Value value = response.body();

                    Toast.makeText(ModifyActivity.this, "Server Response: " + value.getResponse(), Toast.LENGTH_LONG).show();
                    img.setVisibility(View.GONE);
                    txtCalamityName.setVisibility(View.GONE);
                    buttonChoose.setEnabled(true);
                    buttonUpload.setEnabled(false);
                    txtCalamityName.setText("");

            }
            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });

    }



    private void selectImage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        txtCalamityName = (EditText)findViewById(R.id.txtCalamityName);

        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data !=null)
        {

            Uri path = data.getData();
            try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
            img.setImageBitmap(bitmap);
            img.setVisibility(View.VISIBLE);
            txtCalamityName.setVisibility(View.VISIBLE);
            buttonChoose.setEnabled(false);
            buttonUpload.setEnabled(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
    private String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }
}
