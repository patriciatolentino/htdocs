package com.example.dell.myapplication.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SafeExits {

    @SerializedName("exitID")
    @Expose
    private int exitID;

    @SerializedName("exitName")
    @Expose
    private String exitName;

    @SerializedName("iStatus")
    @Expose
    private int iStatus;        //boolean?


    @SerializedName("value")
    @Expose
    private int value;


    public SafeExits(int exitID, String exitName, int iStatus)
    {
        this.exitID = exitID;
        this.exitName = exitName;
        this.iStatus = iStatus;
    }


    public int getExitID() {
        return exitID;
    }

    public void setExitID(int exitID) {
        this.exitID = exitID;
    }

    public String getExitName() {
        return exitName;
    }

    public void setExitName(String exitName) {
        this.exitName = exitName;
    }

    public int getiStatus() {
        return iStatus;
    }

    public void setiStatus(int iStatus) {
        this.iStatus = iStatus;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}