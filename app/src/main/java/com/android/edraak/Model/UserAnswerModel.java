package com.android.edraak.Model;

public class UserAnswerModel {

    private String userId ;
    private String userName ;
    private String email ;
    private int grade ;

    public UserAnswerModel() {
    }

    public UserAnswerModel(String userId, String userName, String email, int grade) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.grade = grade;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
