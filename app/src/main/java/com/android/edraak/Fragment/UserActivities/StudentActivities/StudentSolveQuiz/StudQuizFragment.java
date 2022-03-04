package com.android.edraak.Fragment.UserActivities.StudentActivities.StudentSolveQuiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.edraak.Model.QuizModel;
import com.android.edraak.databinding.FragmentStudQuizBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class StudQuizFragment extends Fragment {

    FragmentStudQuizBinding binding;
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    StudeQuizViewModel mStudeQuizViewModel;
    private List<QuizModel> mQuizzes = new ArrayList<>();
    private List<QuizModel> temp = new ArrayList<>();
    private String courseId, quizId;
    private int counter;
    private int score;
    QuizModel currentQuiz;


    public StudQuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStudQuizBinding.inflate(inflater, container, false);
        courseId = StudQuizFragmentArgs.fromBundle(getArguments()).getCourseId();
        quizId = StudQuizFragmentArgs.fromBundle(getArguments()).getQuizId();
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mStudeQuizViewModel = ViewModelProviders.of(this).get(StudeQuizViewModel.class);
        counter = 0;
        score = 0;


        mStudeQuizViewModel.getData(courseId, quizId);
        mStudeQuizViewModel.onQuizListUpdated.observe(this, new Observer<List<QuizModel>>() {
            @Override
            public void onChanged(List<QuizModel> list) {
                if (counter < list.size()) {
                    loadQuistions(list, counter);

                    binding.nextQuizBTN.setOnClickListener(v -> {
                        counter++;
                        loadQuistions(list, counter);

                    });

                }
            }
        });


    }


    private void loadQuistions(List<QuizModel> listOfQuizzes, int counter) {
        binding.radioGroup2.clearCheck();
        if (counter < listOfQuizzes.size()) {
            binding.nextQuizBTN.setText("Next Quistion");
            binding.studQuistion.setText(listOfQuizzes.get(counter).getQuestion());
            binding.quizFirstChoiceTV.setText(listOfQuizzes.get(counter).getFirstAnswer());
            binding.quizSecondChoiceTV.setText(listOfQuizzes.get(counter).getSecondeAnswer());
            binding.quizThirdChoiceTV.setText(listOfQuizzes.get(counter).getThirdAnswer());
            binding.quizLastChoiceTV.setText(listOfQuizzes.get(counter).getLastAnswer());

            int radioId = binding.radioGroup2.getCheckedRadioButtonId();
            View radioButton = binding.radioGroup2.findViewById(radioId);
            int CurrentSelectideRadio = binding.radioGroup2.indexOfChild(radioButton) + 1;

            if (CurrentSelectideRadio == listOfQuizzes.get(counter).getRightAnswer()) {
                score++;
            } else {
                binding.scoreTV.setText(score + "");
            }

        } else {
            binding.nextQuizBTN.setText("Finish");
        }


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}