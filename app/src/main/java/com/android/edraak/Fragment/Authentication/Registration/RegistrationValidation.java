package com.android.edraak.Fragment.Authentication.Registration;

import android.content.Context;
import android.widget.Toast;

import androidx.navigation.NavController;

import com.android.edraak.Manager.LoginManager;
import com.android.edraak.Model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationValidation {

    private Context context;
    private NavController navController;
    private DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth;

    private final String INSTRUCTOR = "instructor";
    private final String STUDENT = "student";

    public RegistrationValidation() {
    }

    public RegistrationValidation(Context context, NavController navController) {
        this.context = context;
        this.navController = navController;
    }

    void addNewUser(UserModel user) {
        validation(user);
    }

    private void validation(UserModel user) {
        String fullName = user.getFullName().toLowerCase();
        String age = user.getAge();
        String phoneNumber = user.getPhoneNumber();
        String email = user.getEmail().toLowerCase();
        String password = user.getPassword();

        if (fullName.isEmpty()) {
            Toast.makeText(context, "Name can't be Empty", Toast.LENGTH_SHORT).show();
        } else if (fullName.length() < 2) {
            Toast.makeText(context, "Name length at least 2", Toast.LENGTH_SHORT).show();

        } else if (age.isEmpty()) {
            Toast.makeText(context, "Age can't be Empty", Toast.LENGTH_SHORT).show();

        } else if (phoneNumber.isEmpty()) {
            Toast.makeText(context, "Phone Number can't be Empty", Toast.LENGTH_SHORT).show();
        } else if (phoneNumber.length() != 11) {
            Toast.makeText(context, "Phone Number length Must Be 11 Char", Toast.LENGTH_SHORT).show();

        } else if (email.isEmpty()) {
            Toast.makeText(context, "E-mail can't be Empty", Toast.LENGTH_SHORT).show();

        } else if (password.isEmpty()) {
            Toast.makeText(context, "Password can't be Empty", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 8) {
            Toast.makeText(context, "Password length at least 8", Toast.LENGTH_SHORT).show();

        } else {
            createFirebaseUserAuth(user);
        }

    }

    private void createFirebaseUserAuth(UserModel user) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        user.setUserId(mAuth.getCurrentUser().getUid());
                        pushToFirebase(user);
                    }
                });
    }

    private void pushToFirebase(UserModel userModel) {
        mDatabaseRef.child("users").child(userModel.getUserId()).setValue(userModel).addOnCompleteListener(task -> {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            LoginManager loginManager = new LoginManager(context, navController);
            loginManager.Login();
        });
    }
}
