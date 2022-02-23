package com.android.edraak.Manager;
// NOTE: THIS PACKAGE WILL BE CONANING ALL PUBLIC CLASS IN MANY USES AND CALLS

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;

import com.android.edraak.Model.UserModel;
import com.android.edraak.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginManager {
    Context context;
    FirebaseAuth mAuth;
    NavController navController;

    public LoginManager(Context context, NavController navController) {
        this.context = context;
        this.navController = navController;
        mAuth = FirebaseAuth.getInstance();
    }

    public void Login() {
        if (mAuth.getCurrentUser() == null) return;
        getUserData();
    }

    public void Logout() {
        mAuth.signOut();
        //TODO Nav to Login From Profile
        //navController.navigate(R.id.act);
        Toast.makeText(context, "Logged Out", Toast.LENGTH_LONG).show();
    }

    private void getUserData() {
        String uId = mAuth.getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference().child("users")
                .child(uId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel mUser = snapshot.getValue(UserModel.class);
                assert mUser != null;
                String userType = mUser.getUserType();
                navigat(userType);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void navigat(String userType) {
        if (userType.equals("instructor")) {
            navController.navigate(R.id.instructorProfileFragment);
            Toast.makeText(context, "Welcome Instructor", Toast.LENGTH_SHORT).show();
        } else if (userType.equals("student")) {
            navController.navigate(R.id.studentProfileFragment);
            Toast.makeText(context, "Welcome Student", Toast.LENGTH_SHORT).show();
        }
    }
}
