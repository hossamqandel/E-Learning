package com.android.edraak.Model;

public class MembersModel {

    private String studentEmail ;
    private String courseId ;
    private String courseName;
    private double attendanceGrade ;
    private double quizGrade ;

    public MembersModel() {
    }

    public MembersModel(String studentEmail, String courseId, String courseName, double attendanceGrade, double quizGrade) {
        this.studentEmail = studentEmail;
        this.courseId = courseId;
        this.courseName = courseName;
        this.attendanceGrade = attendanceGrade;
        this.quizGrade = quizGrade;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getAttendanceGrade() {
        return attendanceGrade;
    }

    public void setAttendanceGrade(double attendanceGrade) {
        this.attendanceGrade = attendanceGrade;
    }

    public double getQuizGrade() {
        return quizGrade;
    }

    public void setQuizGrade(double quizGrade) {
        this.quizGrade = quizGrade;
    }
}
