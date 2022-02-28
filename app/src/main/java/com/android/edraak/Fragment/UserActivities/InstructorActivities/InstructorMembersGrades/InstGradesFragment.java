package com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorMembersGrades;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.edraak.R;
import com.android.edraak.databinding.FragmentInstGradesBinding;

public class InstGradesFragment extends Fragment {

    FragmentInstGradesBinding binding;
    NavController navController;

    public InstGradesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inst_grades, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentInstGradesBinding.bind(view);
        navController = Navigation.findNavController(view);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}