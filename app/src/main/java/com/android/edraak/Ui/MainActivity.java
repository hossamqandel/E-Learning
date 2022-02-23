package com.android.edraak.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.edraak.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DatabaseReference mDatabasRef = FirebaseDatabase.getInstance().getReference();
    private final String pushAndGetId = mDatabasRef.push().getKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}