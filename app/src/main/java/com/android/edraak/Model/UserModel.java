package com.android.edraak.Model;

import java.io.Serializable;

public class UserModel implements Serializable {

    private String userId;
    private String fullName;
    private String age;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean isVerified;
    private String userType;

    public UserModel() {
    }

    public UserModel(String userId, String fullName, String age, String phoneNumber, String email, String password) {
        this.userId = userId;
        this.fullName = fullName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public UserModel(String userId, String fullName, String age, String phoneNumber, String email, String password, boolean isVerified, String userType) {
        this.userId = userId;
        this.fullName = fullName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.isVerified = isVerified;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
