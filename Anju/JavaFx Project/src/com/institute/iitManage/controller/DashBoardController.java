package com.institute.iitManage.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardController {
    public AnchorPane context;
    public Label lblDate;
    public Label lblTime;

    public void studentsOnAction(ActionEvent actionEvent) throws IOException {
        setUI("StudentForm");
    }

    public void teacherOnAction(ActionEvent actionEvent) {
    }

    public void intakeOnAction(ActionEvent actionEvent) {
    }

    public void courseOnAction(ActionEvent actionEvent) {
    }

    public void registrationOnAction(ActionEvent actionEvent) {
    }


    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
