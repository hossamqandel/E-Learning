package com.android.edraak.Fragment.UserActivities.InstructorActivities.InstructorCourseQuiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.edraak.Model.QuizModel;
import com.android.edraak.R;
import com.android.edraak.databinding.FragmentInstCourseQuizBinding;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class InstCourseQuizFragment extends Fragment {

    FragmentInstCourseQuizBinding binding;
    NavController navController;
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    List<QuizModel> mQuizGroup;
    String courseId;

    InstCourseQuizViewModel instCourseQuizViewModel;
    public InstCourseQuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        instCourseQuizViewModel = ViewModelProviders.of(this).get(InstCourseQuizViewModel.class);

        return inflater.inflate(R.layout.fragment_inst_course_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentInstCourseQuizBinding.bind(view);
        navController = Navigation.findNavController(view);
        courseId = InstCourseQuizFragmentArgs.fromBundle(getArguments()).getCourseId();
        mQuizGroup = new ArrayList<>();

        clearFields(binding.clearFieldsBTN);

        binding.addQuistionBTN.setOnClickListener(v -> addQuistion());
        binding.removeLastQuistionBTN.setOnClickListener(v -> removeLastQuistion(mQuizGroup));
        binding.uploadAssignmentBTN.setOnClickListener(v -> uploadAssignmentToFirebase(mQuizGroup));
    }


    private void clearFields(ImageView imageView) {
        imageView.setOnClickListener(v -> {
            binding.quistion.setText("");
            binding.firstAnswerET.setText("");
            binding.secondAnswerET.setText("");
            binding.thirdAnswerET.setText("");
            binding.lastAnswerET.setText("");
        });
    }
    private void removeLastQuistion(List<QuizModel> list) {
        if (list.size() != 0){
            list.remove(list.size() - 1);
            Toast.makeText(getContext(), ""+list.size(), Toast.LENGTH_SHORT).show();
        }
    }
    private void uploadAssignmentToFirebase(List<QuizModel> list){
        String quistion = binding.quistion.getText().toString().trim();
        String firstAnswer = binding.firstAnswerET.getText().toString().trim();
        String secondAnswer = binding.secondAnswerET.getText().toString().trim();
        String thirdAnswer = binding.thirdAnswerET.getText().toString().trim();
        String lastAnswer = binding.lastAnswerET.getText().toString().trim();

        if (quistion.isEmpty() || firstAnswer.isEmpty() || secondAnswer.isEmpty() || thirdAnswer.isEmpty() || lastAnswer.isEmpty()){}

        else {
            mDatabaseRef.child("courses quiz").child(courseId).push().setValue(list);
            list.clear();
        }

    }
    private void addQuistion(){

        int radioId = binding.radioGroup.getCheckedRadioButtonId();
        View radioButton = binding.radioGroup.findViewById(radioId);
        int index = binding.radioGroup.indexOfChild(radioButton);

        QuizModel quizModel = new QuizModel();
        quizModel.setQuestion(binding.quistion.getText().toString().trim());
        quizModel.setFirstAnswer(binding.firstAnswerET.getText().toString().trim());
        quizModel.setSecondeAnswer(binding.secondAnswerET.getText().toString().trim());
        quizModel.setThirdAnswer(binding.thirdAnswerET.getText().toString().trim());
        quizModel.setLastAnswer(binding.lastAnswerET.getText().toString().trim());
        quizModel.setRightAnswer(index+1); //+1 because first index of RadioButtons will be start from zero

        validation(quizModel);
    }
    private void validation(QuizModel quizModel) {
        String quistion = quizModel.getQuestion();
        String firstAnswer = quizModel.getFirstAnswer();
        String secondAnswer = quizModel.getSecondeAnswer();
        String thirdAnswer = quizModel.getThirdAnswer();
        String lastAnswer = quizModel.getLastAnswer();

        if (quistion.isEmpty()){binding.quistion.setError("required");}
        else if (quistion.isEmpty()){binding.quistion.setError("required");}
        else if (firstAnswer.isEmpty()){binding.quistion.setError("required");}
        else if (secondAnswer.isEmpty()){binding.quistion.setError("required");}
        else if (thirdAnswer.isEmpty()){binding.quistion.setError("required");}
        else if (lastAnswer.isEmpty()){binding.quistion.setError("required");}
        else {
            activeQuistion(quizModel);
        }
    }
    private void activeQuistion(QuizModel quizModel) {
        mQuizGroup.add(quizModel);
        Toast.makeText(getContext(), ""+mQuizGroup.size(), Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}