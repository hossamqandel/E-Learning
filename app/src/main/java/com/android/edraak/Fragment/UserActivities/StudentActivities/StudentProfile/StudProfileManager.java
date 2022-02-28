package com.android.edraak.Fragment.UserActivities.StudentActivities.StudentProfile;

import com.android.edraak.Manager.LoginManager;

public class StudProfileManager {

    protected void logOutAndNavigateBackToLogin(LoginManager loginManager) {
        loginManager.Logout();
    }

}
