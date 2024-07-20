package com.institute.iitManage.controller;

import com.institute.iitManage.db.Database;
import com.institute.iitManage.model.Course;
import com.institute.iitManage.model.Teacher;
import com.institute.iitManage.model.Tm.CourseTm;
import com.institute.iitManage.model.Tm.TechnolgiesTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CourseFormController {
    public AnchorPane context;
    public TextField txtCourseID;
    public TextField txtCourseName;
    public TextField txtCost;
    public ComboBox<String> cmbTeachers;
    public TextField txtTechnologies;

    public TableView<TechnolgiesTm> tblTechnologies;
    public TableColumn<TechnolgiesTm, Integer> colTechId;
    public TableColumn<TechnolgiesTm, String> coltech;
    public TableColumn<TechnolgiesTm, Button> colRemove;
    public Button btnSave;
    public TextField txtSearch;

    public TableView<CourseTm> tblCourse;
    public TableColumn<CourseTm, String> colCourseId;
    public TableColumn<CourseTm, String> colCourse;
    public TableColumn<CourseTm, String>  colTechnologyy;
    public TableColumn<CourseTm, String> colTeacher;
    public TableColumn<CourseTm, Double> colCost;
    public TableColumn<CourseTm, Button> colOption;
    public TextField txtCourseId;
    public TableView<CourseTm> technologuTable;
    public TableColumn<CourseTm, String>  colTechRemove;
    public TableColumn<CourseTm, String> colTechnology;
    public ComboBox txtTeacher;
    public TextField txtTechnology;
    public TextField txtTechnology1;
    public TableView<CourseTm>  courseTable;


    private ArrayList<String> teachersArray = new ArrayList<>();
    private ObservableList<TechnolgiesTm> tmList = FXCollections.observableArrayList();

    public void initialize() {
        generateCourseID();
        setTeachers();
        loadCourse();

        // Initialize Table Columns
        colTechId.setCellValueFactory(new PropertyValueFactory<>("code"));
        coltech.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("button"));

        colCourseId.setCellValueFactory(new PropertyValueFactory<>("code"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        colTechnologyy.setCellValueFactory(new PropertyValueFactory<>("btnTechList"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btnOption"));

        // Setup table interactions
        setupTableInteractions();
    }

    public void newCourseOnAction(ActionEvent actionEvent) {
        // Clear fields for new course entry
        txtCourseID.clear();
        txtCourseName.clear();
        txtCost.clear();
        cmbTeachers.setValue(null);
        txtTechnologies.clear();
        tmList.clear();
        generateCourseID();
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("Dashboard");
    }

    public void saveSCourseOnAction(ActionEvent actionEvent) {
        if (!isExist(txtTechnologies.getText().trim())) {
            Button button = new Button("Remove");
            button.setOnAction(e -> {
                TechnolgiesTm tech = (TechnolgiesTm) ((Button) e.getSource()).getUserData();
                tmList.remove(tech);
                tblTechnologies.setItems(tmList);
            });

            TechnolgiesTm techTm = new TechnolgiesTm(
                    tmList.size() + 1,
                    txtTechnologies.getText().trim(),
                    button
            );
            tmList.add(techTm);
            tblTechnologies.setItems(tmList);
            txtTechnologies.clear();
        } else {
            txtTechnologies.selectAll();
            new Alert(Alert.AlertType.INFORMATION, "This technology already exists").show();
        }
    }

    public void addTechnologyOnAction(ActionEvent actionEvent) {
        String[] selectedTech = new String[tmList.size()];
        int pointer = 0;

        for (TechnolgiesTm t : tmList) {
            selectedTech[pointer] = t.getName();
            pointer++;
        }

        if (btnSave.getText().equalsIgnoreCase("Save Course")) {
            Course course = new Course(
                    txtCourseID.getText(),
                    txtCourseName.getText(),
                    selectedTech,
                    cmbTeachers.getValue().split("\\.")[0],
                    Double.parseDouble(txtCost.getText().trim())
            );
            Database.courseTable.add(course);
            new Alert(Alert.AlertType.INFORMATION, "Course has been saved!").show();
            generateCourseID();
            loadCourse();
        }
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/institute/iitManage/view/" + location + ".fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
        stage.centerOnScreen();
    }

    private void generateCourseID() {
        try {
            if (Database.courseTable != null && !Database.courseTable.isEmpty()) {
                Course lastCourse = Database.courseTable.get(Database.courseTable.size() - 1);
                String stringId = lastCourse.getCourseId();
                String[] split = stringId.split("-");
                int lastIdAsInteger = Integer.parseInt(split[1]);
                lastIdAsInteger++;
                String newId = "C-" + lastIdAsInteger;
                txtCourseID.setText(newId);
            } else {
                txtCourseID.setText("C-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Log the exception or show an error message
            new Alert(Alert.AlertType.ERROR, "Failed to generate course ID: " + e.getMessage()).show();
        }
    }



    private void setTeachers() {
        teachersArray.clear();
        for (Teacher t : Database.teacherTable) {
            teachersArray.add(t.getTeacherId() + ". " + t.getName());
        }
        ObservableList<String> oblist = FXCollections.observableArrayList(teachersArray);
        cmbTeachers.setItems(oblist);
    }

    private boolean isExist(String tech) {
        for (TechnolgiesTm tm : tmList) {
            if (tm.getName().equalsIgnoreCase(tech)) {
                return true;
            }
        }
        return false;
    }

    private void loadCourse() {
        ObservableList<CourseTm> courseList = FXCollections.observableArrayList();

        for (Course c : Database.courseTable) {
            Button techButton = new Button("Show Tech");
            techButton.setOnAction(e -> showTechnologies(c));

            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(e -> {
                Database.courseTable.remove(c);
                loadCourse();
                new Alert(Alert.AlertType.CONFIRMATION, "Course has been deleted!").show();
            });

            CourseTm tm = new CourseTm(
                    c.getCourseId(),
                    c.getCourseName(),
                    c.getTeacherId(),
                    techButton,
                    c.getCost(),
                    deleteButton
            );

            courseList.add(tm);
        }
        tblCourse.setItems(courseList);
    }

    private void showTechnologies(Course course) {
        // Create and show a dialog or a new window to display technologies for the selected course
        // For simplicity, this example just shows an alert with course technologies
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Course Technologies");
        alert.setHeaderText("Technologies for Course: " + course.getCourseName());
        alert.setContentText(String.join(", ", course.getSubjects()));
        alert.showAndWait();
    }

    private void setupTableInteractions() {
        tblTechnologies.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtTechnologies.setText(newSelection.getName());
            }
        });
    }
}
