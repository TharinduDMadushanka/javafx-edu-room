package com.institute.iitManage.controller;

import com.institute.iitManage.db.DBConnection;
import com.institute.iitManage.model.Course;
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
import java.sql.*;
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
    public TableColumn<CourseTm, String> colTeacher;
    public TableColumn<CourseTm, Button> colTechOnCourse;
    public TableColumn<CourseTm, Double> colCost;
    public TableColumn<CourseTm, Button> colOption;

    ArrayList<String> teachersArray = new ArrayList<>();
    ObservableList<TechnolgiesTm> tmList = FXCollections.observableArrayList();

    public void initialize() {
        generateCourseID();
        setTeachers();
        loadCourses();

        colTechId.setCellValueFactory(new PropertyValueFactory<>("code"));
        coltech.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("button"));

        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colTeacher.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        colTechOnCourse.setCellValueFactory(new PropertyValueFactory<>("btnTechList"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }

    public void newCourseOnAction(ActionEvent actionEvent) {
        // Implementation for new course action
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("Dashboard");
    }

    public void addTechOnAction(ActionEvent actionEvent) {
        if (!isExist(txtTechnologies.getText().trim())) {
            Button button = new Button("Remove");

            tmList.add(
                    new TechnolgiesTm(
                            tmList.size() + 1,
                            txtTechnologies.getText().trim(),
                            button
                    )
            );
            tblTechnologies.setItems(tmList);
            txtTechnologies.clear();
        } else {
            txtTechnologies.selectAll();
            new Alert(Alert.AlertType.INFORMATION, "This tech already exists").show();
        }
    }

    public void saveCourseOnAction(ActionEvent actionEvent) {
        String[] selectedTech = new String[tmList.size()];
        int pointer = 0;

        for (TechnolgiesTm t : tmList) {
            selectedTech[pointer] = t.getName();
            pointer++;
        }

        Course course = new Course(
                txtCourseID.getText(),
                txtCourseName.getText(),
                selectedTech,
                cmbTeachers.getValue().split("\\.")[0],
                Double.parseDouble(txtCost.getText().trim())
        );

        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String query = "INSERT INTO course (course_id, course_name, technologies, teacher_id, cost) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, course.getCourseId());  // Changed to match type
            preparedStatement.setString(2, course.getCourseName());
            preparedStatement.setString(3, String.join(",", course.getSubjects()));
            preparedStatement.setString(4, course.getTeacherId());  // Changed to match type
            preparedStatement.setDouble(5, course.getCost());
            preparedStatement.executeUpdate();

            new Alert(Alert.AlertType.INFORMATION, "Course has been saved!").show();
            generateCourseID();
            loadCourses();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save course.").show();
        }
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

    private void generateCourseID() {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String query = "SELECT course_id FROM course ORDER BY course_id DESC LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String lastId = resultSet.getString("course_id");
                int newId = Integer.parseInt(lastId.split("-")[1]) + 1;
                txtCourseID.setText("C-" + newId);
            } else {
                txtCourseID.setText("C-1");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setTeachers() {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String query = "SELECT teacher_id, teacher_name FROM teacher";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                teachersArray.add(resultSet.getInt("teacher_id") + ". " + resultSet.getString("teacher_name"));
            }

            ObservableList<String> oblist = FXCollections.observableArrayList(teachersArray);
            cmbTeachers.setItems(oblist);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean isExist(String tech) {
        for (TechnolgiesTm tm : tmList) {
            if (tm.getName().equalsIgnoreCase(tech)) {
                return true;
            }
        }
        return false;
    }

    private void loadCourses() {
        ObservableList<CourseTm> courseList = FXCollections.observableArrayList();

        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String query = "SELECT c.course_id, c.course_name, c.technologies, c.cost, t.teacher_name " +
                    "FROM course c JOIN teacher t ON c.teacher_id = t.teacher_id";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Button techButton = new Button("Show Tech");
                Button deleteButton = new Button("Delete");

                CourseTm tm = new CourseTm(
                        resultSet.getString("course_id"),  // Ensure correct ID format
                        resultSet.getString("course_name"),
                        resultSet.getString("teacher_name"),
                        techButton,
                        resultSet.getDouble("cost"),
                        deleteButton
                );

                deleteButton.setOnAction(e -> {
                    try {
                        String deleteQuery = "DELETE FROM course WHERE course_id = ?";
                        PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery);
                        deleteStmt.setString(1, tm.getCourseId());  // Ensure correct ID format
                        deleteStmt.executeUpdate();
                        new Alert(Alert.AlertType.INFORMATION, "Course has been deleted!").show();
                        loadCourses();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "Failed to delete course.").show();
                    }
                });

                courseList.add(tm);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        tblCourse.setItems(courseList);
    }
}
