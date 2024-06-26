package Controller;

import Dto.StudentDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class MainFormController{
    public TableView<StudentDto> table;
    private StudentController studentController;

    public TableColumn<StudentDto,String> colName;
    public TableColumn<StudentDto,Integer> colAge;
    public TableColumn<StudentDto,Integer> colGrade;
    public TextField txtName;
    public TextField txtGrade;
    public TextField txtAge;
    public Button btnInsert;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;


    public void initialize(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        try {
            studentController = new StudentController();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.show();
        }

        loadTable();
    }



    public void btnSaveOnAction(ActionEvent actionEvent) {

        try{
            StudentDto dto=new StudentDto(txtName.getText(),Integer.parseInt(txtGrade.getText()),Integer.parseInt(txtAge.getText()));
            String resp = studentController.saveStudent(dto);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.show();
            loadTable();
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

    public void loadTable() {
        try {
            ObservableList<StudentDto> studentList = FXCollections.observableArrayList();
            ArrayList<StudentDto> studentDtos = studentController.getStudentList();

            if (studentDtos != null) {
                System.out.println("Loaded " + studentDtos.size() + " students from database");
                studentList.addAll(studentDtos);
            } else {
                System.out.println("No students found in database");
            }

            table.setItems(studentList);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.show();
        }
    }

    public void tableMouseClicked(MouseEvent mouseEvent) {

    }
}
