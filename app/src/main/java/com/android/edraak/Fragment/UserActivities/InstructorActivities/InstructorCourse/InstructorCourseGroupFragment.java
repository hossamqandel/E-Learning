package com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorCourse;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.edraak.R;
import com.android.edraak.databinding.FragmentInstructorCourseBinding;


public class InstructorCourseGroupFragment extends Fragment {

    FragmentInstructorCourseBinding binding;
    NavController navController;
    public InstructorCourseGroupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instructor_course, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentInstructorCourseBinding.bind(view);
        navController = Navigation.findNavController(view);

        binding.uiADDNEWCOURSEBTN.setOnClickListener(v -> navController.navigate(R.id.action_instructorCourseFragment_to_instructorCourseDetailsFragment));
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}