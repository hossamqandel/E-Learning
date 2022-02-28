package com.android.edraak.Fragment.UserActivities.StudentActivities.StudentCourses;

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

import com.android.edraak.Adapter.Instructor.InstructorCourseAdapter;
import com.android.edraak.Model.CourseModel;
import com.android.edraak.R;
import com.android.edraak.databinding.FragmentStudCourseGroupBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class StudCourseGroupFragment extends Fragment {

    FragmentStudCourseGroupBinding binding;
    NavController navController;
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    List<CourseModel> mGroup = new ArrayList<>();
    InstructorCourseAdapter mAdapter = new InstructorCourseAdapter();
    public StudCourseGroupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stud_course_group, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentStudCourseGroupBinding.bind(view);
        navController = Navigation.findNavController(view);

        binding.studCourseADDBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCourses();
            }
        });
    }



    private void getCourses(){
        String courseId = binding.studCourseIdET.getText().toString().trim();

        if (courseId.isEmpty()){
            Toast.makeText(getContext(), "Enter Course ID", Toast.LENGTH_SHORT).show();
        }
        else {
            mDatabaseRef.child("courses").child(courseId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    CourseModel course = snapshot.getValue(CourseModel.class);
                    mGroup.add(course);
                    mAdapter.setList(mGroup);
                    binding.studRecycler.setAdapter(mAdapter);
                    binding.studCourseIdET.setText("");

                    mAdapter.setOnClick(courseId1 -> {

                        StudCourseGroupFragmentDirections.ActionStudCourseGroupFragmentToStudCourseDetailsFragment action
                                = StudCourseGroupFragmentDirections.actionStudCourseGroupFragmentToStudCourseDetailsFragment();
                        action.setCourseId(courseId1);
                        navController.navigate(action);

                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}