package com.android.edraak.Fragment.UserActivities.StudentActivities.StudentSolveQuiz;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.edraak.Model.QuizModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudeQuizViewModel extends ViewModel {

    private DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    private List<QuizModel> mListOfQuizzes = new ArrayList<>();
    public MutableLiveData<List<QuizModel>> onQuizListUpdated = new MutableLiveData<>();


    public void getData(String courseId, String quizId) {
        fetchData(courseId, quizId);
    }

    private void fetchData(String courseId, String quizId){
        mDatabaseRef.child("courses quiz").child(courseId).child(quizId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mListOfQuizzes.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    QuizModel quiz = ds.getValue(QuizModel.class);
                    mListOfQuizzes.add(quiz);
                    onQuizListUpdated.setValue(mListOfQuizzes);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
