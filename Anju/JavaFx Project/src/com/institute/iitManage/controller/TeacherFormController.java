package com.institute.iitManage.controller;

import com.institute.iitManage.db.Database;
import com.institute.iitManage.model.Teacher;
import com.institute.iitManage.model.Tm.TeacherTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Optional;

public class TeacherFormController {
    public AnchorPane context;
    public TextField txtTeacherID;
    public TextField txtFullName;
    public TextField txtAddress;
    public Button btnSaveTeacher;
    public TextField txtSearch;
    public TextField txtContact;

    public TableView<TeacherTm> tblTeacher;
    public TableColumn<TeacherTm,String> colTeacherID;
    public TableColumn<TeacherTm,String>  colFullName;
    public TableColumn<TeacherTm,String>  colContact;
    public TableColumn<TeacherTm,String>  colAddress;
    public TableColumn<TeacherTm,Button>  colOption;

    String searchText="";

    public void initialize() {
        colTeacherID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        generateTeacherId();
        setTableData(searchText);

    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws Exception {
        setUi("DashBoard");
    }

    public void newTeacherOnAction(ActionEvent actionEvent) {

        generateTeacherId();
        setTableData(searchText);
        clear();
        btnSaveTeacher.setText("Save Teacher");
    }

    public void saveTeacherOnAction(ActionEvent actionEvent) {
    }

    private void setUi(String location)throws Exception {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

    private void generateTeacherId(){

        if(!Database.teacherTable.isEmpty()){

            Teacher lastTeacher = Database.teacherTable.get(Database.teacherTable.size()-1);
            String stringId = lastTeacher.getTeacherId();
            String[] split=stringId.split("-");
            String lastIdAsString = split[1];
            int lastIdAsInteger = Integer.parseInt(lastIdAsString);
            lastIdAsInteger++;
            String newId = "T-"+lastIdAsInteger;
            txtTeacherID.setText(newId);

        }else {
            txtTeacherID.setText("T-1");
        }
    }

    private void setTableData(String name){
        ObservableList<TeacherTm> oblist = FXCollections.observableArrayList();

        for (Teacher teacher : Database.teacherTable) {
            if (teacher.getTeacherId().contains(name)) {

                Button button = new Button("Delete");

                oblist.add(new TeacherTm(
                        teacher.getTeacherId(),
                        teacher.getName(),
                        teacher.getContact(),
                        teacher.getAddress(),
                        button
                ));

                button.setOnAction(event -> {
                    Alert alert =new Alert(Alert.AlertType.CONFIRMATION,"Are you sure...!",ButtonType.YES,ButtonType.NO,ButtonType.YES);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.get().equals(ButtonType.YES)) {
                        Database.teacherTable.remove(teacher);
                        new Alert(Alert.AlertType.CONFIRMATION,"Teacher has been Deleted!").show();
                        setTableData(searchText);
                    }
                });
            }
        }
    }

    private void clear(){

        txtFullName.clear();
        txtAddress.clear();
        txtContact.clear();
    }

    private void setTableDataValue(TeacherTm teacher){
        txtTeacherID.setText(teacher.getTeacherId());
        txtFullName.setText(teacher.getName());
        txtAddress.setText(teacher.getAddress());
        txtContact.setText(teacher.getContact());
        btnSaveTeacher.setText("Update Teacher");
    }
}
