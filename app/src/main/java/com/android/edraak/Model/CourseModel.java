package com.android.edraak.Model;

public class CourseModel {

    private String instructorId  ;
    private String courseId ;
    private String courseName ;
    private double assignmentGrade ;
    private double attendanceGrade ;
    private double projectsGrade ;

    public CourseModel() {
    }

    public CourseModel(String instructorId, String courseId, String courseName, double assignmentGrade, double attendanceGrade, double projectsGrade) {
        this.instructorId = instructorId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.assignmentGrade = assignmentGrade;
        this.attendanceGrade = attendanceGrade;
        this.projectsGrade = projectsGrade;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
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

    public double getAssignmentGrade() {
        return assignmentGrade;
    }

    public void setAssignmentGrade(double assignmentGrade) {
        this.assignmentGrade = assignmentGrade;
    }

    public double getAttendanceGrade() {
        return attendanceGrade;
    }

    public void setAttendanceGrade(double attendanceGrade) {
        this.attendanceGrade = attendanceGrade;
    }

    public double getProjectsGrade() {
        return projectsGrade;
    }

    public void setProjectsGrade(double projectsGrade) {
        this.projectsGrade = projectsGrade;
    }
}
