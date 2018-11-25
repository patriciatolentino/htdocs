package com.example.dell.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notif {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("text")
    @Expose
    private String text;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }
}
