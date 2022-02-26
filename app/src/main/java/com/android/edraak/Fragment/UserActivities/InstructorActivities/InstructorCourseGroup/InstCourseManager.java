package com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorCourseGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.android.edraak.Adapter.Instructor.InstructorCourseAdapter;
import com.android.edraak.Model.CourseModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class InstCourseManager {

    NavController navController;
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();

    public InstCourseManager(NavController navController) {
        this.navController = navController;
    }


}
