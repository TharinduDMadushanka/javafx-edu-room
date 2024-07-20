package com.institute.iitManage.controller;

import com.institute.iitManage.db.Database;
import com.institute.iitManage.model.Intake;
import com.institute.iitManage.model.Tm.IntakeTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.util.Optional;

public class IntakeFormController {

    public AnchorPane context;
    public TextField txtIntakeID;
    public TextField txtName;
    public ComboBox<String> cmbCourse;
    public DatePicker txtDate;
    public Button btnSave;
    public TableView<IntakeTm> tblIntakes;
    public TableColumn<IntakeTm, String> colId;
    public TableColumn<IntakeTm, String> colName;
    public TableColumn<IntakeTm, LocalDate> colDate;
    public TableColumn<IntakeTm, String> colCourse;
    public TableColumn<IntakeTm, Boolean> colStatus;
    public TableColumn<IntakeTm, Button> colOption;
    public TextField txtSearch;
    public TextField txtRegId;
    public TextField txtStudent;
    public RadioButton rdbPaid;
    public ToggleGroup payment;
    public RadioButton rdbPending;
    public TableColumn colStudent;
    public TableView tblReg;
    public TableColumn colReg;
    public TableColumn colPayment;

    private String searchText = "";

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        generateIntakeId();
        setTableData(searchText);

        tblIntakes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setTableDataValue(newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            setTableData(searchText);
        });
    }

    public void newRegOnAction(ActionEvent actionEvent) {
        generateIntakeId();
        clear();
        btnSave.setText("Save Intake");
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws Exception {
        setUi("DashBoard");
    }

    public void saveRegOnAction(ActionEvent actionEvent) {
        if (btnSave.getText().equalsIgnoreCase("Save Intake")) {
            Intake intake = new Intake(
                    txtIntakeID.getText(),
                    txtName.getText(),
                    txtDate.getValue(), // Convert LocalDate to java.sql.Date
                    cmbCourse.getValue(),
                    true, // assuming new intake is always active initially
                    new Button("Delete")
            );

            Database.intakeTable.add(intake);
            generateIntakeId();
            clear();
            setTableData(searchText);
            new Alert(Alert.AlertType.INFORMATION, "Intake has been Saved!").show();

        } else {
            for (Intake intake : Database.intakeTable) {
                if (intake.getId().equals(txtIntakeID.getText())) {
                    intake.setName(txtName.getText());
                    intake.setDate(txtDate.getValue()); // Convert LocalDate to java.sql.Date
                    intake.setCourse(cmbCourse.getValue());

                    setTableData(searchText);
                    generateIntakeId();
                    clear();
                    new Alert(Alert.AlertType.INFORMATION, "Intake has been updated!").show();
                    btnSave.setText("Save Intake");
                    return;
                }
            }
        }
    }

    private void setUi(String location) throws Exception {
        Stage stage = (Stage) context.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/institute/iitManage/view/" + location + ".fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
        stage.centerOnScreen();
    }

    private void generateIntakeId() {
        if (!Database.intakeTable.isEmpty()) {
            Intake lastIntake = Database.intakeTable.get(Database.intakeTable.size() - 1);
            String stringId = lastIntake.getId();
            String[] split = stringId.split("-");
            int lastIdAsInteger = Integer.parseInt(split[1]);
            lastIdAsInteger++;
            String newId = "I-" + lastIdAsInteger;
            txtIntakeID.setText(newId);
        } else {
            txtIntakeID.setText("I-1");
        }
    }

    private void setTableData(String name) {
        ObservableList<IntakeTm> oblist = FXCollections.observableArrayList();

        for (Intake intake : Database.intakeTable) {
            if (intake.getName().toLowerCase().contains(name.toLowerCase())) {
                Button button = new Button("Delete");
                Intake currentIntake = intake;

                button.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
                        Database.intakeTable.remove(currentIntake);
                        new Alert(Alert.AlertType.CONFIRMATION, "Intake has been Deleted!").show();
                        setTableData(searchText);
                    }
                });

                oblist.add(new IntakeTm(
                        intake.getId(),
                        intake.getName(),
                        intake.getDate(), // Convert java.sql.Date to LocalDate
                        intake.getCourse(),
                        intake.isStatus(),
                        button
                ));
            }
        }
        tblIntakes.setItems(oblist);
    }

    private void clear() {
        txtName.clear();
        cmbCourse.setValue(null);
        txtDate.setValue(null);
    }

    private void setTableDataValue(IntakeTm intake) {
        txtIntakeID.setText(intake.getId());
        txtName.setText(intake.getName());
        txtDate.setValue(intake.getDate()); // LocalDate is directly set
        cmbCourse.setValue(intake.getCourse());
        btnSave.setText("Update Intake");
    }

}
