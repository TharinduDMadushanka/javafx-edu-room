package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainFormController{

    private StudentController studentController;
    public TextField txtName;
    public TextField txtGrade;
    public TextField txtAge;
    public Button btnInsert;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;

    public MainFormController() throws Exception {
        studentController=new StudentController();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        try{

        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtName.clear();
        txtGrade.clear();
        txtAge.clear();
    }
}
