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
import javafx.stage.Window;

public class TeacherFormController {
    public AnchorPane context;
    public TextField txtTeacherID;
    public TextField txtFullName;
    public TextField txtAddress;
    public Button btnSaveTeacher;
    public TextField txtSearch;
    public TextField txtContact;

    public TableView tblTeacher;
    public TableColumn colTeacherID;
    public TableColumn colFullName;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableColumn colOption;


    public void backToHomeOnAction(ActionEvent actionEvent) throws Exception {
        setUi("DashBoard");
    }

    public void newTeacherOnAction(ActionEvent actionEvent) {
    }

    public void saveTeacherOnAction(ActionEvent actionEvent) {
    }

    private void setUi(String location)throws Exception {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
