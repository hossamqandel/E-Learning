package com.android.edraak.Fragment.Authentication.Registration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.edraak.Model.UserModel;
import com.android.edraak.R;
import com.android.edraak.databinding.FragmentRegistrationBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegistrationFragment extends Fragment {

    FragmentRegistrationBinding binding;
    NavController navController;
    RegistrationValidation registrationValidation;
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    boolean isInstructor;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentRegistrationBinding.bind(view);
        navController = Navigation.findNavController(view);
        isInstructor = RegistrationFragmentArgs.fromBundle(getArguments()).getIsInstructor();

        registrationValidation = new RegistrationValidation(getContext(), navController);


        binding.uiStudentSignupBTN.setOnClickListener(v ->
                registrationValidation.addNewUser(setNewUserObj()));
    }



    private UserModel setNewUserObj(){
        String fullName = binding.uiStudentFullNameET.getText().toString().trim();
        String age = binding.uiStudentAgeET.getText().toString().trim();
        String phoneNumber = binding.uiStudentPhoneNumberET.getText().toString().trim();
        String email = binding.uiStudentEmailET.getText().toString().trim();
        String password = binding.uiStudentPasswordET.getText().toString().trim();

        UserModel userModel =  new UserModel("", fullName, age, phoneNumber, email, password);
        userModel.setUserType(getAccountType());
        return userModel;
    }

    private String getAccountType() {
        if(isInstructor) return "instructor";
        else return "student";
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}