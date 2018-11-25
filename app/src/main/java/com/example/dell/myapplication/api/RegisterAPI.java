package com.example.dell.myapplication.api;


import com.example.dell.myapplication.model.AlertMessage;
import com.example.dell.myapplication.model.Crud;
import com.example.dell.myapplication.model.Instruction;
import com.example.dell.myapplication.model.Message;
import com.example.dell.myapplication.model.Notif;
import com.example.dell.myapplication.model.PushNotif;
import com.example.dell.myapplication.model.Reports;
import com.example.dell.myapplication.model.SafeExits;
import com.example.dell.myapplication.model.Users;
import com.example.dell.myapplication.model.Value;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RegisterAPI {
    @FormUrlEncoded
    @POST("insert.php")
    Call<Crud> add(@Field("id") String id,
                   @Field("calamityName") String calamityName,
                   @Field("description") String description,
                   @Field("something") String something);

    @GET("view.php")
    Call<Value> view();


    @FormUrlEncoded
    @POST("search.php")
    Call<Value> search(@Field("search") String search);

    @FormUrlEncoded
    @POST("delete.php")
    Call<Value> delete(@Field("id") String id);

    @FormUrlEncoded
    @POST("update.php")
    Call<Value> update(@Field("id") String id,
                       @Field("calamityName") String calamityName,
                       @Field("description") String description,
                       @Field("something") String something);

    @FormUrlEncoded
    @POST("messages/send.php")
    Call<Message> sendMessage(@Field("receiverID") int receiverID,
                              @Field("senderID") int senderID,
                              @Field("message") String message);

    @FormUrlEncoded
    @POST("messages/changestatus.php")
    Call<Message> updateMessage(@Field("id") int id,
                                @Field("status") int status);

    @GET("messages/pending.php")
    Call<Value> getPendingMessage();


    @FormUrlEncoded
    @POST("upload.php")
    Call<Value> uploadImage(@Field("calamityName") String calamityName,
                            @Field("image") String image);

    @GET("alert/alert.php")
    Call<Crud> sendAlert();

    @FormUrlEncoded
    @POST("alert/alertmessage.php")
    Call<AlertMessage> alertUsers(@Field("userID") int userID,
                                  @Field("calamityID") String calamityID,
                                  @Field("status") int status);


    @Headers("content-type:application/json")
    @GET("fetch.php")
        //user
    Call<List<SafeExits>> getExit();

    @FormUrlEncoded
    @POST("upd.php")
        //admin
    Call<ResponseBody> updateExit(@Field("exitID") int exitID,
                                  @Field("iStatus") int iStatus);

    @POST()
    Call<Users> sendNotif();

    @Headers("content-type:application/json")
    @GET("get.php")
        //message
    Call<List<Instruction>> getMessage();

    @FormUrlEncoded
    @POST("ins.php")
        //message
    Call<String> sendMessage(@Field("exitID") int exitID,
                             @Field("instruction") String instruction);


    @GET("viewreports.php")
    Call<Reports> viewreports();

    //spinner
    @GET("getloc.php")
    Call<List<Message>> getLocation();


    //spinner
    @FormUrlEncoded
    @POST("sendloc.php")
    Call<String> sendLocation(@Field("status") int status,
                              @Field("senderID") String senderID,
                              @Field("location") int location);

    @Headers({"Authorization: key=AAAALsWTz_U:APA91bG_qRZxFeqTk5PcY3HEQbPTKHX44dJW8WGpej0CWraBT" +
            "F05y6oOk4UZbZGMkiCM56nPKPJXMQsC6E8mmK7PQ7cxVkYQt9smQ1w" +
            "Buls_tjxN9vrYe_5qfHUkN8G7kc_BDaWpr73-",
            "Content-Type:application/json"})
    @POST("send")
    Call<ResponseBody> pushNotif(@Body PushNotif pushNotif);
}
