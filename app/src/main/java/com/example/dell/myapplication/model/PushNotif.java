package com.example.dell.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PushNotif {
    @SerializedName("to")
    @Expose
    private String to;

    @SerializedName("notification")
    @Expose
    private Notif notif;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Notif getNotif() {
        return notif;
    }

    public void setNotif(Notif notif) {
        this.notif = notif;
    }
}
