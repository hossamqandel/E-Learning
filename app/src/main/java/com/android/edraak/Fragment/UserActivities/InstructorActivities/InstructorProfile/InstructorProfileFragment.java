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
import com.android.edraak.Model.UserModel;
import com.android.edraak.R;
import com.android.edraak.databinding.FragmentInstructorProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class InstructorProfileFragment extends Fragment {

    FragmentInstructorProfileBinding binding;
    NavController navController;

    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mFireAuth = FirebaseAuth.getInstance();
    FirebaseUser mFireUser = mFireAuth.getCurrentUser();
    String mCurrentUser = mFireUser.getUid();

    LoginManager loginManager;
    UserModel user;
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

        displayUserInfo();

        binding.uiProfileLOGOUTBTN.setOnClickListener(v -> {
            instructorProfileManager.logOutAndNavigateBackToLogin(loginManager);
            navController.navigate(R.id.action_instructorProfileFragment_to_loginFragment2);
        });

        binding.uiInstructorCONTINUEBTN.setOnClickListener(v ->
                navController.navigate(R.id.action_instructorProfileFragment_to_instructorCourseFragment));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void displayUserInfo(){

        if (mCurrentUser == null){}

        mDatabaseRef.child("users").child(mCurrentUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(UserModel.class);

                boolean isVerifyed = user.isVerified();
                if (isVerifyed){ binding.uiInstructorVERIFYTV.setVisibility(View.VISIBLE); }
                else if (!isVerifyed){ binding.uiInstructorVERIFYTV.setVisibility(View.GONE); }


                binding.uiInstructorNAMETV.setText(user.getFullName());
                binding.uiInstructorEMAILTV.setText(user.getEmail());
                binding.uiInstructorPHONENUMBERTV.setText(user.getPhoneNumber());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}