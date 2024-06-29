package com.institute.iitManage.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupFormController {
    public AnchorPane context;
    public TextField txtFirstName;
    public TextField txtEmail;
    public TextField txtLastName;
    public TextField txtPassword;

    public void signupOnAction(ActionEvent actionEvent) {
    }

    public void alreadyHaveAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }


    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
