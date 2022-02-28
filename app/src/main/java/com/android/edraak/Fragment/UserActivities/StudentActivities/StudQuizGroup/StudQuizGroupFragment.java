package com.android.edraak.Fragment.UserActivities.StudentActivities.StudQuizGroup;

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

import com.android.edraak.Adapter.Student.StudentCourseQuizzesAdapter;
import com.android.edraak.R;
import com.android.edraak.databinding.FragmentStudCourseGroupBinding;
import com.android.edraak.databinding.FragmentStudQuizGroupBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class StudQuizGroupFragment extends Fragment {

    FragmentStudQuizGroupBinding binding;
    NavController navController;
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    StudentCourseQuizzesAdapter mAdapter = new StudentCourseQuizzesAdapter();
    List<String> mCourseQuizzes = new ArrayList<>();
    String courseId;
    public StudQuizGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        courseId = StudQuizGroupFragmentArgs.fromBundle(getArguments()).getCourseId();
        return inflater.inflate(R.layout.fragment_stud_quiz_group, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentStudQuizGroupBinding.bind(view);
        navController = Navigation.findNavController(view);

        loadCourseQuizzes(courseId);
        Toast.makeText(getContext(), ""+courseId, Toast.LENGTH_SHORT).show();
    }

    
    private void loadCourseQuizzes(String CourseId){
        mDatabaseRef.child("courses quiz").child(courseId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {

                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        mCourseQuizzes.add(snapshot1.getKey());
                    }
                    mAdapter.setList(mCourseQuizzes);
                    binding.recyclerQuizzes.setAdapter(mAdapter);
                } else {
                    Toast.makeText(getContext(), "No Quiz", Toast.LENGTH_SHORT).show();
                }
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