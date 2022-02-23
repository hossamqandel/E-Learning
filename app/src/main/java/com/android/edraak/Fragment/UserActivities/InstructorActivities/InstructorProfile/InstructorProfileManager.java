package com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorProfile;

import com.android.edraak.Manager.LoginManager;
import com.android.edraak.R;

public class InstructorProfileManager {

    protected void logOutAndNavigateBackToLogin(LoginManager loginManager) {
        loginManager.Logout();
    }
}
