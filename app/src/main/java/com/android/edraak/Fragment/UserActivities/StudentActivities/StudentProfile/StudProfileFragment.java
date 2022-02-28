package com.android.edraak.Fragment.UserActivities.StudentActivities.StudentProfile;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorProfile.InstProfileManager;
import com.android.edraak.Manager.LoginManager;
import com.android.edraak.Model.UserModel;
import com.android.edraak.R;
import com.android.edraak.databinding.FragmentStudProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class StudProfileFragment extends Fragment {

    FragmentStudProfileBinding binding;
    NavController navController;

    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mFireAuth = FirebaseAuth.getInstance();
    FirebaseUser mFireUser = mFireAuth.getCurrentUser();
    String mCurrentUser = mFireUser.getUid();

    LoginManager loginManager;
    UserModel user;
    InstProfileManager instProfileManager = new InstProfileManager();

    public StudProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stud_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        exitFromTheApp();
        binding = FragmentStudProfileBinding.bind(view);
        navController = Navigation.findNavController(view);
        loginManager = new LoginManager(getContext(), navController);

        displayUserInfo();

        binding.uiProfileLOGOUTBTN.setOnClickListener(v -> {
            instProfileManager.logOutAndNavigateBackToLogin(loginManager);
            navController.navigate(R.id.action_studProfileFragment_to_loginFragment);
        });


        binding.uiStudentCONTINUEBTN.setOnClickListener(v ->
                navController.navigate(R.id.action_studProfileFragment_to_studCourseGroupFragment));

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
                if (isVerifyed){ binding.uiStudentVERIFYIV.setVisibility(View.VISIBLE); }
                else if (!isVerifyed){ binding.uiStudentVERIFYIV.setVisibility(View.GONE); }


                binding.uiStudentNAMETV.setText(user.getFullName());
                binding.uiStudentEMAILTV.setText(user.getEmail());
                binding.uiStudentPHONETV.setText(user.getPhoneNumber());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void exitFromTheApp(){
        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                getActivity().moveTaskToBack(true);
                getActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }
}