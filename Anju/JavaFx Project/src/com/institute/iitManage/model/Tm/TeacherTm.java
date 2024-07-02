package com.institute.iitManage.model.Tm;

import javafx.scene.control.Button;

public class TeacherTm {
    private String teacherId;
    private String name;
    private String address;
    private String contact;
    private Button button;

    public TeacherTm() {
    }

    public TeacherTm(String teacherId, String name, String address, String contact, Button button) {
        this.teacherId = teacherId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.button = button;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "TeacherTm{" +
                "teacherId='" + teacherId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", button=" + button +
                '}';
    }
}
