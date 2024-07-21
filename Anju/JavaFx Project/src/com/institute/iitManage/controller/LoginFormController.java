package com.institute.iitManage.controller;

import com.institute.iitManage.db.Database;
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
import java.sql.*;
import java.util.Optional;

public class LoginFormController {
    public AnchorPane context;
    public TextField txtEmail;
    public TextField txtPassword;
    public Hyperlink txtForgotPw;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {

        String email = txtEmail.getText().trim().toLowerCase();
        String password = txtPassword.getText().trim();

        //connect login method with mysql database

        try{
            User user = login(email);
            if(null != user){
                if(new PasswordManager().checkPassword(password, user.getPassword())){
                    setUI("DashBoard");
                }else {
                    new Alert(Alert.AlertType.ERROR,"Email or Password Incorrect...!").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"User Not Found...!").show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }



        /*for(User user: Database.userTable){
            if(user.getEmail().equals(email)){
                if(user.getPassword().equals(password)){
                    System.out.println(user.toString());
                    return;
                }else {
                    new Alert(Alert.AlertType.ERROR,"Email or Password Incorrect").show();
                    return;
                }
            }
        }
        new Alert(Alert.AlertType.ERROR,"User Not Found...!");*/

//        Optional<User> selectedUser = Database.userTable.stream().filter(e -> e.getEmail().equals(email)).findFirst();
//        if(selectedUser.isPresent()){
//            if (new PasswordManager().checkPassword(password,selectedUser.get().getPassword())){
//                setUI("DashBoard");
//            }else {
//                new Alert(Alert.AlertType.ERROR,"Email or Password Incorrect...!").show();
//            }
//        }else {
//            new Alert(Alert.AlertType.ERROR,"User Not Found...!").show();
//        }

    }

    public void createAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUI("SignupForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

    public void forgotPasswordOnAction(ActionEvent actionEvent) throws IOException {
        setUI("ForgotPasswordForm");
    }

    private User login(String email) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iitmanage", "root", "Thariya920@");
        String sql ="SELECT * FROM user WHERE email= '"+email+"'";

        //System.out.println(sql); // --> inject sql

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if(resultSet.next()){
            User user = new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            return user;
        }
        return null;
    }
}
