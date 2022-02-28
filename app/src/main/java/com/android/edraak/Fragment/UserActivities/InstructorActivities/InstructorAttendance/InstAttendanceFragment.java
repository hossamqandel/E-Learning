package com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorAttendance;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorCourseControl.InstCourseControlFragment;
import com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorCourseControl.InstCourseControlManager;
import com.android.edraak.R;
import com.android.edraak.databinding.FragmentInstAttendanceBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class InstAttendanceFragment extends Fragment {


    FragmentInstAttendanceBinding binding;
    NavController navController;
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    String courseId;
    InstCourseControlManager instCourseControlManager;

    public InstAttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inst_attendance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentInstAttendanceBinding.bind(view);
        navController = Navigation.findNavController(view);
        courseId = InstAttendanceFragmentArgs.fromBundle(getArguments()).getCourseId();


        binding.GENERATECODEBTN.setOnClickListener(v -> {
            generatePincode();

        });

        binding.CONFIRMCODEBTN.setOnClickListener(v -> {
            confirmAttendance();
        });

    }




    private void generatePincode(){
        String randomCode = Integer.toString(ThreadLocalRandom.current().nextInt(1000, 9998 + 1));
        binding.CODETV.setText(randomCode);
    }

    private void confirmAttendance(){
        String code = binding.CODETV.getText().toString().trim();
        if (code.isEmpty()){
            Toast.makeText(getContext(), "You must Generate code first", Toast.LENGTH_SHORT).show();
        }
        else {
            mDatabaseRef.child("attendance").child(courseId).child(code).setValue("");
            binding.CONFIRMCODEBTN.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();

        }
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}