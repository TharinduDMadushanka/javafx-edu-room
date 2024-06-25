package NavigationType03;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardController {
    public AnchorPane loadContex;

    public void initialize() throws IOException {
        setUI("/NavigationType03/WindowA");
    }

    public void windowAOnAction(ActionEvent actionEvent) throws IOException {
//        loadContex.getChildren().clear();
//        loadContex.getChildren().add(FXMLLoader.load(getClass().getResource("/NavigationType03/WindowA.fxml")));
        setUI("/NavigationType03/WindowA");
    }

    public void windowBOnAction(ActionEvent actionEvent) throws IOException {
//        loadContex.getChildren().clear();
//        loadContex.getChildren().add(FXMLLoader.load(getClass().getResource("/NavigationType03/WindowB.fxml")));
        setUI("/NavigationType03/WindowB");
    }

    private void setUI(String location) throws IOException {
        loadContex.getChildren().clear();
        loadContex.getChildren().add(FXMLLoader.load(getClass().getResource(location+".fxml")));
    }
}
