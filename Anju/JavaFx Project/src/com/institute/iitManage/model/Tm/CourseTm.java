package com.institute.iitManage.model.Tm;

import javafx.scene.control.Button;

public class CourseTm {
    private String courseId;
    private String courseName;
    private String teacherName;
    private Button btnTechList;
    private double cost;
    private Button btnDelete;

    public CourseTm() {
    }

    public CourseTm(String courseId, String courseName, String teacherName, Button btnTechList, double cost, Button btnDelete) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.btnTechList = btnTechList;
        this.cost = cost;
        this.btnDelete = btnDelete;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Button getBtnTechList() {
        return btnTechList;
    }

    public void setBtnTechList(Button btnTechList) {
        this.btnTechList = btnTechList;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }
}
