package com.institute.iitManage.controller;

import com.institute.iitManage.db.DBConnection;
import com.institute.iitManage.model.User;
import com.institute.iitManage.util.security.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane context;
    public TextField txtEmail;
    public TextField txtPassword;
    public Hyperlink txtForgotPw;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {

        String email = txtEmail.getText().trim().toLowerCase();
        String password = txtPassword.getText().trim();

        // Connect login method with MySQL database
        try {
            User user = login(email);
            if (user != null) {
                if (new PasswordManager().checkPassword(password, user.getPassword())) {
                    setUI("DashBoard");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Email or Password Incorrect...!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "User Not Found...!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUI("SignupForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

    public void forgotPasswordOnAction(ActionEvent actionEvent) throws IOException {
        setUI("ForgotPasswordForm");
    }

    private User login(String email) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iitmanage", "root", "Thariya920@");

        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT * FROM user WHERE email = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            User user = new User(
                    resultSet.getString("email"),
                    resultSet.getString("first_name"),  // Make sure this matches your database column name
                    resultSet.getString("last_name"),   // Make sure this matches your database column name
                    resultSet.getString("password")
            );
            return user;
        }
        return null;
    }
}
