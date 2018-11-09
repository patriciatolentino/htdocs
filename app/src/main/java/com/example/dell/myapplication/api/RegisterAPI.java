package com.example.dell.myapplication.api;


import com.example.dell.myapplication.model.Crud;
import com.example.dell.myapplication.model.Message;
import com.example.dell.myapplication.model.SafeExits;
import com.example.dell.myapplication.model.Users;
import com.example.dell.myapplication.model.Value;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RegisterAPI {
    @FormUrlEncoded
    @POST("insert.php")
    Call<Crud> add(@Field("id") String id,
                   @Field("calamityName") String calamityName,
                   @Field("description") String description,
                   @Field ("something") String something);

    @GET("view.php")
    Call<Value> view();


    @FormUrlEncoded
    @POST("search.php")
    Call  <Value> search(@Field("search") String search);

    @FormUrlEncoded
    @POST("delete.php")
    Call  <Value> delete(@Field("id") String id);

    @FormUrlEncoded
    @POST("update.php")
    Call<Value> update(@Field("id") String id,
                       @Field("calamityName") String calamityName,
                       @Field("description") String description,
                       @Field ("something") String something);
    @FormUrlEncoded
    @POST("messages/send.php")
    Call<Message> sendMessage(@Field("receiverID") int receiverID,
                              @Field("senderID") int senderID,
                              @Field("message") String message);

    @FormUrlEncoded
    @POST("messages/changestatus.php")
    Call<Message> updateMessage(@Field("id") int id,
                                @Field("receiverID") int receiverID);

    @GET("messages/pending.php")
    Call <Value> getPendingMessage();


    @FormUrlEncoded
    @POST("upload.php")
    Call<Value> uploadImage(@Field("calamityName") String calamityName,
                           @Field("image") String image);
    @GET("alert/alert.php")
    Call<Crud> sendAlert();

    @FormUrlEncoded
    @POST("alert/alertmessage.php")
    Call<Value> alertUsers(@Field("calamityID") int calamityID,
                            @Field("userID") int userID);


    @Headers("content-type:application/json")
    @GET("fetch.php") //user
    Call<List<SafeExits>> getExit();

    @FormUrlEncoded
    @POST("upd.php") //admin
    Call<ResponseBody> updateExit(@Field("exitID") int exitID,
                                  @Field("iStatus") int iStatus);

    @POST()
    Call<Users> sendNotif();

    @Headers("content-type:application/json")
    @GET("get.php/") //message
    Call<List<SafeExits>> getMessage();

    @FormUrlEncoded
    @POST("ins.php/") //message
    Call<String> sendMessage(@Field("exitID") int exitID,
                             @Field("instruction") String instruction);
}
