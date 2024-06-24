package NavigationType01;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    public void nextOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/NavigationType01/WindowB.fxml"))));
        stage.show();
    }
}
