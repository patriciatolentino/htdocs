package com.example.dell.myapplication.model;

import android.media.Image;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    public boolean isSelected;

    //private int image;
    @SerializedName("calamityID")
    @Expose
    private String calamityID;

    @SerializedName("calamityName")
    @Expose
    private String calamityName;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("image")
    @Expose
    private String image;


    public String getCalamityID() {
        return calamityID;
    }

    public void setCalamityID(String calamityID) {
        this.calamityID = calamityID;
    }

    public String getCalamityName() {
        return calamityName;
    }

    public void setCalamityName(String calamityName) {
        this.calamityName = calamityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    /*
public int getImage() {
    return image;
 }

  public void setImage(int image) {
      this.image = image;
  }
  */
}
