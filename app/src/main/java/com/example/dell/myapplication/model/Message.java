package com.example.dell.myapplication.model;

public class Message {
    public int id;
    public int receiverID;
    public int userID;
    public String message;
    public String status;
    

    public Message(int receiverID, int userID, String message){

        this.receiverID = receiverID;
        this.userID = userID;
        this.message = message;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public int getSenderID() {
        return userID;
    }

    public void setSenderID(int senderID) {
        this.userID = userID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
