package com.institute.iitManage.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CourseFormController {
    public AnchorPane context;
    public TextField txtCourseId;
    public TextField txtTeacherId;
    public TextField txtCourseName;
    public TextField txtCost;
    public TextField txtSubjects;
    public TextField txtSearch;
    public Button btnSaveCourse;
    
    public TableView tblCourse;
    public TableColumn colCourseId;
    public TableColumn colCourseName;
    public TableColumn colTeacherId;
    public TableColumn colCost;
    public TableColumn colSubjects;

    public void newCourseOnAction(ActionEvent actionEvent) {
    }

    public void backToHomeOnAction(ActionEvent actionEvent) {
    }

    public void subjectAddOnAction(ActionEvent actionEvent) {
    }

    public void saveStudentOnAction(ActionEvent actionEvent) {
    }
}
