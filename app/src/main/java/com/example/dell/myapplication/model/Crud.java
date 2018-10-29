package com.example.dell.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Crud {

    public int image;

    Crud(int image) {
        this.image = image;

    }
    String id;
    @SerializedName("calamityName")
    String calamityName;
    String description;
    String something;
    String path;


    public String getId() {

        return id;
    }

    public String getCalamityName() {

        return calamityName;
    }
    public String getDescription() {

        return description;
    }
    public String getSomething() {
        return something;
    }

    @SerializedName("path")
    public String getPath() {
        return path;
    }


    @SerializedName ("response")
    private String Response;

    public String getResponse() {
        return Response;
    }

}

