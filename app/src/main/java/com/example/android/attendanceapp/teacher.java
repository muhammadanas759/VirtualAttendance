package com.example.android.attendanceapp;

import java.util.List;

public class teacher {
    private List<String> classOffer,courseOffer;
    private String name;
    private int noOfCourseOffer;
    private String password;

    public teacher() {
    }

    public teacher(List<String> classOffer, List<String> courseOffer, String name, int noOfCourseOffer, String password) {
        this.classOffer = classOffer;
        this.courseOffer = courseOffer;
        this.name = name;
        this.noOfCourseOffer = noOfCourseOffer;
        this.password = password;
    }

    public List<String> getClassOffer() {
        return classOffer;
    }

    public void setClassOffer(List<String> classOffer) {
        this.classOffer = classOffer;
    }

    public List<String> getCourseOffer() {
        return courseOffer;
    }

    public void setCourseOffer(List<String> courseOffer) {
        this.courseOffer = courseOffer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoOfCourseOffer() {
        return noOfCourseOffer;
    }

    public void setNoOfCourseOffer(int noOfCourseOffer) {
        this.noOfCourseOffer = noOfCourseOffer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
