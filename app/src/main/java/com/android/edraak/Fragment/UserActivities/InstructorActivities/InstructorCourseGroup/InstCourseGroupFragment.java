package com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorCourseGroup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.edraak.Adapter.Instructor.InstructorCourseAdapter;
import com.android.edraak.Model.CourseModel;
import com.android.edraak.R;
import com.android.edraak.databinding.FragmentInstCourseGroupBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class InstCourseGroupFragment extends Fragment {

    FragmentInstCourseGroupBinding binding;
    NavController navController;

    InstCourseManager courseManager;
    InstructorCourseAdapter mAdapter = new InstructorCourseAdapter();
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    List<CourseModel> mGroup;


    public InstCourseGroupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inst_course_group, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentInstCourseGroupBinding.bind(view);
        navController = Navigation.findNavController(view);

        binding.uiADDNEWCOURSEBTN.setOnClickListener(v -> navController.navigate(R.id.action_instructorCourseFragment_to_instructorCourseDetailsFragment));

        getCoursesDat();
    }


    private void getCoursesDat(){
        mGroup = new ArrayList<>();
        mDatabaseRef.child("courses").orderByChild("instructorId").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mGroup.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    CourseModel course = ds.getValue(CourseModel.class);

                    mGroup.add(course);

                }

                mAdapter.setList(mGroup);
                binding.uiRecyclerCourses.setAdapter(mAdapter);

                mAdapter.setOnClick(new InstructorCourseAdapter.onClick() {
                    @Override
                    public void onItemClick(String courseId) {

                        InstCourseGroupFragmentDirections.ActionInstructorCourseFragmentToInstructorCourseControlFragment action
                                = InstCourseGroupFragmentDirections.actionInstructorCourseFragmentToInstructorCourseControlFragment();
                        action.setCourseId(courseId);
                        navController.navigate(action);

                    }
                });

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