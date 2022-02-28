package com.android.edraak.Model;

public class ChatModel {

    private String message;
    private String userName ;
    private String senderId ;

    public ChatModel() {
    }

    public ChatModel(String message, String userName, String senderId) {
        this.message = message;
        this.userName = userName;
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
}
