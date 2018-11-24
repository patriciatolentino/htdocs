package com.example.dell.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Value {
    @SerializedName("value")
    @Expose
    String value;

    @SerializedName("message")
    @Expose
    String message;

    List<Result> result;

    List<AlertDetails> alerts;

    public List<MessageDetails> getMessageDetails() {
        return messageDetails;
    }

    public void setMessageDetails(List<MessageDetails> messageDetails) {
        this.messageDetails = messageDetails;
    }
    List<MessageDetails> messageDetails;

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public List<Result> getResult() {
        return result;
    }

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
    public List<AlertDetails> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<AlertDetails> alerts) {
        this.alerts = alerts;
    }


}
