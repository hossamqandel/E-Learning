package com.android.edraak.Fragment.Common;

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

import com.android.edraak.Adapter.Multi.ChatAdapter;
import com.android.edraak.Model.ChatModel;
import com.android.edraak.R;
import com.android.edraak.databinding.FragmentChatBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends Fragment {

    FragmentChatBinding binding;
    NavController navController;
    List<ChatModel> mChats = new ArrayList<>();
    ChatAdapter mAdapter = new ChatAdapter();
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    String userId = FirebaseAuth.getInstance().getUid();
    String courseId;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);
        courseId = ChatFragmentArgs.fromBundle(getArguments()).getCourseId();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        binding.sendBTN.setOnClickListener(v -> {
            sendMessage(courseId, userId);
        });

        chatData(courseId);
    }


    private void sendMessage(String courseId, String userId) {
        String message = binding.editTextTextPersonName.getText().toString().trim();
        if (!message.isEmpty()) {
            mDatabaseRef.child("chats").child(courseId).push().setValue(new ChatModel(message, "", userId));
            binding.editTextTextPersonName.setText("");
        }
        else {
            Toast.makeText(getContext(), "enter Somethings", Toast.LENGTH_SHORT).show();
        }
    }

    private void chatData(String courseId) {

        mDatabaseRef.child("chats").child(courseId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mChats.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    mChats.add(snapshot1.getValue(ChatModel.class));
                }
                mAdapter.setList(mChats);
                binding.recyclerChat.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}