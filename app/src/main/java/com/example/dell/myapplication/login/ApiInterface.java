package com.example.dell.myapplication.login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
        Call<User1> performUserLogin(@Field("username") String UserName,
                                     @Field("password") String UserPassword);


}
