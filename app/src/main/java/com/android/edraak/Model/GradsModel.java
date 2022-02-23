package com.android.edraak.Model;

public class GradsModel {

    private int quizGrade  ;
    private int attendanceGrade ;

    public GradsModel() {
    }

    public GradsModel(int quizGrade, int attendanceGrade) {
        this.quizGrade = quizGrade;
        this.attendanceGrade = attendanceGrade;
    }

    public int getQuizGrade() {
        return quizGrade;
    }

    public void setQuizGrade(int quizGrade) {
        this.quizGrade = quizGrade;
    }

    public int getAttendanceGrade() {
        return attendanceGrade;
    }

    public void setAttendanceGrade(int attendanceGrade) {
        this.attendanceGrade = attendanceGrade;
    }
}
