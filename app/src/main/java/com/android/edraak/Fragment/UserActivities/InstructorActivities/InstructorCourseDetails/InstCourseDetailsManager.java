package com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorCourseDetails;

import android.content.Context;
import android.widget.Toast;

import androidx.navigation.NavController;

import com.android.edraak.Model.CourseModel;
import com.android.edraak.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InstCourseDetailsManager {

    private DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String currentInstructorId = mAuth.getUid();
    private String pushCourseId = mDatabaseRef.push().getKey();

    Context context;
    NavController navController;
    public InstCourseDetailsManager(Context context, NavController navController) {
        this.context = context;
        this.navController = navController;
    }

    protected void postCourse(CourseModel course){
        validation(course);
    }

    private void validation(CourseModel course) {
        String courseName = course.getCourseName().trim();
        String courseAssignmentGrade = Double.toString(course.getAssignmentGrade()).trim();
        String courseAttendanceGrade = Double.toString(course.getAttendanceGrade()).trim();
        String courseProjectsGrade = Double.toString(course.getProjectsGrade()).trim();

        for (int i = 0 ; i < 9; i++ ){
            String x = Integer.toString(i);
            if (courseName.contains(x)){
                Toast.makeText(context, "Course name only accept Characters ", Toast.LENGTH_LONG).show();
            }
        }

        if (courseName.isEmpty() || courseName.length() < 2 ){
            Toast.makeText(context, "Course name can't be Empty ", Toast.LENGTH_LONG).show();
        }

        else if (courseAssignmentGrade.isEmpty() || courseAssignmentGrade.length() < 2){
            Toast.makeText(context, "Course Assignment Grade can't be Empty ", Toast.LENGTH_LONG).show();
        }

        else if (courseAttendanceGrade.isEmpty() || courseAttendanceGrade.length() < 2){
            Toast.makeText(context, "Course Attendance Grade can't be Empty ", Toast.LENGTH_LONG).show();

        }

        else if (courseProjectsGrade.isEmpty() || courseProjectsGrade.length() < 2){
            Toast.makeText(context, "Course Projects Grade can't be Empty ", Toast.LENGTH_LONG).show();
        }

        else {
            pushCourseToFirebase(course);
        }
    }

    private void pushCourseToFirebase(CourseModel course) {
        course.setCourseId(pushCourseId);
        course.setInstructorId(currentInstructorId);
        mDatabaseRef.child("courses").child(pushCourseId).setValue(course);
        Toast.makeText(context, "Success.. Course Posted ", Toast.LENGTH_LONG).show();
        navController.navigate(R.id.action_instructorCourseDetailsFragment_to_instructorCourseFragment);
    }
}
