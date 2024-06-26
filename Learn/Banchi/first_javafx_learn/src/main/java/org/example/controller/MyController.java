package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class MyController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void buttonClick(){
        System.out.println("The button is clicked.");
    }

//    public void goPageTwo(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("views/pageTwo.fxml"));
//        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//        scene=new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

    public void goPageTwo(javafx.event.ActionEvent actionEvent) throws  IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/views/pageTwo.fxml"));
        stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openNewWindow() throws IOException {
        FXMLLoader fxmlloader=new FXMLLoader(getClass().getResource("/views/pageTwo.fxml"));
        Parent root =(Parent) fxmlloader.load();
        Stage stage=new Stage();
        stage.setTitle("Page 2");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
