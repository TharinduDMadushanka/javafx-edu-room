package org.example.PageTwoControler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerTwo {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goPageOne(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/testView.fxml"));
        stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
