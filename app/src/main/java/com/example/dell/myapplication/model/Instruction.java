package com.example.dell.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Instruction {
    @SerializedName("exitID")
    @Expose
    private String exitID;
    @SerializedName("instruction")
    @Expose
    private String instruction;

    public String getExitID() {
        return exitID;
    }

    public void setExitID(String exitID) {
        this.exitID = exitID;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
