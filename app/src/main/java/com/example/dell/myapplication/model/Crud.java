package com.example.dell.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Crud {
    @SerializedName("result")
    private List<Result> result;
    public List<Result> getResult() {
        return result;
    }
    public void setResult(List<Result> result) {
        this.result = result;
    }
    public boolean isSelected;

    @SerializedName("reports")
    private List<Reports> reports;
    public List<Reports> getReports() {
        return reports;
    }


    @SerializedName ("response")
    private String Response;

    public String getCalamityID() {
        return calamityID;
    }

    public void setCalamityID(String calamityID) {
        this.calamityID = calamityID;
    }

    String calamityID;
    @SerializedName("calamityName")
    String calamityName;
    String description;



    public String getCalamityName() {

        return calamityName;
    }
    public String getDescription() {

        return description;
    }


    public String getResponse() {
        return Response;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public boolean getSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}