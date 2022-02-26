package com.android.edraak.Adapter.Instructor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.edraak.Model.CourseModel;
import com.android.edraak.R;

import java.util.ArrayList;
import java.util.List;

public class InstructorCourseAdapter extends RecyclerView.Adapter<InstructorCourseAdapter.MyViewHolder> {

    private List<CourseModel> list = new ArrayList<>();
    private onClick onClick;

    public void setList(List<CourseModel> list) {
        this.list = list;
        notifyItemChanged(list.size() - 1);
    }

    public void setOnClick(InstructorCourseAdapter.onClick onClick) {
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.courseName.setText(list.get(position).getCourseName());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView courseName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.ui_course_Name_TV);

            itemView.setOnClickListener(v -> {
                if (onClick != null){
                    onClick.onItemClick(list.get(getLayoutPosition()).getCourseId());
                }
            });
        }
    }


    public interface onClick {

        void onItemClick(String courseId);
    }
}