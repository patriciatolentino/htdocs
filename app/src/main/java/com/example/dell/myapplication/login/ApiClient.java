package com.example.dell.myapplication.login;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL ="https://isproj2b.benilde.edu.ph/BEAP/CRUD/";
    public static Retrofit retrofit = null;

    public static  Retrofit getApiClient() {
        if (retrofit == null)
        {
            retrofit =  new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return  retrofit;
    }
}
