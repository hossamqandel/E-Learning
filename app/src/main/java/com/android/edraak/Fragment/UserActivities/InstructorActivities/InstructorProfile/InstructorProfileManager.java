package com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorProfile;

import androidx.annotation.NonNull;

import com.android.edraak.Manager.LoginManager;
import com.android.edraak.Model.UserModel;
import com.android.edraak.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InstructorProfileManager {

    FirebaseUser mFireUser = FirebaseAuth.getInstance().getCurrentUser();;
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    String mUserId = mFireUser.getUid();



    protected void logOutAndNavigateBackToLogin(LoginManager loginManager) {
        loginManager.Logout();
    }


//    protected UserModel getUserDataFromFirebaseRealtime(UserModel user){
//
//        if (mUserId == null){}
//
//        mDatabaseRef.child("users").child(mUserId).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                user = snapshot.getValue(UserModel.class);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        return user;
//    }
}
