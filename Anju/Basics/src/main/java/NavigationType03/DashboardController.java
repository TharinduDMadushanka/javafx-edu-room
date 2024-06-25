package NavigationType03;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardController {
    public AnchorPane loadContex;

    public void windowAOnAction(ActionEvent actionEvent) throws IOException {
        loadContex.getChildren().add(FXMLLoader.load(getClass().getResource("/NavigationType03/WindowA.fxml")));
    }

    public void windowBOnAction(ActionEvent actionEvent) throws IOException {
        loadContex.getChildren().add(FXMLLoader.load(getClass().getResource("/NavigationType03/WindowB.fxml")));
    }
}
