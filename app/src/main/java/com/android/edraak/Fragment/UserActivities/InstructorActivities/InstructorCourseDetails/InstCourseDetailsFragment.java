package com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorCourseDetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.edraak.Model.CourseModel;
import com.android.edraak.R;
import com.android.edraak.databinding.FragmentInstCourseDetailsBinding;


public class InstCourseDetailsFragment extends Fragment {

    FragmentInstCourseDetailsBinding binding;
    NavController navController;
    InstCourseDetailsManager courseDetailsManager;
    public InstCourseDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inst_course_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentInstCourseDetailsBinding.bind(view);
        navController = Navigation.findNavController(view);

        binding.uiCOURSEDETAILSCREATEBTN.setOnClickListener(v -> addCourse());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void addCourse(){
        CourseModel course = new CourseModel();
        courseDetailsManager = new InstCourseDetailsManager(getActivity(), navController);
        course.setCourseName(binding.uiCOURSEDETAILSNAMEET.getText().toString().trim());
        course.setAssignmentGrade(Double.parseDouble(binding.uiCOURSEDETAILSQUIZGRADEET.getText().toString().trim()));
        course.setAttendanceGrade(Double.parseDouble(binding.uiCOURSEDETAILSATTENDANCEGRADEET.getText().toString().trim()));
        course.setProjectsGrade(Double.parseDouble(binding.uiCOURSEDETAILSPROJECTGRADEET.getText().toString().trim()));
        courseDetailsManager.postCourse(course);
    }
}