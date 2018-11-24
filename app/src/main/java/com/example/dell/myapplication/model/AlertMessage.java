package com.example.dell.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlertMessage {
    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("response")
    @Expose
    private Response response;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
