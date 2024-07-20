package com.institute.iitManage.model;

import javafx.scene.control.Button;

public class RegistrationTm {
    private String id;
    private String student;
    private String regDate;
    private String course;
    private String paymentStatus;
    private Button button;

    public RegistrationTm(String id, String student, String regDate, String course, String paymentStatus, Button button) {
        this.id = id;
        this.student = student;
        this.regDate = regDate;
        this.course = course;
        this.paymentStatus = paymentStatus;
        this.button = button;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
