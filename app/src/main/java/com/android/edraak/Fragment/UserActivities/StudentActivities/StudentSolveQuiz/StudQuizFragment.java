package com.android.edraak.Fragment.UserActivities.StudentActivities.StudentSolveQuiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.edraak.Model.QuizModel;
import com.android.edraak.R;
import com.android.edraak.databinding.FragmentStudQuizBinding;

import java.util.ArrayList;
import java.util.List;

public class StudQuizFragment extends Fragment {

    FragmentStudQuizBinding binding;
    NavController navController;
    List<QuizModel> mQuizzes = new ArrayList<>();
    int index = 0;


    public StudQuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stud_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentStudQuizBinding.bind(view);
        navController = Navigation.findNavController(view);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}