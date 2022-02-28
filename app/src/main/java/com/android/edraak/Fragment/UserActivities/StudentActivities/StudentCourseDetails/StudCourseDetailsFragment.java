package com.android.edraak.Fragment.UserActivities.StudentActivities.StudentCourseDetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorCourseControl.InstCourseControlFragmentArgs;
import com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorCourseControl.InstCourseControlFragmentDirections;
import com.android.edraak.Fragment.UserActivities.StudentActivities.StudentCourses.StudCourseGroupFragmentDirections;
import com.android.edraak.R;
import com.android.edraak.databinding.FragmentStudCourseDetailsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudCourseDetailsFragment extends Fragment {


    FragmentStudCourseDetailsBinding binding;
    NavController navController;
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String courseId;

    public StudCourseDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stud_course_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentStudCourseDetailsBinding.bind(view);
        navController = Navigation.findNavController(view);
        courseId = StudCourseDetailsFragmentArgs.fromBundle(getArguments()).getCourseId();

        Toast.makeText(getContext(), "CourseId #" + courseId, Toast.LENGTH_SHORT).show(); // TODO this Toast was just to make sure the courseId moved correct - remove it after finishing the project


        binding.activeATTENDANCEBTN.setOnClickListener(v -> {
            String attendanceCode = binding.attendanceCODEET.getText().toString().trim();
            if (attendanceCode.isEmpty()) {
            } else {
                signStudentAttendance(courseId, attendanceCode);
            }
        });

        binding.fourthCardview.setOnClickListener(v -> viewCourseQuizzes(courseId));

        binding.thirdfirstCardview.setOnClickListener(v -> navToChat(courseId));
    }






    private void signStudentAttendance(String courseId, String attendanceCode) {
        mDatabaseRef.child("attendance").child(courseId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(attendanceCode)) {
                    if (snapshot.child(attendanceCode).hasChild(courseId)) {
                        Toast.makeText(getContext(), "Already Here", Toast.LENGTH_SHORT).show();
                    } else {
                        mDatabaseRef.child("attendance").child(courseId).child(attendanceCode).child(userId)
                                .setValue(userId);
                        binding.attendanceCODEET.setText("");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void viewCourseQuizzes(String courseId){
        StudCourseDetailsFragmentDirections.ActionStudCourseDetailsFragmentToStudQuizGroupFragment action =
                StudCourseDetailsFragmentDirections.actionStudCourseDetailsFragmentToStudQuizGroupFragment(courseId);
        navController.navigate(action);
    }

    private void navToChat(String courseId){
        navController.navigate(StudCourseDetailsFragmentDirections.actionStudCourseDetailsFragmentToChatFragment(courseId));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}