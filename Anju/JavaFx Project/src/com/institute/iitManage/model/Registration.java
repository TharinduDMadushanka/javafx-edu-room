package com.institute.iitManage.model;

import java.time.LocalDate;

public class Registration {
    private String id;
    private String student;
    private LocalDate regDate;
    private String course;
    private String paymentStatus;

    public Registration(String id, String student, LocalDate regDate, String course, String paymentStatus) {
        this.id = id;
        this.student = student;
        this.regDate = regDate;
        this.course = course;
        this.paymentStatus = paymentStatus;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStudent() { return student; }
    public void setStudent(String student) { this.student = student; }

    public LocalDate getRegDate() { return regDate; }
    public void setRegDate(LocalDate regDate) { this.regDate = regDate; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
}
