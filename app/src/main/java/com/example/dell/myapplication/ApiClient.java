package com.example.dell.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL ="http://10.90.67.4/CRUD/";
    public static Retrofit retrofit = null;

    public static  Retrofit getApiClient() {
        if (retrofit == null)
        {
            retrofit =  new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return  retrofit;
    }
}
