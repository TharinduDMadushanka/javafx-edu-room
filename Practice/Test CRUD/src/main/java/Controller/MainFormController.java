package Controller;

import Dto.StudentDto;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainFormController{
    private StudentController studentController;

    public TableColumn colName;
    public TableColumn colAge;
    public TableColumn colGrade;
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
            StudentDto dto=new StudentDto(txtName.getText(),Integer.parseInt(txtGrade.getText()),Integer.parseInt(txtAge.getText()));
            String resp = studentController.saveStudent(dto);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.show();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.show();
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
