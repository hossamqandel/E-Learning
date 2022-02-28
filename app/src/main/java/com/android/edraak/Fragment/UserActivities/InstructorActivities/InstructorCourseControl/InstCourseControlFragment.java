package com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorCourseControl;

import static android.content.Context.CLIPBOARD_SERVICE;

import android.content.ClipData;
import android.content.ClipboardManager;
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

import com.android.edraak.R;
import com.android.edraak.databinding.FragmentInstCourseControlBinding;


public class InstCourseControlFragment extends Fragment {

    NavController navController;
    FragmentInstCourseControlBinding binding;
    InstCourseControlManager instCourseControlManager;

    public InstCourseControlFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inst_course_control, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentInstCourseControlBinding.bind(view);
        navController = Navigation.findNavController(view);
        instCourseControlManager = new InstCourseControlManager(navController);
        String courseId = InstCourseControlFragmentArgs.fromBundle(getArguments()).getCourseId();


        showAndCopyCurrentCourseId(courseId);
        Toast.makeText(getContext(), "CourseId #"+courseId, Toast.LENGTH_SHORT).show(); // TODO this Toast was just to make sure the courseId moved correct - remove it after finishing the project

        binding.secondeCardView.setOnClickListener(v -> navToCreateQuizFrag(courseId));
        instCourseControlManager.passCourseIdToAttendancePage(binding.firstCardView, courseId);

    }


    private void showAndCopyCurrentCourseId(String currentCourseId){
        binding.CurrentCourseIdTV.setText(currentCourseId);

        binding.CurrentCourseIdTV.setOnLongClickListener(v -> {
            ClipboardManager manager = (ClipboardManager) getContext().getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("text", binding.CurrentCourseIdTV.getText().toString());
            manager.setPrimaryClip(clipData);
            Toast.makeText(getContext(), "Copied", Toast.LENGTH_SHORT).show();
            return false;
        });


    }

    private void navToCreateQuizFrag(String courseId){
        InstCourseControlFragmentDirections.ActionInstructorCourseControlFragmentToInstCourseQuizFragment
                action = InstCourseControlFragmentDirections.actionInstructorCourseControlFragmentToInstCourseQuizFragment(courseId);
        navController.navigate(action);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}