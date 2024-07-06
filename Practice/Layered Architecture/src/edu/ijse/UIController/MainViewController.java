package edu.ijse.UIController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {
    public AnchorPane context;

    public void itemOnAction(ActionEvent actionEvent) throws IOException {
        setUI("ItemView");
    }

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        setUI("CustomerView");
    }

    public void orderOnAction(ActionEvent actionEvent) {

    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
