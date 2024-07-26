package com.institute.iitManage.controller;

import com.institute.iitManage.db.DBConnection;
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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

    public void saveTeacherOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Teacher teacher = new Teacher(
                txtTeacherID.getText(),
                txtFullName.getText(),
                txtAddress.getText(),
                txtContact.getText()
        );

        if (btnSaveTeacher.getText().equalsIgnoreCase("Save Teacher")) {
            if (saveTeacher(teacher)) {
                generateTeacherId();
                clear();
                setTableData(searchText);
                new Alert(Alert.AlertType.INFORMATION, "Teacher has been Saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Teacher could not be saved!").show();
            }
        } else {
            if (updateTeacher(teacher)) {
                setTableData(searchText);
                generateTeacherId();
                clear();
                new Alert(Alert.AlertType.INFORMATION, "Teacher has been updated...!").show();
                btnSaveTeacher.setText("Save Teacher");
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong..!").show();
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
        try {
            String lastId = getLastId();

            if (lastId != null) {
                String[] split = lastId.split("-");
                String lastIdAsString = split[1];
                int lastIdAsInteger = Integer.parseInt(lastIdAsString);
                lastIdAsInteger++;
                String newId = "T-" + lastIdAsInteger;
                txtTeacherID.setText(newId);
            } else {
                txtTeacherID.setText("T-1");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void setTableData(String name) {
        ObservableList<TeacherTm> oblist = FXCollections.observableArrayList();

        try {
            List<Teacher> teacherList = searchTeacher(name);

            for (Teacher teacher : teacherList) {
                Button button = new Button("Delete");

                oblist.add(new TeacherTm(
                        teacher.getTeacherId(),
                        teacher.getName(),
                        teacher.getAddress(),
                        teacher.getContact(),
                        button
                ));

                button.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.NO, ButtonType.YES);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.get().equals(ButtonType.YES)) {
                        try {
                            deleteTeacher(teacher.getTeacherId());
                            new Alert(Alert.AlertType.INFORMATION, "Teacher has been Deleted...!").show();
                            setTableData(searchText);
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                            new Alert(Alert.AlertType.ERROR, "Error deleting teacher. Please try again.").show();
                        }
                    }
                });
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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

    private boolean saveTeacher(Teacher teacher) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO teacher VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, teacher.getTeacherId());
        preparedStatement.setString(2, teacher.getName());
        preparedStatement.setString(3, teacher.getAddress());
        preparedStatement.setString(4, teacher.getContact());
        return preparedStatement.executeUpdate() > 0;
    }

    private String getLastId() throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT teacher_id FROM teacher ORDER BY CAST(SUBSTRING(teacher_id,3) AS UNSIGNED) DESC LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    private List<Teacher> searchTeacher(String text) throws ClassNotFoundException, SQLException {
        text = "%" + text + "%";
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM teacher WHERE teacher_name LIKE ? OR teacher_address LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, text);
        preparedStatement.setString(2, text);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Teacher> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new Teacher(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return list;
    }

    private void deleteTeacher(String id) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM teacher WHERE teacher_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

    private boolean updateTeacher(Teacher teacher) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE teacher SET teacher_name = ?, teacher_address = ?, teacher_contact = ? WHERE teacher_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, teacher.getName());
        preparedStatement.setString(2, teacher.getAddress());
        preparedStatement.setString(3, teacher.getContact());
        preparedStatement.setString(4, teacher.getTeacherId());
        return preparedStatement.executeUpdate() > 0;
    }
}
