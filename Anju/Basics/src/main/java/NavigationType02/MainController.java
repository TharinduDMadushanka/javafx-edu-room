package NavigationType02;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    public AnchorPane contexA;
    public AnchorPane contexB;

    public void nextOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/NavigationType02/WindowB.fxml"));
        Stage stage = (Stage) contexA.getScene().getWindow();
        stage.setScene(new Scene(load));
        stage.centerOnScreen();

    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/NavigationType02/WindowA.fxml"));
        Stage stage = (Stage) contexB.getScene().getWindow();
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
    }
}
