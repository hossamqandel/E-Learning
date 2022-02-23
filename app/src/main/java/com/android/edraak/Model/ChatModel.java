package com.android.edraak.Model;

public class ChatModel {

    private String massage ;
    private String userName ;
    private String senderId ;

    public ChatModel() {
    }

    public ChatModel(String massage, String userName, String senderId) {
        this.massage = massage;
        this.userName = userName;
        this.senderId = senderId;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
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
