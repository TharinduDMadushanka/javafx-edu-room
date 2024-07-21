package com.institute.iitManage.controller;

import com.institute.iitManage.db.Database;
import com.institute.iitManage.model.User;
import com.institute.iitManage.util.security.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.DataInput;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class SignupFormController {

    public AnchorPane context;
    public TextField txtFirstName;
    public PasswordField txtPassword;
    public TextField txtLastName;
    public TextField txtEmail;

    public void signupOnAction(ActionEvent actionEvent) {
        String firstName = txtFirstName.getText().trim().toLowerCase();
        String lastname = txtLastName.getText().trim().toLowerCase();
        String email = txtEmail.getText().trim().toLowerCase();
        String password = txtPassword.getText().trim();

        /*Database.userTable.add(
                new User(firstName,lastname,email,new PasswordManager().encrypt(password))
        );*/
        User user = new User(firstName,lastname,email, password);
        boolean isSaved = false;

        try {
            isSaved = singup(user);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Your Account has been Created...!").show();
                setUI("LoginForm");
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Something went wrong, Try again...!").show();
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void alreadyHaveAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

    private boolean singup(User user) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/iitmanage", "root", "Thariya920@");

        String sql = "INSERT INTO user VALUE ('" + user.getEmail() + "','" + user.getFirstName() +
                "','" + user.getLastName() + "','" + new PasswordManager().encrypt(user.getPassword()) + "')";

        Statement statement = connection.createStatement();

       /* int rowCount = statement.executeUpdate(sql);

        if (rowCount > 0) {
            return true;
        } else {
            return false;
        }*/
        System.out.println("HI");

        return statement.executeUpdate(sql) > 0;
    }


}