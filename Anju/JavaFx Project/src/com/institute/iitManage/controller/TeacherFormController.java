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
    public TableColumn<TeacherTm, String> colTeacherID;
    public TableColumn<TeacherTm, String> colFullName;
    public TableColumn<TeacherTm, String> colAddress;
    public TableColumn<TeacherTm, String> colContact;
    public TableColumn<TeacherTm, Button> colOption;

    String searchText = "";

    public void initialize() {
        colTeacherID.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        generateTeacherId();
        setTableData(searchText);

        tblTeacher.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setTableDataValue(newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            setTableData(searchText);
        });
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws Exception {
        setUi("DashBoard");
    }

    public void newTeacherOnAction(ActionEvent actionEvent) {
        generateTeacherId();
        clear();
        btnSaveTeacher.setText("Save Teacher");
    }

    public void saveTeacherOnAction(ActionEvent actionEvent) {
        if (btnSaveTeacher.getText().equalsIgnoreCase("Save Teacher")) {
            Teacher teacher = new Teacher(
                    txtTeacherID.getText(),
                    txtFullName.getText(),
                    txtAddress.getText(),
                    txtContact.getText()

            );

            Database.teacherTable.add(teacher);
            generateTeacherId();
            clear();
            setTableData(searchText);
            new Alert(Alert.AlertType.INFORMATION, "Teacher has been Saved...!").show();
            System.out.println(teacher.toString());

        } else {
            for (Teacher teacher : Database.teacherTable) {
                if (teacher.getTeacherId().equals(txtTeacherID.getText())) {
                    teacher.setName(txtFullName.getText());
                    teacher.setAddress(txtAddress.getText());
                    teacher.setContact(txtContact.getText());


                    setTableData(searchText);
                    generateTeacherId();
                    clear();
                    new Alert(Alert.AlertType.INFORMATION, "Teacher has been updated...!").show();
                    btnSaveTeacher.setText("Save Teacher");
                    return;
                }
            }
        }
    }

    private void setUi(String location) throws Exception {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

    private void generateTeacherId() {
        if (!Database.teacherTable.isEmpty()) {
            Teacher lastTeacher = Database.teacherTable.get(Database.teacherTable.size() - 1);
            String stringId = lastTeacher.getTeacherId();
            String[] split = stringId.split("-");
            String lastIdAsString = split[1];
            int lastIdAsInteger = Integer.parseInt(lastIdAsString);
            lastIdAsInteger++;
            String newId = "T-" + lastIdAsInteger;
            txtTeacherID.setText(newId);
        } else {
            txtTeacherID.setText("T-1");
        }
    }

    private void setTableData(String name) {
        ObservableList<TeacherTm> oblist = FXCollections.observableArrayList();

        for (Teacher teacher : Database.teacherTable) {
            if (teacher.getTeacherId().contains(name)){

                Button button = new Button("Delete");
                Teacher currentTeacher= teacher; // Create a final copy of the current teacher

                oblist.add(new TeacherTm(
                        teacher.getTeacherId(),
                        teacher.getName(),
                        teacher.getAddress(),
                        teacher.getContact(),
                        button
                ));

                button.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure...!", ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.get().equals(ButtonType.YES)) {
                        Database.teacherTable.remove(currentTeacher);
                        new Alert(Alert.AlertType.CONFIRMATION, "Teacher has been Deleted!").show();
                        setTableData(searchText);
                    }
                });
            }
        }
        tblTeacher.setItems(oblist);
    }

    private void clear() {
        txtFullName.clear();
        txtAddress.clear();
        txtContact.clear();
    }

    private void setTableDataValue(TeacherTm teacher) {
        txtTeacherID.setText(teacher.getTeacherId());
        txtFullName.setText(teacher.getName());
        txtAddress.setText(teacher.getAddress());
        txtContact.setText(teacher.getContact());
        btnSaveTeacher.setText("Update Teacher");
    }
}
