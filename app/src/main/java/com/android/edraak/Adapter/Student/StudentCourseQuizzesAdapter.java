package com.android.edraak.Adapter.Student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.edraak.R;

import java.util.ArrayList;
import java.util.List;

public class StudentCourseQuizzesAdapter extends RecyclerView.Adapter<StudentCourseQuizzesAdapter.MyViewHolder> {

    private List<String> list;

    private OnClick onClick ;

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.student_course_quizzes_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.coursePosition.setText("Quiz " + (position + 1));

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView coursePosition ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            coursePosition = itemView.findViewById(R.id.quizCounter);
            if (onClick != null){

                itemView.setOnClickListener(view -> onClick.onItemClick(list.get(getAdapterPosition())));
            }

        }
    }

    public interface OnClick {
        void onItemClick(String quizId);
    }
}