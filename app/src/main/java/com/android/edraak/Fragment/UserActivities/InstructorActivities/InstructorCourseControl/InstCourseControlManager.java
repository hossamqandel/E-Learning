package com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorCourseControl;

import android.content.Context;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;

public class InstCourseControlManager {

    NavController navController;

    public InstCourseControlManager() {
    }

    public InstCourseControlManager(NavController navController) {
        this.navController = navController;
    }

    public void passCourseIdToAttendancePage(CardView cardView, String courseId){

        cardView.setOnClickListener(v -> {
            InstCourseControlFragmentDirections.ActionInstructorCourseControlFragmentToInstAttendanceFragment action
                    = InstCourseControlFragmentDirections.actionInstructorCourseControlFragmentToInstAttendanceFragment();
            action.setCourseId(courseId);
            navController.navigate(action);

        });
    }
}
