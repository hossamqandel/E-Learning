package com.android.edraak.Fragment.Authentication.Login;

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

import com.android.edraak.Manager.LoginManager;
import com.android.edraak.R;
import com.android.edraak.databinding.FragmentLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginFragment extends Fragment {
//    inhossam@gmail.com

    FragmentLoginBinding binding;
    NavController navController;
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth;
    boolean isInstructor;

//    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentLoginBinding.bind(view);
        navController = Navigation.findNavController(view);
        mAuth = FirebaseAuth.getInstance();

        binding.uiLoginBTN.setOnClickListener(v -> {
            String email = binding.uiEmailET.getText().toString().trim();
            String password = binding.uiPasswordET.getText().toString().trim();
            firebaseLogin(email, password);
        });

        navAsInstructorToSignupPage();
        navAsStudentToSignupPage();
    }

    private void firebaseLogin(String email, String password) {
        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    LoginManager loginManager = new LoginManager(getContext(), navController);
                    loginManager.Login();
                }
            });
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void navAsInstructorToSignupPage() {
        binding.uiRegInstructorBTN.setOnClickListener(v -> {
            isInstructor = true;
            LoginFragmentDirections.ActionLoginFragmentToRegistrationFragment action =
                    LoginFragmentDirections.actionLoginFragmentToRegistrationFragment();
            action.setIsInstructor(isInstructor);
            navController.navigate(action);
            Toast.makeText(getContext(), "" + action.getIsInstructor(), Toast.LENGTH_SHORT).show();
        });
    }

    private void navAsStudentToSignupPage() {

        binding.uiRegStudentBTN.setOnClickListener(v -> {
            isInstructor = false;
            LoginFragmentDirections.ActionLoginFragmentToRegistrationFragment action =
                    LoginFragmentDirections.actionLoginFragmentToRegistrationFragment();
            action.setIsInstructor(isInstructor);
            navController.navigate(action);
            Toast.makeText(getContext(), "" + action.getIsInstructor(), Toast.LENGTH_SHORT).show();
        });
    }
}