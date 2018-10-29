package com.example.dell.myapplication.alert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;

import com.example.dell.myapplication.ApiClient;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.api.RegisterAPI;
import com.example.dell.myapplication.model.SafeExits;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewAnotherAlertActivity extends AppCompatActivity {

    CheckBox MFCExit2, backgateExit2, mainExit2, mainGateExit2, LRTExit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_blueprint);


        MFCExit2 = (CheckBox)findViewById(R.id.MFCExit2);
        backgateExit2 = (CheckBox)findViewById(R.id.backgateExit2);
        mainExit2 = (CheckBox)findViewById(R.id.mainExit2);
        mainGateExit2 = (CheckBox)findViewById(R.id.mainGateExit2);
        LRTExit2 = (CheckBox)findViewById(R.id.LRTExit2);

        viewSafeExit();

    }

    private void viewSafeExit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<List<SafeExits>> call = api.getExit();
        call.enqueue(new Callback<List<SafeExits>>() {
            @Override
            public void onResponse(Call<List<SafeExits>> call, Response<List<SafeExits>> response) {
                List<SafeExits> sl = response.body();

                for (SafeExits value : sl)
                {
                    if(value.getExitID() == 1)
                    {
                        if(value.getiStatus() == 1)

                        {
                            MFCExit2.setChecked(true);
                        }
                        else
                        {
                            MFCExit2.setChecked(false);
                        }
                    }

                    else if(value.getExitID() == 2)
                    {
                        if(value.getiStatus() == 1)
                        {
                            backgateExit2.setChecked(true);
                        }
                        else
                        {
                            backgateExit2.setChecked(false);
                        }
                    }

                    else if(value.getExitID() == 3)
                    {
                        if(value.getiStatus() == 1)
                        {
                            mainExit2.setChecked(true);
                        }
                        else
                        {
                            mainExit2.setChecked(false);
                        }
                    }

                    else if(value.getExitID() == 4)
                    {
                        if(value.getiStatus() == 1)
                        {
                            mainGateExit2.setChecked(true);
                        }
                        else
                        {
                            mainGateExit2.setChecked(false);
                        }
                    }

                    else if(value.getExitID() == 5)
                    {
                        if(value.getiStatus() == 1)
                        {
                            LRTExit2.setChecked(true);
                        }
                        else
                        {
                            LRTExit2.setChecked(false);
                        }
                    }

                    Log.d("responsebody ",String.valueOf(value.getiStatus()));
                }
            }

            @Override
            public void onFailure(Call<List<SafeExits>> call, Throwable t) {
                Log.d("responsebody", "onFailure: " + t.getMessage());
            }
        });
    }
}