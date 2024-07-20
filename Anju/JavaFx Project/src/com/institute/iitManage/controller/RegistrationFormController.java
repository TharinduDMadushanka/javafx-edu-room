package com.institute.iitManage.controller;

import com.institute.iitManage.db.Database;
import com.institute.iitManage.model.Course;
import com.institute.iitManage.model.Registration;
import com.institute.iitManage.model.RegistrationTm;
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
import java.util.Optional;

public class RegistrationFormController {

    public AnchorPane context;
    public TextField txtRegId;
    public TextField txtStudent;
    public ComboBox<String> cmbCourse;
    public RadioButton rdbPaid;
    public ToggleGroup payment;
    public RadioButton rdbPending;
    public TextField txtSearch;
    public TableView<RegistrationTm> tblReg;
    public TableColumn<RegistrationTm, String> colId;
    public TableColumn<RegistrationTm, String> colStudent;
    public TableColumn<RegistrationTm, String> colReg;
    public TableColumn<RegistrationTm, String> colCourse;
    public TableColumn<RegistrationTm, String> colPayment;
    public TableColumn<RegistrationTm, Button> colOption;
    public Button btnSave;

    private String searchText = "";

    public void initialize() {
        // Initialize Table Columns
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStudent.setCellValueFactory(new PropertyValueFactory<>("student"));
        colReg.setCellValueFactory(new PropertyValueFactory<>("regDate"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        // Generate initial Registration ID and populate table
        generateRegId();
        setTableData(searchText);

        // Load courses into ComboBox
        loadCourses();

        // Set up listeners
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            setTableData(searchText);
        });

        tblReg.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setTableDataValue(newValue);
            }
        });
    }

    public void newRegOnAction(ActionEvent actionEvent) {
        generateRegId();
        clear();
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws Exception {
        setUi("DashBoard");
    }

    public void saveRegOnAction(ActionEvent actionEvent) {
        String regId = txtRegId.getText();
        String student = txtStudent.getText();
        LocalDate regDate = LocalDate.now(); // Assuming registration date is the current date
        String course = cmbCourse.getValue();
        String paymentStatus = rdbPaid.isSelected() ? "Paid" : "Pending";

        if (btnSave.getText().equalsIgnoreCase("Save Registration")) {
            Registration registration = new Registration(
                    regId,
                    student,
                    regDate,
                    course,
                    paymentStatus
            );

            Database.registrationTable.add(registration);
            generateRegId();
            clear();
            setTableData(searchText);
            new Alert(Alert.AlertType.INFORMATION, "Registration has been Saved!").show();

        } else {
            for (Registration registration : Database.registrationTable) {
                if (registration.getId().equals(txtRegId.getText())) {
                    registration.setStudent(txtStudent.getText());
                    registration.setRegDate(LocalDate.now()); // Update to current date or provided date
                    registration.setCourse(cmbCourse.getValue());
                    registration.setPaymentStatus(rdbPaid.isSelected() ? "Paid" : "Pending");

                    setTableData(searchText);
                    generateRegId();
                    clear();
                    new Alert(Alert.AlertType.INFORMATION, "Registration has been updated!").show();
                    btnSave.setText("Save Registration");
                    return;
                }
            }
        }
    }

    private void generateRegId() {
        if (!Database.registrationTable.isEmpty()) {
            Registration lastRegistration = Database.registrationTable.get(Database.registrationTable.size() - 1);
            String stringId = lastRegistration.getId();
            String[] split = stringId.split("-");
            int lastIdAsInteger = Integer.parseInt(split[1]);
            lastIdAsInteger++;
            String newId = "R-" + lastIdAsInteger;
            txtRegId.setText(newId);
        } else {
            txtRegId.setText("R-1");
        }
    }

    private void setUi(String location) throws Exception {
        Stage stage = (Stage) context.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/institute/iitManage/view/" + location + ".fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
        stage.centerOnScreen();
    }

    private void setTableData(String searchText) {
        ObservableList<RegistrationTm> oblist = FXCollections.observableArrayList();

        for (Registration registration : Database.registrationTable) {
            if (registration.getStudent().toLowerCase().contains(searchText.toLowerCase())) {
                Button button = new Button("Delete");
                Registration currentRegistration = registration;

                button.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
                        Database.registrationTable.remove(currentRegistration);
                        new Alert(Alert.AlertType.CONFIRMATION, "Registration has been Deleted!").show();
                        setTableData(searchText);
                    }
                });

                oblist.add(new RegistrationTm(
                        registration.getId(),
                        registration.getStudent(),
                        registration.getRegDate().toString(), // Assume this is in LocalDate format
                        registration.getCourse(),
                        registration.getPaymentStatus(),
                        button
                ));
            }
        }
        tblReg.setItems(oblist);
    }

    private void clear() {
        txtStudent.clear();
        cmbCourse.setValue(null);
        payment.selectToggle(null);
    }

    private void setTableDataValue(RegistrationTm registration) {
        txtRegId.setText(registration.getId());
        txtStudent.setText(registration.getStudent());
        cmbCourse.setValue(registration.getCourse());
        if (registration.getPaymentStatus().equals("Paid")) {
            rdbPaid.setSelected(true);
        } else {
            rdbPending.setSelected(true);
        }
        btnSave.setText("Update Registration");
    }

    private void loadCourses() {
        // Sample course names
        String[] sampleCourses = {
                "SE",
                "CS",
                "AI",
                "DS"
        };

        ObservableList<String> courseList = FXCollections.observableArrayList(sampleCourses);
        cmbCourse.setItems(courseList);
    }
}
