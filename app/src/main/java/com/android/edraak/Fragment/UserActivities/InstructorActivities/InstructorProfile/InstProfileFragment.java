package com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.edraak.Manager.LoginManager;
import com.android.edraak.Model.UserModel;
import com.android.edraak.R;
import com.android.edraak.databinding.FragmentInstProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class InstProfileFragment extends Fragment {

    FragmentInstProfileBinding binding;
    NavController navController;

    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mFireAuth = FirebaseAuth.getInstance();
    FirebaseUser mFireUser = mFireAuth.getCurrentUser();
    String mCurrentUser = mFireUser.getUid();

    LoginManager loginManager;
    UserModel user;
    InstProfileManager instProfileManager = new InstProfileManager();

    public InstProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inst_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        exitFromTheApp();
        binding = FragmentInstProfileBinding.bind(view);
        navController = Navigation.findNavController(view);
        loginManager = new LoginManager(getContext(), navController);

        displayUserInfo();

        binding.uiProfileLOGOUTBTN.setOnClickListener(v -> {
            instProfileManager.logOutAndNavigateBackToLogin(loginManager);
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