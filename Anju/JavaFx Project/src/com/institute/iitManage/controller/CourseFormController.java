package com.institute.iitManage.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    public void backToHomeOnAction(ActionEvent actionEvent) throws Exception {
        setUI("DashBoard");
    }

    public void subjectAddOnAction(ActionEvent actionEvent) {
    }

    public void saveStudentOnAction(ActionEvent actionEvent) {
    }

    private void setUI(String location)throws Exception{
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
