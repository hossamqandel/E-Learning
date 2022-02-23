package com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.edraak.Manager.LoginManager;
import com.android.edraak.R;
import com.android.edraak.databinding.FragmentInstructorProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class InstructorProfileFragment extends Fragment {

    FragmentInstructorProfileBinding binding;
    NavController navController;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser mUser = mAuth.getCurrentUser();
    String us = mUser.getUid();
    LoginManager loginManager;
    InstructorProfileManager instructorProfileManager = new InstructorProfileManager();

    public InstructorProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instructor_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentInstructorProfileBinding.bind(view);
        navController = Navigation.findNavController(view);
        loginManager = new LoginManager(getContext(), navController);

        binding.uiProfileLOGOUTBTN.setOnClickListener(v -> {
            instructorProfileManager.logOutAndNavigateBackToLogin(loginManager);
            navController.navigate(R.id.action_instructorProfileFragment_to_loginFragment2);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}