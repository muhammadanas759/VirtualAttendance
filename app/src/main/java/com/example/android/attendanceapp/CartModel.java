package com.example.android.attendanceapp;

public class CartModel {
     private String classname,section,noOfStudent,shiftOfmodel,credit,subject;

    public CartModel() {
    }

    public CartModel(String aClass, String section, String noOfStudent, String shiftOfmodel, String credit, String subject) {
        this.classname = aClass;
        this.section = section;
        this.noOfStudent = noOfStudent;
        this.shiftOfmodel = shiftOfmodel;
        this.credit = credit;
        this.subject = subject;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getNoOfStudent() {
        return noOfStudent;
    }

    public void setNoOfStudent(String noOfStudent) {
        this.noOfStudent = noOfStudent;
    }

    public String getShiftOfmodel() {
        return shiftOfmodel;
    }

    public void setShiftOfmodel(String shiftOfmodel) {
        this.shiftOfmodel = shiftOfmodel;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
